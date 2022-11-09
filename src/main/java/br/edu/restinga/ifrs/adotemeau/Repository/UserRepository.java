package br.edu.restinga.ifrs.adotemeau.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.restinga.ifrs.adotemeau.Model.EndUser;

@Repository
public interface UserRepository extends JpaRepository<EndUser, Long>{
    
}
