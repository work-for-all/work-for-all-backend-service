package br.com.workforall.service;

import br.com.workforall.exception.LoginException;
import br.com.workforall.exception.CompanyNotFoundException;
import br.com.workforall.model.Company;
import br.com.workforall.model.CompanyAuthentication;
import br.com.workforall.model.dto.CompanyDto;
import br.com.workforall.repository.CompanyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private ModelMapper modelMapper;

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Company processCompanyRegister(CompanyDto companyDto) throws CompanyNotFoundException {
        findCompany(companyDto.getCnpj());

        Company company = modelMapper.map(companyDto, Company.class);

        String passwordEncoded = new BCryptPasswordEncoder().encode(company.getPassword());
        company.setPassword(passwordEncoded);

        companyRepository.save(company);
        return company;
    }

    public Company processCompanyLogin(CompanyAuthentication companyAuthentication) throws LoginException, CompanyNotFoundException {
        Company company = findCompany(companyAuthentication.getCnpj());


        String passwordEncodedDb = company.getPassword();
        validatePasswordLogin(companyAuthentication.getPassword(), passwordEncodedDb);
        return company;
    }

    public void validatePasswordLogin(String password, String passwordEncoded){
        if(!passwordEncoder.matches(password, passwordEncoded)) {
            throw new LoginException("Senha incorreta!");
        }
    }

    public Company processUpdateCompany(CompanyDto companyDto) throws CompanyNotFoundException {
        Company company = findCompany(companyDto.getCnpj());

        company.setName(companyDto.getName());
        company.setDescription(companyDto.getDescription());
        company.setSector(companyDto.getSector());
        company.setSize(companyDto.getSize());
        company.setType(companyDto.getType());
        company.setSlogan(companyDto.getSlogan());
        return companyRepository.save(company);
    }

    public Company findCompany(String cnpj) throws CompanyNotFoundException {
        Optional<Company> companyOptional = companyRepository.findByCnpj(cnpj);

        if(companyOptional.isPresent()) {
            return companyOptional.get();
        }else {
            throw new CompanyNotFoundException("CNPJ não cadastrado!");
        }
    }
}
