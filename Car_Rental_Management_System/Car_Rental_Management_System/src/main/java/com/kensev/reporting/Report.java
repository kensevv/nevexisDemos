package com.kensev.reporting;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class Report {

	private static Font font = FontFactory.getFont(FontFactory.COURIER, 20, BaseColor.BLACK);

	public static void getPDF(Object[] allObjects, OutputStream response) throws DocumentException, URISyntaxException,
			MalformedURLException, IOException, IllegalArgumentException, IllegalAccessException {
		
		Image img = Image.getInstance("C:\\Users\\kense\\Documents\\nevexis\\nevexisDemos\\Car_Rental_Management_System\\Car_Rental_Management_System\\logo.png");
		 validList(allObjects);

		Document document = new Document();

		img.scalePercent((document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin())
				/ img.getWidth() * 100);

		
		Paragraph title = new Paragraph(allObjects[0].getClass().getSimpleName() + " list", font);
		title.setRole(PdfName.H1);
		title.setAlignment(Paragraph.ALIGN_CENTER);
		
		Paragraph totalNumber = new Paragraph(
				String.format("Total %s: %d", allObjects[0].getClass().getSimpleName(), allObjects.length), font);
		totalNumber.setAlignment(Paragraph.ALIGN_CENTER);
		
		
		PdfPTable table = new PdfPTable(allObjects[0].getClass().getDeclaredFields().length);
		addTableHeader(allObjects, table);
		addRows(allObjects, table);

		PdfWriter.getInstance(document, new BufferedOutputStream(response, 1024 * 1024 * 3));
		document.open();
		document.add(img);
		document.add(title);
		document.add(totalNumber);
		document.add(Chunk.NEWLINE);
		document.add(table);
		document.close();
	}

	private static void addTableHeader(Object[] allObjects, PdfPTable table) {
		for (Field field : allObjects[0].getClass().getDeclaredFields()) {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(field.getName()));
			table.addCell(header);
		}
	}

	private static void addRows(Object[] allObjects, PdfPTable table)
			throws IllegalArgumentException, IllegalAccessException {
		for (Object object : allObjects) {
			Field[] fields = object.getClass().getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				table.addCell(String.valueOf(field.get(object)));
			}
		}
	}

	private static void validList(Object[] allObjects) {
		if (allObjects.length <= 0 || allObjects == null) {
			throw new Error("Empty list");
		}
	}
}