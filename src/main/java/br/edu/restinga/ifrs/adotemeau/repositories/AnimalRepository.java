package br.edu.restinga.ifrs.adotemeau.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.restinga.ifrs.adotemeau.models.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long>{
    
}
