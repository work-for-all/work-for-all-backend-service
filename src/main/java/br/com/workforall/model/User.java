package br.com.workforall.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "User")
public class User {
    @Id
    String id;

    String name;

    String cpf;

    @JsonProperty("birth_date")
    LocalDate birthDate;

    @JsonProperty("marital_status")
    String maritalStatus;

    String ethnicity;

    Boolean disability;

    @JsonProperty("disability_description")
    String disabilityDescription;

    String gender;

    @JsonProperty("sexual_orientation")
    String sexualOrientation;
}
