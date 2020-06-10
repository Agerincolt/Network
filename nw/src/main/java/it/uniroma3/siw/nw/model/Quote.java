package it.uniroma3.siw.nw.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

@Entity
public class Quote {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String code;
	private String description;
	private LocalDateTime date;

	@ManyToOne(fetch = FetchType.LAZY)
	private Enterprise enterprise;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Request publicRequest;
	
	public Quote() {
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	public Request getPublicRequest() {
		return publicRequest;
	}

	public void setPublicRequest(Request publicRequest) {
		this.publicRequest = publicRequest;
	}

	public LocalDateTime getDate() {
		return date;
	}

	@Override
	public int hashCode() {
		return this.code.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		Quote other = (Quote) obj;
		return this.getCode().equals(other.getCode());
	}

	@Override
	public String toString() {
		return "Quote [code=" + code + ", description=" + description + ", date=" + date + ", enterprise=" + enterprise + "]";
	}
}