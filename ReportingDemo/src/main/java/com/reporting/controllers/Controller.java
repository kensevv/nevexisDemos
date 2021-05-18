package com.reporting.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.reporting.services.Report;

@RestController
public class Controller {
	@Autowired
	private Report report;

	@GetMapping("/getReport/{fileType}")
	public void getReport(@PathVariable String fileType, final HttpServletResponse response)
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, IOException {
		StringBuilder methodName = new StringBuilder();
		reflectionSetUp(methodName, fileType, response);
		Method method = report.getClass().getMethod(methodName.toString(), OutputStream.class);
		method.invoke(report, response.getOutputStream());
	}

	private void reflectionSetUp(StringBuilder methodName, String fileType, final HttpServletResponse response) {
		switch (fileType.toUpperCase()) {
		case "TXT":
			methodName.append("getTxtFile");
			response.setContentType("application/txt");
			response.setHeader("Content-Disposition", "attachment;filename=persons.txt");
			break;
		case "PDF":
			methodName.append("getPdfFile");
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment;filename=persons.pdf");
			break;
		case "EXCELL":
			methodName.append("getExcell");
			response.setContentType("application/Excell");
			response.setHeader("Content-Disposition", "attachment;filename=persons.xlsx");
			break;
		case "ZIP":
			methodName.append("getZip");
			response.setContentType("application/zip");
			response.setHeader("Content-Disposition", "attachment;filename=persons.zip");
		}
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
