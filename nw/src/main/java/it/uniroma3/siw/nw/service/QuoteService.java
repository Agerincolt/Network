package it.uniroma3.siw.nw.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.nw.model.Quote;
import it.uniroma3.siw.nw.repository.QuoteRepository;

@Service
public class QuoteService {
	
	@Autowired
	protected QuoteRepository quoteRepository;
	
	/**
	 * This method retrieves a Quote from the DB based on its ID.
	 * @param id the id of the Quote to retrieve from the DB
	 * @return the retrieved Quote, or null if no Quote with the passed ID could be found in the DB
	 */
	@Transactional
	public Quote getQuote(Long id) {
		Optional<Quote> result = this.quoteRepository.findById(id);
		return result.orElse(null);
	}

	/**
	 * This method saves a Quote in the DB.
	 * @param quote the Quote to save into the DB
	 * @return the saved Quote
	 */
	@Transactional
	public Quote saveQuote(Quote quote) { 
		return this.quoteRepository.save(quote);
	}

	/**
	 * This method deletes a Quote from the DB.
	 * @param quote the Quote to delete from DB
	 */
	@Transactional
	public void deleteQuote(Quote quote) {
		this.quoteRepository.delete(quote);
	}
}