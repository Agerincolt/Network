package it.uniroma3.siw.nw.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;

/*
 * Credentials model the account credentials for a user of the application.
 * It contains data such as the username, the (secured) password and the role of a user.
 */
@Entity
public class Credentials {
	
	public final static String DEFAULT_ROLE	= "DEFAULT";
	public final static String ADMIN_ROLE = "ADMIN";
	
	/**
	 * Unique identifier for this Credential
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	
	@Column(unique = true, nullable = false, length = 100)
	private String username;
	
	@Column(nullable = false, length = 100)
	private String password;
	
	private String role;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Customer customer;
	
	public Credentials() {
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public String getRole() {
		return this.role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
}
