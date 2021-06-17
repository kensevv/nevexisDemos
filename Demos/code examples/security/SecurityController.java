package com.frantishex.lwr.security;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.frantishex.lwr.controller.BaseController;
import com.frantishex.lwr.controller.dto.UserDTO;
import com.frantishex.lwr.model.Lawsuite;
import com.frantishex.lwr.model.Person;
import com.frantishex.lwr.service.LawsuiteRepository;
import com.frantishex.lwr.service.PersonRepository;

@RestController("securityController")
@RequestMapping("/security")
public class SecurityController extends BaseController {

	@Autowired
	private SecurityService securityService;

	@Autowired
	private LawsuiteRepository lawsuiteRepository;

	@Autowired
	private PersonRepository personRepository;

	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@RequestMapping(value = "/register", method = RequestMethod.POST, params = { "username", "password" })
	public ResponseEntity<SecurityResponseDTO> registerUser(String username, String password, String names, String email, String phoneNumber) {
		try {
			securityService.register(username, password, names, email, phoneNumber);
			return new ResponseEntity<SecurityResponseDTO>(new SecurityResponseDTO("User Created"), HttpStatus.OK);
		} catch (Throwable e) {
			e.printStackTrace();
			return new ResponseEntity<SecurityResponseDTO>(new SecurityResponseDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST, params = { "username", "password" })
	public ResponseEntity<SecurityResponseDTO> changePassword(String username, String password) {
		try {
			securityService.changePassword(username, password);
			return new ResponseEntity<SecurityResponseDTO>(new SecurityResponseDTO("Password Changed"), HttpStatus.OK);
		} catch (Throwable e) {
			e.printStackTrace();
			return new ResponseEntity<SecurityResponseDTO>(new SecurityResponseDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
	@RequestMapping(value = "/listUsers", method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> listUsers() {
		return convertToDtoList(securityService.list());
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@RequestMapping(value = "/deletePersons", method = RequestMethod.DELETE)
	public ResponseEntity<SecurityResponseDTO> deleteUnusedPersons() {
		try {
			List<Lawsuite> listLawsuites = lawsuiteRepository.findAll();
			List<Person> listPersons = personRepository.findAll();
			securityService.deleteUnusedPersons(listLawsuites, listPersons);
			return new ResponseEntity<SecurityResponseDTO>(new SecurityResponseDTO("Pesrons Deleted Successfully!"), HttpStatus.OK);
		} catch (Throwable e) {
			e.printStackTrace();
			return new ResponseEntity<SecurityResponseDTO>(new SecurityResponseDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@RequestMapping(value = "/getUnusedPersons", method = RequestMethod.GET)
	public ResponseEntity<?> getUnusedPersons() {
		try {
			List<Lawsuite> listLawsuites = lawsuiteRepository.findAll();
			List<Person> listPersons = personRepository.findAll();

			return new ResponseEntity<List<Person>>(securityService.getUnusedPersons(listLawsuites, listPersons), HttpStatus.OK);
		} catch (Throwable e) {
			e.printStackTrace();
			return new ResponseEntity<SecurityResponseDTO>(new SecurityResponseDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@RequestMapping(value = "/getUsedPersons", method = RequestMethod.GET)
	public ResponseEntity<?> getUsedPersons() {
		try {
			List<Lawsuite> listLawsuites = lawsuiteRepository.findAll();
			List<Person> listPersons = personRepository.findAll();

			return new ResponseEntity<List<Person>>(securityService.getUsedPersons(listLawsuites, listPersons), HttpStatus.OK);
		} catch (Throwable e) {
			e.printStackTrace();
			return new ResponseEntity<SecurityResponseDTO>(new SecurityResponseDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET, params = { "username" })
	public ResponseEntity<?> login(String username) {
		try {
			return new ResponseEntity<>(securityService.loadUserByUsername(username), HttpStatus.OK);
		} catch (Throwable e) {
			e.printStackTrace();
			return new ResponseEntity<SecurityResponseDTO>(new SecurityResponseDTO(e), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
