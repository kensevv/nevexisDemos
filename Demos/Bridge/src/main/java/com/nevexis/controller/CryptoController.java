package com.nevexis.controller;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nevexis.dto.CryptoDTO;
import com.nevexis.model.Crypto;
import com.nevexis.services.CryptoRepo;
import com.sun.el.parser.ParseException;


@RestController
@RequestMapping("/crypto")
public class CryptoController {

	@Autowired
	private CryptoRepo cryptoRepo;
	
	@Autowired
	private ModelMapper modelMapper;



	public void addNew(Crypto c) {
		cryptoRepo.saveAndFlush(c);
	}
	
	private Collection<CryptoDTO> convertToCryptoDTO(Collection<Crypto> cryptoList) throws ParseException {
		List<CryptoDTO> dtos = cryptoList
				.stream()
				.map(crypto -> modelMapper.map(crypto, CryptoDTO.class))
				.collect(Collectors.toList());
		return dtos;
	}
	
	@GetMapping(value = "/{currency}")
	public Collection<CryptoDTO> getData (@PathVariable("currency") String currencyName) throws ParseException {
		return convertToCryptoDTO(cryptoRepo.findByCurrency(currencyName));
	}
}
