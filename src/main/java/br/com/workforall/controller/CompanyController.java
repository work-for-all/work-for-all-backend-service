package br.com.workforall.controller;

import br.com.workforall.exception.LoginException;
import br.com.workforall.exception.CompanyNotFoundException;
import br.com.workforall.model.Company;
import br.com.workforall.model.CompanyAuthentication;
import br.com.workforall.model.dto.CompanyDto;
import br.com.workforall.repository.CompanyRepository;
import br.com.workforall.service.CompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping("/list")
    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    @PostMapping("/register")
    public ResponseEntity<?> postCompany(@RequestBody @Valid CompanyDto companyDto) {
        try {
            Company company = companyService.processCompanyRegister(companyDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(company);
        }catch (CompanyNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCompany(@RequestBody @Valid CompanyDto companyDto) {
        try {
            Company company = companyService.processUpdateCompany(companyDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(company);
        }catch (CompanyNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginCompany(@RequestBody @Valid CompanyAuthentication companyAuthentication) {
        try {
            Company company = companyService.processCompanyLogin(companyAuthentication);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(company);
        }catch (LoginException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/detail")
    public ResponseEntity<?> detailCompany(String cnpj) {
        try {
            Company company = companyService.findCompany(cnpj);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(company);
        }catch (LoginException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
