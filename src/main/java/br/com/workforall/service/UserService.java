package br.com.workforall.service;

import br.com.workforall.exception.EntityNotFoundException;
import br.com.workforall.model.User;
import br.com.workforall.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findUser(String id) throws EntityNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);

        if(userOptional.isPresent()) {
            return userOptional.get();
        }else {
            throw new EntityNotFoundException("Usuário não existente!");
        }
    }
}
