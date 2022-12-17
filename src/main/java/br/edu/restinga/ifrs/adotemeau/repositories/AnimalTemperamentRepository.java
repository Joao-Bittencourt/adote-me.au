package br.edu.restinga.ifrs.adotemeau.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.restinga.ifrs.adotemeau.models.AnimalTemperament;

@Repository
public interface AnimalTemperamentRepository extends JpaRepository<AnimalTemperament, Long>{
    
}
