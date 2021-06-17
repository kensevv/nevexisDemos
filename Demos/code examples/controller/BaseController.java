package com.frantishex.lwr.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.frantishex.lwr.controller.dto.BaseEntityDTO;
import com.frantishex.lwr.model.BaseEntity;

public class BaseController {
	
	protected final Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	@Autowired
	protected ModelMapper mapper;
	
	@PersistenceContext
	protected EntityManager em;
	
	protected <T extends BaseEntityDTO> ResponseEntity<T> getInternalServerError(Exception e, T dto) {
		logger.error("Error:",e);
		dto.setErrorMessage(e.getMessage());
		return new ResponseEntity<T>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	protected <DTO extends BaseEntityDTO, E extends BaseEntity> ResponseEntity<List<DTO>> convertToDtoList(List<E> entities) {
		List<DTO> result = mapper.map(entities,  new TypeToken<List<DTO>>(){}.getRawType());
		return new ResponseEntity<List<DTO>>(result, HttpStatus.OK);
	}
	
	protected <DTO extends BaseEntityDTO, T extends BaseEntity > ResponseEntity<Page<DTO>> convertToDtoPage(Page<T> page, int pageNo) {
		Page<DTO> result = mapper.map(page, new TypeToken<Page<DTO>>() {}.getRawType());
		return new ResponseEntity<Page<DTO>>(result, HttpStatus.OK);
	}
	
//	@ExceptionHandler({ Throwable.class})
//    public void handleException(Throwable e) {
//        e.printStackTrace();
//    }	
}
