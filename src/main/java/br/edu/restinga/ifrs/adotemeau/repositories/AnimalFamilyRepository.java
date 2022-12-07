package br.edu.restinga.ifrs.adotemeau.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.restinga.ifrs.adotemeau.models.AnimalFamily;

@Repository
public interface AnimalFamilyRepository extends JpaRepository<AnimalFamily, Long>{

}
