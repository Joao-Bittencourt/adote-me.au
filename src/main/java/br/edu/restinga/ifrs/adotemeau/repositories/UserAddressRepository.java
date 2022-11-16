package br.edu.restinga.ifrs.adotemeau.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.restinga.ifrs.adotemeau.models.UserAddress;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Long>{
    
    @Query(value = "SELECT user_addresses.* FROM user_addresses" +
            "WHERE user_addresses.user_id = :userId",
            nativeQuery = true)
    Optional<UserAddress> findByUserId(@Param("userId") Long userId);
}