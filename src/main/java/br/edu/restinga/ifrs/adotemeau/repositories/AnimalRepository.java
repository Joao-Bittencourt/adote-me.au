package br.edu.restinga.ifrs.adotemeau.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.restinga.ifrs.adotemeau.models.Animal;
import br.edu.restinga.ifrs.adotemeau.models.User;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long>{
    
    List<Animal> findByUser_Id(Long userId);

    List<Animal> findByTemperament_Id(Long id);

    List<Animal> findByFamily_Id(Long id);

    List<Animal> findByBreed_Id(Long id);
}
