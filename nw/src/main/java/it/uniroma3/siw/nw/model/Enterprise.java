package it.uniroma3.siw.nw.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Enterprise {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String name;
	
	@Column(nullable = false, unique = true)
	private String vatin;
	
	private String address;
	
	@OneToMany(mappedBy = "enterprise")
	private List<Quote> quotes;
	
	@OneToMany(mappedBy = "recipient", fetch = FetchType.EAGER)
	private List<Request> privateRequest;
	
	public Enterprise() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVatin() {
		return vatin;
	}

	public void setVatin(String vatin) {
		this.vatin = vatin;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Quote> getQuotes() {
		return quotes;
	}

	public void setQuotes(List<Quote> quotes) {
		this.quotes = quotes;
	}

	public List<Request> getPrivateRequest() {
		return privateRequest;
	}

	public void setPrivateRequest(List<Request> privateRequest) {
		this.privateRequest = privateRequest;
	}

	@Override
	public int hashCode() {
		return this.getName().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		Enterprise other = (Enterprise) obj;
		return this.getName().equals(other.getName());
	}

	@Override
	public String toString() {
		return "Contractor [name=" + name + ", vatin=" + vatin + ", address=" + address + "]";
	}
}