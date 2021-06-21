package com.frantishex.lwr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.frantishex.lwr.controller.dto.PersonDTO;
import com.frantishex.lwr.model.Person;
import com.frantishex.lwr.service.PersonRepository;

@RestController("personController")
@RequestMapping("/person")
public class PersonController extends BaseController {

	@Autowired
	private PersonRepository personDao;

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonDTO> getPerson(@PathVariable("id") Long personId) {
		PersonDTO result = mapper.map(personDao.findById(personId).get(), PersonDTO.class);
		return new ResponseEntity<PersonDTO>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PersonDTO>> getPersonByName(@RequestParam("name") String name) {
		return convertToDtoList(personDao.findByName(name));
	}

	@RequestMapping(value = "/get/egn/{egn}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonDTO> getPersonByEGN(@PathVariable("egn") String egn) {
		PersonDTO result = mapper.map(personDao.findByEGN(egn).get(0), PersonDTO.class);
		return new ResponseEntity<PersonDTO>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonDTO> addPerson(@RequestBody PersonDTO personDTO) {
		Person person = mapper.map(personDTO, Person.class);
		person.setId(null);
		if (personDao.findByWholeName(personDTO.getName()).isEmpty()) {
			PersonDTO result = mapper.map(personDao.saveAndFlush(person), PersonDTO.class);
			return new ResponseEntity<PersonDTO>(result, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonDTO> updatePerson(@RequestBody PersonDTO personDTO) {
		Person person = mapper.map(personDTO, Person.class);
		PersonDTO result = mapper.map(personDao.saveAndFlush(person), PersonDTO.class);
		return new ResponseEntity<PersonDTO>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/list", params = { "page", "size" }, method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> listPaginated(@RequestParam("page") int page, @RequestParam("size") int size) {
		return convertToDtoPage(personDao.findAll(PageRequest.of(page, size)), page);
	}

	@RequestMapping(value = "/findByNamePart", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<PersonDTO>> findByNamePart(@RequestParam("pNamePart") String pNamePart) {
		return convertToDtoList(personDao.findByNamePart(pNamePart));
	}

	@RequestMapping(value = "/get/phone/{phone}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonDTO> getPersonByPhone(@PathVariable("phone") String phone) {
		PersonDTO result = mapper.map(personDao.findByPhone(phone).get(0), PersonDTO.class);
		return new ResponseEntity<PersonDTO>(result, HttpStatus.OK);
	}
}
