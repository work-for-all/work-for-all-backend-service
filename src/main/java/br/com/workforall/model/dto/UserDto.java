package br.com.workforall.model.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UserDto {
    @Email
    String email;

    String password;

    String name;
}
