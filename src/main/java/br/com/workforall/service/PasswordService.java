package br.com.workforall.service;

import br.com.workforall.exception.RegisterLoginException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordService {

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static void validatePasswordLogin(String password, String passwordEncoded){
        if(!passwordEncoder.matches(password, passwordEncoded)) {
            throw new RegisterLoginException("Senha incorreta!");
        }
    }
}
