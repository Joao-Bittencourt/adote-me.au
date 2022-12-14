package br.edu.restinga.ifrs.adotemeau.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.restinga.ifrs.adotemeau.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    boolean existsByEmail(String email);
    
    // findByEmail(String email);
}
