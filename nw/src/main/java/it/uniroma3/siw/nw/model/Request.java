package it.uniroma3.siw.nw.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

@Entity
public class Request {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String code;
	@Column(nullable = false)
	private String name;
	private String description;
	@Column(nullable = false)
	private LocalDateTime date;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Enterprise recipient;
	
	@OneToMany(mappedBy = "publicRequest", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<Quote> quotes;
	
	public Request() {
	}
	
	@PrePersist
	protected void onPersist() {
		this.date = LocalDateTime.now();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Enterprise getRecipient() {
		return recipient;
	}

	public void setRecipient(Enterprise recipient) {
		this.recipient = recipient;
	}

	public List<Quote> getQuotes() {
		return quotes;
	}

	public void setQuotes(List<Quote> quotes) {
		this.quotes = quotes;
	}

	public LocalDateTime getDate() {
		return date;
	}

	@Override
	public int hashCode() {
		return this.getCode().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		Request other = (Request) obj;
		return this.getCode().equals(other.getCode());
	}

	@Override
	public String toString() {
		return "Request [code=" + code + ", name=" + name + ", description=" + description + ", date=" + date + "]";
	}
}