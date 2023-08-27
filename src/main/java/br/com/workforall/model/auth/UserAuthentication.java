package br.com.workforall.model.auth;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UserAuthentication {
    @Email
    private String email;

    private String password;
}
