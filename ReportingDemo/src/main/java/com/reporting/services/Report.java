package com.reporting.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.reporting.threads.FetchPageThread;

@Service
public class Report {
	@Autowired
	private DBService dbService;

	private final int THREAD_COUNT = 8;
	private long personsCount;
	private int limitPeronsPerPage; // limit
	private int offset;
	private Persons[] allPersons;

	@PostConstruct
	public void postConstruct() {
		doCalculations();
		try {
			fetchPersons();
		} catch (InterruptedException | BrokenBarrierException e1) {
			e1.printStackTrace();
		}
		try {
			fillTxtFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			fillPdfFile();
		} catch (DocumentException | URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		try {
			fillExelFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void fetchPersons() throws InterruptedException, BrokenBarrierException {
		allPersons = new Persons[(int)personsCount];
		CyclicBarrier barrier = new CyclicBarrier(THREAD_COUNT + 1);

		for (int i = 0; i < THREAD_COUNT; i++) {
			new FetchPageThread(dbService, barrier, limitPeronsPerPage, offset, allPersons).start();
			offset += limitPeronsPerPage;
		}
		barrier.await();
	}

	private void fillTxtFile() throws IOException {
		try (FileWriter writer = new FileWriter("output.txt");) {
			for (Persons person : allPersons) {
				writer.write(String.format("%d: %s", person.getId(), person.getNames()) + System.lineSeparator());
			}
			writer.write(String.format("Total Persons Count : %d", this.personsCount) + System.lineSeparator());
		}
	}
	
	private void fillPdfFile() throws DocumentException, URISyntaxException, MalformedURLException, IOException {
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream("persons.pdf"));
		document.open();
		
		Path path = Paths.get(ClassLoader.getSystemResource("thumbnail.png").toURI());
		Image img = Image.getInstance(path.toAbsolutePath().toString());
		
		int indentation = 0;
		float scaler = ((document.getPageSize().getWidth() - document.leftMargin()
		               - document.rightMargin() - indentation) / img.getWidth()) * 100;
		img.scalePercent(scaler);
		document.add(img);
		
		Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
		Chunk chunk = new Chunk(String.format("Total people: %d", this.personsCount), font);
		document.add(chunk);
		PdfPTable table = new PdfPTable(2);
		addTableHeader(table);
		addRows(table);
		document.add(table);
		
		document.close();
	}
	
	
	private void fillExelFile() throws IOException {
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
			Row row = sheet.createRow(i+2);
			Cell cell1 = row.createCell(0);
			cell1.setCellValue(allPersons[i].getId());
			cell1 = row.createCell(1);
			cell1.setCellValue(allPersons[i].getNames());
		}
		
		File currDir = new File(".");
		String path = currDir.getAbsolutePath();
		String fileLocation = path.substring(0, path.length() - 1) + "persons.xlsx";

		FileOutputStream outputStream = new FileOutputStream(fileLocation);
		workbook.write(outputStream);
		workbook.close();
	}
	
	private void doCalculations() {
		personsCount = dbService.getPersonsCount();
		limitPeronsPerPage = (int) Math.ceil((double) personsCount / (double) THREAD_COUNT);
		offset = 0;
	}
	
	private void addTableHeader(PdfPTable table) {
	    Stream.of("ID", "Names")
	      .forEach(columnTitle -> {
	        PdfPCell header = new PdfPCell();
	        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
	        header.setBorderWidth(2);
	        header.setPhrase(new Phrase(columnTitle));
	        table.addCell(header);
	    });
	}
	
	private void addRows(PdfPTable table) {
	    for(Persons person : allPersons) {
	    	table.addCell(person.getId().toString());
	    	table.addCell(person.getNames().toString());
	    }
	}
}