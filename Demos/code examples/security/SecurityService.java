package com.frantishex.lwr.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.frantishex.lwr.model.Lawsuite;
import com.frantishex.lwr.model.Person;
import com.frantishex.lwr.model.User;
import com.frantishex.lwr.service.BaseService;
import com.frantishex.lwr.service.PersonRepository;

@Service("securityService")
public class SecurityService extends BaseService implements UserDetailsService {

	// @Autowired
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Autowired
	private UserRepository userDao;

	@Autowired
	private PersonRepository personRepository;

	public void register(String userName, String password, String names, String email, String phone) {
		User u = new User();
		u.setUsername(userName);
		u.setNames(names);
		u.setEmail(email);
		u.setPhone(phone);
		u.setPassword(passwordEncoder.encode(password));
		getEm().persist(u);
	}

	public List<User> list() throws UsernameNotFoundException {
		return userDao.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return getEm().createQuery("Select u from User u where u.username = :pUsername", User.class).setParameter("pUsername", username).getSingleResult();
	}

	public void changePassword(String username, String password) {
		User u = getEm().createQuery("Select u from User u where u.username = :pUsername", User.class).setParameter("pUsername", username).getSingleResult();
		u.setPassword(passwordEncoder.encode(password));
	}

	public void deleteUnusedPersons(List<Lawsuite> listLawsuites, List<Person> listPersons) {
		for (Person unusedPerson : getUnusedPersons(listLawsuites, listPersons)) {
			personRepository.delete(unusedPerson);
		}
	}

	public List<Person> getUnusedPersons(List<Lawsuite> listLawsuites, List<Person> listPersons) {
		List<Person> unusedPersons = new ArrayList<Person>();
		boolean isUsed = false;
		for (Person p : listPersons) {
			isUsed = false;
			for (Lawsuite l : listLawsuites) {
				if (l.getHelpersList().contains(p)) {
					isUsed = true;
				} else if (l.getClaimentList().contains(p)) {
					isUsed = true;
				} else if (l.getDefendantList().contains(p)) {
					isUsed = true;
				}
			}
			if (!isUsed) {
				unusedPersons.add(p);
			}
		}
		return unusedPersons;
	}
	
	public List<Person> getUsedPersons(List<Lawsuite> listLawsuites, List<Person> listPersons) {
		List<Person> unusedPersons = new ArrayList<Person>();
		boolean isUsed = false;
		for (Person p : listPersons) {
			isUsed = false;
			for (Lawsuite l : listLawsuites) {
				if (l.getHelpersList().contains(p)) {
					isUsed = true;
				} else if (l.getClaimentList().contains(p)) {
					isUsed = true;
				} else if (l.getDefendantList().contains(p)) {
					isUsed = true;
				}
			}
			if (isUsed) {
				unusedPersons.add(p);
			}
		}
		return unusedPersons;
	}
}
