package br.edu.restinga.ifrs.adotemeau.http.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.restinga.ifrs.adotemeau.http.dtos.UserAddressDTO;
import br.edu.restinga.ifrs.adotemeau.models.UserAddress;
import br.edu.restinga.ifrs.adotemeau.services.UserAddressService;

@RestController
@RequestMapping("/user-address")
public class UserAddressController {

    final UserAddressService userAddressService;

    public UserAddressController(UserAddressService userAddressService) {
        this.userAddressService = userAddressService;
    }

    @GetMapping
    public ResponseEntity<Page<UserAddress>> getAllUserAddresses(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(userAddressService.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Object> createUserAddresses(@Valid @RequestBody UserAddressDTO userAddressDto) {
        var userAddress = new UserAddress();
        BeanUtils.copyProperties(userAddressDto, userAddress);
        return ResponseEntity.status(HttpStatus.CREATED).body(userAddressService.create(userAddress));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserAddress(@PathVariable(value = "id") Long id) {
        Optional<UserAddress> userAddressModelOptional = userAddressService.findById(id);

        if (!userAddressModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User adress not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userAddressModelOptional.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUserAddress(@PathVariable(value = "id") int id, @RequestBody UserAddress userAddress) {
        Optional<UserAddress> userAddressModelOptional = userAddressService.findById(id);
        if (!userAddressModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User adress not found.");
        }

        userAddress.setId(userAddressModelOptional.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(userAddressService.create(userAddress));
    }
}
