package br.com.workforall.model;

import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

@Data
public class CompanyAuthentication {
    @CNPJ
    private String cnpj;
    private String password;
}
