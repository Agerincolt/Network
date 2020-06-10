package it.uniroma3.siw.nw.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.nw.model.Enterprise;
import it.uniroma3.siw.nw.repository.EnterpriseRepository;

@Service
public class EnterpriseService {
	
	@Autowired
	protected EnterpriseRepository enterpriseRepository;
	
	/**
	 * This method retrieves an Enterprise from the DB based on its ID.
	 * @param id the id of the Enterprise to retrieve from the DB
	 * @return the retrieved Enterprise, or null if no Enterprise with the passed ID could be found in the DB
	 */
	@Transactional
	public Enterprise getEnterprise(Long id) {
		Optional<Enterprise> result = this.enterpriseRepository.findById(id);
		return result.orElse(null);
	}

	/**
	 * This method saves an Enterprise in the DB.
	 * @param enterprise the Enterprise to save into the DB
	 * @return the saved Enterprise
	 */
	@Transactional
	public Enterprise saveEnterprise(Enterprise enterprise) { 
		return this.enterpriseRepository.save(enterprise);
	}

	/**
	 * This method deletes a Enterprise from the DB.
	 * @param enterprise the Enterprise to delete from DB
	 */
	@Transactional
	public void deleteEnterprise(Enterprise enterprise) {
		this.enterpriseRepository.delete(enterprise);
	}
}