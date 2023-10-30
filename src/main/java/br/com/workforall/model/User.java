package br.com.workforall.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@Document(collection = "User")
public class User {
    @Id
    String id;

    String name;

    String cpf;

    @JsonProperty("birth_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    LocalDate birthDate;

    @JsonProperty("marital_status")
    String maritalStatus;

    String ethnicity;

    String gender;

    String password;

    String email;

    boolean deficient;

    List<String> jobs;
}