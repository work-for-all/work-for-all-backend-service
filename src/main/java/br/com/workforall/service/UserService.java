package br.com.workforall.service;

import br.com.workforall.exception.EntityNotFoundException;
import br.com.workforall.exception.RegisterLoginException;
import br.com.workforall.model.Job;
import br.com.workforall.model.User;
import br.com.workforall.model.auth.UserAuthentication;
import br.com.workforall.model.dto.UserDto;
import br.com.workforall.repository.JobRepository;
import br.com.workforall.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRepository jobRepository;

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

    public void proccessUserAddJob(String idJob, String idUser){
        User user = findUser(idUser);

        List<String> listJobs;

        if(user.getJobs() == null){
            listJobs = new ArrayList<>();
        }else{
            listJobs = user.getJobs();
        }
        listJobs.add(idJob);
        user.setJobs(listJobs);

        userRepository.save(user);
    }

    public List<Job> findJobsForUser(String idUser) {
        User user = findUser(idUser);

        if(user.getJobs() == null)
            return null;

        List<Job> jobList = new ArrayList<>();

        for(String idJob : user.getJobs()){
            Job job = jobRepository.findById(idJob).get();
            jobList.add(job);
        }
        return jobList;
    }

    public boolean isUserInJob(String idUser, String job) {
        User user = findUser(idUser);

        if(user.getJobs() == null)
            return false;

        for(String idJob : user.getJobs())
            if(job.equals(idJob))
                return true;
        return false;
    }
}