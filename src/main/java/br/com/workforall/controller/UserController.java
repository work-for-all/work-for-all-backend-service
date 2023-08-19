package br.com.workforall.controller;

import br.com.workforall.exception.EntityNotFoundException;
import br.com.workforall.exception.RegisterLoginException;
import br.com.workforall.model.User;
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
}
