package it.uniroma3.siw.nw.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.nw.model.Credentials;

/**
 * This interface is a CrudRepository for repository operations on Credentials.
 * 
 * @see Credentials
 * @author vfabi
 */

@Repository
public interface CredentialsRepository extends CrudRepository <Credentials, Long>{
	
	/**
	 * Retrieve Credentials by username
	 * @param username the username of the Credentials to retrieve
	 * @return an Optional for the Credentials with the passed username
	 */
	public Optional<Credentials> findByUsername(String username);

}