 package com.reporting.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.itextpdf.text.DocumentException;
import com.reporting.services.Report;

@RestController
public class Controller {
	@Autowired
	private Report report;

	@GetMapping(value = "/downloadExcell", produces = "application/excell")
	public ResponseEntity<StreamingResponseBody> downloadExcell(HttpServletResponse response) throws IOException {

		response.setContentType("application/Excell");
		response.setHeader("Content-Disposition", "attachment;filename=persons.xlsx");

		StreamingResponseBody stream = out -> {
			report.getExelFile(response.getOutputStream());

		};
		return new ResponseEntity<StreamingResponseBody>(stream, HttpStatus.OK);
	}

	@GetMapping(value = "/downloadPdf", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<StreamingResponseBody> pdfReport(final HttpServletResponse response)
			throws MalformedURLException, DocumentException, URISyntaxException, IOException {

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "attachment;filename=persons.pdf");

		StreamingResponseBody stream = out -> {
			try {
				report.getPdfFile(response.getOutputStream());
			} catch (DocumentException | URISyntaxException e) {
				e.printStackTrace();
			}
		};
		return new ResponseEntity<StreamingResponseBody>(stream, HttpStatus.OK);
	}

	@GetMapping(value = "/downloadTxt", produces = "application/txt")
	public ResponseEntity<StreamingResponseBody> txtReport(final HttpServletResponse response)
			throws MalformedURLException, DocumentException, URISyntaxException, IOException {

		response.setContentType("application/txt");
		response.setHeader("Content-Disposition", "attachment;filename=persons.txt");

		StreamingResponseBody stream = out -> {

			report.getTxtFile(response.getOutputStream());
		};
		return new ResponseEntity<StreamingResponseBody>(stream, HttpStatus.OK);
	}
	// pdf - file from disk download:
	/*
	 * @GetMapping(value = "/downloadPdf", produces = "application/pdf") public
	 * ResponseEntity<InputStreamResource> downloadPdf(HttpServletResponse response)
	 * throws IOException {
	 * 
	 * File file = new File("persons.pdf"); HttpHeaders respHeaders = new
	 * HttpHeaders();
	 * 
	 * respHeaders.setContentLength(file.length());
	 * respHeaders.setContentDispositionFormData("attachment", file.getName());
	 * 
	 * InputStreamResource isr = new InputStreamResource(new FileInputStream(file));
	 * return new ResponseEntity<InputStreamResource>(isr, respHeaders,
	 * HttpStatus.OK); }
	 */
}
