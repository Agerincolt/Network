package it.uniroma3.siw.nw.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.nw.model.Quote;

@Repository
public interface QuoteRepository extends CrudRepository <Quote, Long> {

}
