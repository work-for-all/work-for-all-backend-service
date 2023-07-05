package br.com.workforall.controller;

import br.com.workforall.model.Company;
import br.com.workforall.model.dto.CompanyDto;
import br.com.workforall.repository.CompanyRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/list")
    public String getCompanies() {
        return "teste";
    }

    @PostMapping
    public CompanyDto postCompany(@RequestBody @Valid CompanyDto companyDto) {
        Company company = modelMapper.map(companyDto, Company.class);
        companyRepository.save(company);
        return companyDto;
    }
}
