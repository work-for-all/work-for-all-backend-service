package br.com.workforall.service;

import br.com.workforall.exception.EntityNotFoundException;
import br.com.workforall.exception.RegisterLoginException;
import br.com.workforall.model.User;
import br.com.workforall.model.auth.UserAuthentication;
import br.com.workforall.model.dto.UserDto;
import br.com.workforall.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public User processUserRegister(UserDto userDto) throws RegisterLoginException{
        Optional<User> userOptional = userRepository.findByEmail(userDto.getEmail());

        if(userOptional.isPresent())
            throw new RegisterLoginException("E-mail já cadastrado!");

        User user = modelMapper.map(userDto, User.class);

        String passwordEncoded = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(passwordEncoded);

        userRepository.save(user);
        return user;
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public User findUser(String id) throws EntityNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);

        if(userOptional.isPresent()) {
            return userOptional.get();
        }else {
            throw new EntityNotFoundException("Usuário não existente!");
        }
    }

    public User findUserByEmail(String email) throws EntityNotFoundException{
        Optional<User> userOptional = userRepository.findByEmail(email);

        if(userOptional.isPresent())
            return userOptional.get();
        throw new EntityNotFoundException("E-mail não cadastrado!");
    }

    public User proccessUserLogin(UserAuthentication userAuthentication) throws EntityNotFoundException{
        User user = findUserByEmail(userAuthentication.getEmail());

        String passwordEncodedDb = user.getPassword();
        PasswordService.validatePasswordLogin(userAuthentication.getPassword(), passwordEncodedDb);
        return user;
    }
}
