package br.edu.restinga.ifrs.adotemeau.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.restinga.ifrs.adotemeau.models.File;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
    
    Optional<File> findByImgurDeleteId(String imgurDeleteId);
    
    void deleteByImgurDeleteId(String imgurDeleteId);
}
