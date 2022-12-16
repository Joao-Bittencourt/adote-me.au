package br.edu.restinga.ifrs.adotemeau.repositories;

import br.edu.restinga.ifrs.adotemeau.models.AnimalFamily;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AnimalFamilyRepository extends JpaRepository<AnimalFamily, Long> {

}
