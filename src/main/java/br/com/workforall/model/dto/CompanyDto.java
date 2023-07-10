package br.com.workforall.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

@Data
public class CompanyDto {

    String name;

    @CNPJ
    String cnpj;

    String password;

    String sector;

    String size;

    String type;

    String slogan;

    String description;
}
