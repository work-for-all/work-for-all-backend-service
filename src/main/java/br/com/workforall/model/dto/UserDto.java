package br.com.workforall.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Data
public class UserDto {
    @NotNull
    @Email
    String email;

    @NotNull
    String name;

    @NotNull
    @CPF
    String cpf;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "ddMMyyyy")
    @JsonProperty("birth_date")
    LocalDate birthDate;

    @JsonProperty("marital_status")
    String maritalStatus;

    String ethnicity;

    String gender;

    @NotNull
    String password;

    boolean deficient;
}