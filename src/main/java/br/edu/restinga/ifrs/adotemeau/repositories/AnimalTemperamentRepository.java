package br.edu.restinga.ifrs.adotemeau.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.restinga.ifrs.adotemeau.models.AnimalTemperament;

@Repository
public interface AnimalTemperamentRepository extends JpaRepository<AnimalTemperament, Long>{

    List<AnimalTemperament> findAllByActive(Boolean status);
}
