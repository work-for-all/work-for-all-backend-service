package br.com.workforall.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Data
public class UserDto {
    @Email
    String email;

    String name;

    @CPF
    String cpf;

    @JsonProperty("birth_date")
    LocalDate birthDate;

    @JsonProperty("marital_status")
    String maritalStatus;

    String ethnicity;

    String gender;

    String password;

    boolean deficient;
}