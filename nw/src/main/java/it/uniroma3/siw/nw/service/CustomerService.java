package it.uniroma3.siw.nw.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.nw.model.Customer;
import it.uniroma3.siw.nw.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	protected CustomerRepository customerRepository;
	
	/**
	 * This method retrieves a Customer from the DB based on its ID.
	 * @param id the id of the Customer to retrieve from the DB
	 * @return the retrieved Customer, or null if no Customer with the passed ID could be found in the DB
	 */
	@Transactional
	public Customer getCustomer(Long id) {
		Optional<Customer> result = this.customerRepository.findById(id);
		return result.orElse(null);
	}

	/**
	 * This method saves a Customer in the DB.
	 * @param customer the Customer to save into the DB
	 * @return the saved Customer
	 */
	@Transactional
	public Customer saveCustomer(Customer customer) { 
		return this.customerRepository.save(customer);
	}

	/**
	 * This method deletes a Customer from the DB.
	 * @param customer the Customer to delete from DB
	 */
	@Transactional
	public void deleteCustomer(Customer customer) {
		this.customerRepository.delete(customer);
	}
}