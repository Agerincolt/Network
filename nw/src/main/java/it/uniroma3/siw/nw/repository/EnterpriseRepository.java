package it.uniroma3.siw.nw.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import it.uniroma3.siw.nw.model.Enterprise;

@Repository
public interface EnterpriseRepository extends CrudRepository <Enterprise, Long>{

}
