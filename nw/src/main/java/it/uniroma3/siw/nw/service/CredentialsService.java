package it.uniroma3.siw.nw.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.nw.model.Credentials;
import it.uniroma3.siw.nw.repository.CredentialsRepository;

/**
 * The CredentialsService handles logic regarding Credentials.
 * This mainly consists in retrieving or storing Credentials in the DB.
 * @author vfabi
 *
 */

@Service
public class CredentialsService {
	
	@Autowired
	protected CredentialsRepository credentialsRepository;
	
	/*
	 * Se non avessi annotato il metodo PasswordEncoder con @Bean in AuthConfiguration
	 * Spring con @Autowired mi avrebbe istanziato un passwordEncoder di default, ovvero
	 * uno che non fa niente...
	 */
	/*@Autowired
	protected PasswordEncoder passwordEncoder;
	*/
	
	/**
	 * This method retrieves a Credentials from a DB based on its ID.
	 * @param id the id of the Credentials to retrieve from the DB
	 * @return the retrieved Credentials, or null if no Credentials with the passed ID could be found in the DB
	 */
	@Transactional
	public Credentials getCredentials(Long id) {
		Optional<Credentials> result = this.credentialsRepository.findById(id);
		return result.orElse(null);
	}
	
	/**
	 * This method retrieves a Credentials from a DB based on its "username".
	 * @param username the username of the Credentials to retrieve from the DB
	 * @return the retrieved User, or null if no User with the passed "username" could be found in the DB
	 */
	
	@Transactional
	public Credentials getCredentials(String username) {
		Optional<Credentials> result = this.credentialsRepository.findByUsername(username);
		return result.orElse(null);
	}
	
	/**
	 * This method saves a Credentials in the DB.
	 * @param credentials the Credentials to save into the DB
	 * @return the saved credentials
	 * @throws DataIntegrityViolationException if a Credentials with the same username
	 * 								as the passed Credentials already exists in the DB
	 */
	@Transactional
	public Credentials saveCredentials(Credentials credentials) { 
		credentials.setPassword(credentials.getPassword());
		return this.credentialsRepository.save(credentials); 
	}
	
	/**
	 * This method retrieves all Credentials from the DB.
	 * @return a List with all the retrieved Credentials
	 */
	@Transactional
	public List<Credentials> getAllCredentials() {
		List<Credentials> result = new ArrayList<>();
		Iterable<Credentials> iterable = this.credentialsRepository.findAll();
		for(Credentials credentials : iterable)
			result.add(credentials);
		return result;
	}
	
	/**
	 * This method deletes a Credentials from the DB.
	 * @param credentials the Credentials to delete from DB
	 */
	@Transactional
	public void deleteCredentials(Credentials credentials) {
		this.credentialsRepository.delete(credentials);
	}
}
