package br.com.workforall.controller;

import br.com.workforall.exception.EntityNotFoundException;
import br.com.workforall.exception.RegisterLoginException;
import br.com.workforall.model.Job;
import br.com.workforall.model.User;
import br.com.workforall.model.auth.UserAuthentication;
import br.com.workforall.model.dto.UserDto;
import br.com.workforall.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/quantity")
    public ResponseEntity<?> getQuantityUser(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAllUsers().size());
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<?> getUser(@PathVariable String idUser){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.findUser(idUser));
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não cadastrado!");
        }
    }

    @GetMapping("/jobs/{idUser}")
    public ResponseEntity<?> getJobsForUser(@PathVariable String idUser){
        try {
            List<Job> jobList = userService.findJobsForUser(idUser);
            return ResponseEntity.status(HttpStatus.OK).body(jobList);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/validate-user-in-job")
    public ResponseEntity<?> validateUserInJob(@RequestParam String user, @RequestParam String job) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.isUserInJob(user, job));
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/list")
    public List<User> getUsers(){
        return userService.findAllUsers();
    }

    @PostMapping("/register")
    public ResponseEntity<?> postUser(@RequestBody @Valid UserDto userDto){
        try {
            User user = userService.processUserRegister(userDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }catch (RegisterLoginException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody @Valid UserAuthentication userAuthentication) {
        try {
            User user = userService.proccessUserLogin(userAuthentication);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
        }catch (EntityNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}