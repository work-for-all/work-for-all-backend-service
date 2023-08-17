package br.com.workforall.controller;

import br.com.workforall.exception.EntityNotFoundException;
import br.com.workforall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário não cadastrada!");
        }
    }
}
