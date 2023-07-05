package br.com.workforall.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Company")
public class Company {
    @Id
    String id;

    String name;

    String cnpj;

    String password;

    String sector;

    String size;

    String type;

    String slogan;

    String description;
}
