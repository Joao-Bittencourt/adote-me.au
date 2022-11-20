package br.edu.restinga.ifrs.adotemeau.services;

import br.edu.restinga.ifrs.adotemeau.models.UserAddress;
import br.edu.restinga.ifrs.adotemeau.repositories.UserAddressRepository;

import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserAddressService {

    final UserAddressRepository userAddressRepository;

    public UserAddressService(UserAddressRepository userAddressRepository) {
        this.userAddressRepository = userAddressRepository;
    }

    @Transactional
    public UserAddress create(UserAddress userAddress) {
        return userAddressRepository.save(userAddress);
    }

    public Page<UserAddress> findAll(Pageable pageable) {
        return userAddressRepository.findAll(pageable);
    }

    public Optional<UserAddress> findById(long id) {
        return userAddressRepository.findById(id);
    }
    
     
    public Optional<UserAddress> findByUserId(long userId) {
        return userAddressRepository.findByUserId(userId);
    }

}
