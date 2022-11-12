package br.edu.restinga.ifrs.adotemeau.Service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.restinga.ifrs.adotemeau.Model.User;
import br.edu.restinga.ifrs.adotemeau.Repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User create(User user) {
        return userRepository.save(user);  
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Transactional
    public User update(User updateUser, Long id) {
        Optional<User> opt = this.userRepository.findById(id);
        User user = opt.get();

        if(updateUser.getName() != null)
            user.setName(updateUser.getName());
        if(updateUser.getPassword() != null)
            user.setPassword(updateUser.getPassword());
        if(updateUser.getEmail() != null)
            user.setEmail(updateUser.getEmail());
        
        this.userRepository.save(user);
        return user;
    }
    @Transactional
    public String delete(Long id) {
        Optional<User> optional = this.userRepository.findById(id);
        User user = optional.get();
        this.userRepository.deleteById(id);
        return String.format("Usuário foi deletado com sucesso", user.getName());
    }
}
