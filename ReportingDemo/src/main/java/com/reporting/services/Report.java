package com.reporting.services;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.PostConstruct;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.reporting.entities.Persons;
import com.reporting.threadpool.ThreadPool;
import com.reporting.threads.FetchPageThread;

@Service
public class Report {

	private static Font font;
	private static Image img;
	@Autowired
	private DBService dbService;
	@Autowired
	private ThreadPool threadPool;
	private long personsCount;
	private int limitPeronsPerPage; 
	private int offset;
	private Persons[] allPersons;
	
	static {
		font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
		try {
			img = Image.getInstance(
					Paths.get(ClassLoader.getSystemResource("thumbnail.png").toURI()).toAbsolutePath().toString());
		} catch (BadElementException | IOException | URISyntaxException e) {
			throw new Error(e);
		}
	}
	
	@PostConstruct
	public void postConstruct() {
		doCalculations();
		try {
			fetchPersons();
		} catch (InterruptedException | BrokenBarrierException e1) {
			e1.printStackTrace();
		}
	}

	private void fetchPersons() throws InterruptedException, BrokenBarrierException {
		allPersons = new Persons[(int) personsCount];
		CyclicBarrier barrier = new CyclicBarrier(threadPool.getThreadCount() + 1);

		for (int i = 0; i < threadPool.getThreadCount(); i++) {
			threadPool.getExecutorService()
					.submit(new FetchPageThread(dbService, barrier, limitPeronsPerPage, offset, allPersons));
			offset += limitPeronsPerPage;
		}
		barrier.await();
	}

	public void getTXT(OutputStream response) throws IOException {
		try (PrintStream writer = new PrintStream(response)) {
			writer.println(String.format("Total Persons Count : %d", this.personsCount));
			for (Persons person : allPersons) {
				writer.println(String.format("%d: %s", person.getId(), person.getNames()));
			}
		}
	}

	public void getPDF(OutputStream response)
			throws DocumentException, URISyntaxException, MalformedURLException, IOException {
		Document document = new Document();

		img.scalePercent(
				((document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin()) / img.getWidth())
						* 100);
		Chunk chunk = new Chunk(String.format("Total people: %d", this.personsCount), font);
		PdfPTable table = new PdfPTable(2);
		addTableHeader(table);
		addRows(table);

		PdfWriter.getInstance(document, new BufferedOutputStream(response, 1024 * 1024 * 3));
		document.open();
		document.add(img);
		document.add(chunk);
		document.add(table);
		document.close();
	}

	public void getXLSX(OutputStream response) throws IOException {
		Workbook workbook = new XSSFWorkbook();

		Sheet sheet = workbook.createSheet("Persons");
		sheet.setColumnWidth(0, 4000);
		sheet.setColumnWidth(1, 6000);

		Row personsCount = sheet.createRow(0);
		Cell cell = personsCount.createCell(0);
		cell.setCellValue("Total People");
		cell = personsCount.createCell(1);
		cell.setCellValue(this.personsCount);

		Row header = sheet.createRow(1);

		Cell headerCell = header.createCell(0);
		headerCell.setCellValue("Id");
		headerCell = header.createCell(1);
		headerCell.setCellValue("Names");

		for (int i = 0; i < allPersons.length; i++) {
			Row row = sheet.createRow(i + 2);
			Cell cell1 = row.createCell(0);
			cell1.setCellValue(allPersons[i].getId());
			cell1 = row.createCell(1);
			cell1.setCellValue(allPersons[i].getNames());
		}
		workbook.write(response);
		workbook.close();
	}

	public void getZIP(OutputStream response) throws IOException, DocumentException, URISyntaxException {
		ZipOutputStream zipOut = new ZipOutputStream(response);
		ByteArrayOutputStream fileOutput = new ByteArrayOutputStream();
		getPDF(fileOutput);
		zipOut.putNextEntry(new ZipEntry("persons.pdf"));
		zipOut.write(fileOutput.toByteArray());
		
		fileOutput.reset();
		getXLSX(fileOutput);
		zipOut.putNextEntry(new ZipEntry("persons.xlsx"));
		zipOut.write(fileOutput.toByteArray());
		
		fileOutput.reset();
		getTXT(fileOutput);
		zipOut.putNextEntry(new ZipEntry("persons.txt"));
		zipOut.write(fileOutput.toByteArray());
		
		zipOut.close();
	}
	
	private void doCalculations() {
		personsCount = dbService.getPersonsCount();
		limitPeronsPerPage = (int) Math.ceil((double) personsCount / (double) threadPool.getThreadCount());
		offset = 0;
	}

	private void addTableHeader(PdfPTable table) {
		Stream.of("ID", "Names").forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(columnTitle));
			table.addCell(header);
		});
	}

	private void addRows(PdfPTable table) {
		for (Persons person : allPersons) {
			table.addCell(String.valueOf(person.getId()));
			table.addCell(person.getNames());
		}
	}
}