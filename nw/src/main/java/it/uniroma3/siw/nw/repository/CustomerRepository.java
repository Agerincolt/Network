package it.uniroma3.siw.nw.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.nw.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository <Customer, Long>{
	
	/**
	 * Retrieve User by its name
	 * @param name the name of the Customer to retrieve
	 * @return an Optional for the Customer with the passed name
	 */
	public Optional<Customer> findByName(String name);

}
