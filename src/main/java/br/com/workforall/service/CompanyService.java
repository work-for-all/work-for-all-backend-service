package br.com.workforall.service;

import br.com.workforall.exception.RegisterLoginException;
import br.com.workforall.exception.EntityNotFoundException;
import br.com.workforall.model.Company;
import br.com.workforall.model.auth.CompanyAuthentication;
import br.com.workforall.model.dto.CompanyDto;
import br.com.workforall.repository.CompanyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ModelMapper modelMapper;

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Company processCompanyRegister(CompanyDto companyDto) throws EntityNotFoundException {
        Optional<Company> companyOptional = companyRepository.findByCnpj(companyDto.getCnpj());

        if(companyOptional.isPresent())
            throw new RegisterLoginException("CNPJ já cadastrado!");

        Company company = modelMapper.map(companyDto, Company.class);

        String passwordEncoded = new BCryptPasswordEncoder().encode(company.getPassword());
        company.setPassword(passwordEncoded);

        companyRepository.save(company);
        return company;
    }

    public Company processCompanyLogin(CompanyAuthentication companyAuthentication) throws RegisterLoginException, EntityNotFoundException {
        Company company = findCompany(companyAuthentication.getCnpj());

        String passwordEncodedDb = company.getPassword();
        PasswordService.validatePasswordLogin(companyAuthentication.getPassword(), passwordEncodedDb);
        return company;
    }

    public Company processCompanyUpdate(CompanyDto companyDto) throws EntityNotFoundException {
        Company company = findCompany(companyDto.getCnpj());

        company.setName(companyDto.getName());
        company.setDescription(companyDto.getDescription());
        company.setSector(companyDto.getSector());
        company.setSize(companyDto.getSize());
        company.setType(companyDto.getType());
        company.setSlogan(companyDto.getSlogan());
        return companyRepository.save(company);
    }

    public Company findCompany(String cnpj) throws EntityNotFoundException {
        Optional<Company> companyOptional = companyRepository.findByCnpj(cnpj);

        if(companyOptional.isPresent())
            return companyOptional.get();
        throw new EntityNotFoundException("CNPJ não cadastrado!");
    }

    public List<Company> findAllCompanies(){
        return companyRepository.findAll();
    }
}
