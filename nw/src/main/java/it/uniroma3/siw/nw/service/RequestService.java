package it.uniroma3.siw.nw.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.nw.model.Request;
import it.uniroma3.siw.nw.repository.RequestRepository;

@Service
public class RequestService {
	
	@Autowired
	protected RequestRepository requestRepository;
	
	/**
	 * This method retrieves a Request from the DB based on its ID.
	 * @param id the id of the Request to retrieve from the DB
	 * @return the retrieved Request, or null if no Request with the passed ID could be found in the DB
	 */
	@Transactional
	public Request getRichiesta(Long id) {
		Optional<Request> result = this.requestRepository.findById(id);
		return result.orElse(null);
	}

	/**
	 * This method saves a Request in the DB.
	 * @param request the Request to save into the DB
	 * @return the saved Request
	 */
	@Transactional
	public Request saveRichiesta(Request request) { 
		return this.requestRepository.save(request);
	}

	/**
	 * This method deletes a Request from the DB.
	 * @param request the Request to delete from DB
	 */
	@Transactional
	public void deleteRichiesta(Request request) {
		this.requestRepository.delete(request);
	}
}