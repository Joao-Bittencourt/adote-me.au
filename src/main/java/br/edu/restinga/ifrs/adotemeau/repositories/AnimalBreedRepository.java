package br.edu.restinga.ifrs.adotemeau.repositories;

import br.edu.restinga.ifrs.adotemeau.models.AnimalBreed;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalBreedRepository extends JpaRepository<AnimalBreed, Long> {

    List<AnimalBreed> findByAnimalFamilyTypeContaining(String fanimalFamilyType);

    List<AnimalBreed> findAllByActive(Boolean active);
}
