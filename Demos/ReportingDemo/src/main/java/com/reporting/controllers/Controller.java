package com.reporting.controllers;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.reporting.enums.FileType;
import com.reporting.fetchdata.FetchPersonsService;
import com.reporting.services.Report;

@RestController
public class Controller {
	@Autowired
	private FetchPersonsService personsReport;
	

	@GetMapping("/getReport")
	public void getReport(@RequestParam FileType fileType, final HttpServletResponse response)
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, IOException {
		
		response.setContentType(String.format("application/%s", fileType.name().toLowerCase()));
		response.setHeader("Content-Disposition", String.format("attachment;filename=persons.%s", fileType.name().toLowerCase()));
		
		Method method = Report.class.getMethod(String.format("get%s", fileType.name().toUpperCase()), Object[].class, OutputStream.class);
		method.invoke(Report.class, personsReport.getAllPersons(),  response.getOutputStream());
	}
}
