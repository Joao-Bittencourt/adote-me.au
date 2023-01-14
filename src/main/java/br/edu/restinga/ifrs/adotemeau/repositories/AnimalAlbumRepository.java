package br.edu.restinga.ifrs.adotemeau.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.restinga.ifrs.adotemeau.models.AnimalAlbum;

@Repository
public interface AnimalAlbumRepository extends JpaRepository <AnimalAlbum, Long> {
    List<AnimalAlbum> findByAnimalId(Long animalId);
}
