package br.edu.restinga.ifrs.adotemeau.repositories;

import br.edu.restinga.ifrs.adotemeau.models.AnimalFamily;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalFamilyRepository extends JpaRepository<AnimalFamily, Long> {

}
