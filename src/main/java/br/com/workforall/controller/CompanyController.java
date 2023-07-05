package br.com.workforall.controller;

import br.com.workforall.model.dto.CompanyDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @GetMapping("/list")
    public String getCompanies() {
        return "teste";
    }

    @PostMapping
    public CompanyDto postCompany(@RequestBody @Valid CompanyDto companyDto) {


        return companyDto;
    }
}
