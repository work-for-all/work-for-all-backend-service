package br.com.workforall.service;

import br.com.workforall.exception.LoginException;
import br.com.workforall.exception.RegisterException;
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

    public Company processCompanyRegister(CompanyDto companyDto) throws RegisterException {
        Optional<Company> companyOptional = companyRepository.findByCnpj(companyDto.getCnpj());

        if(companyOptional.isPresent()) {
            throw new RegisterException("CNPJ já cadastrado!");
        }

        Company company = modelMapper.map(companyDto, Company.class);

        String passwordEncoded = new BCryptPasswordEncoder().encode(company.getPassword());
        company.setPassword(passwordEncoded);

        companyRepository.save(company);
        return company;
    }

    public Optional<Company> processCompanyLogin(CompanyAuthentication companyAuthentication) throws LoginException {
        Optional<Company> companyOptional = companyRepository.findByCnpj(companyAuthentication.getCnpj());

        if(companyOptional.isPresent()) {
            Company company = companyOptional.get();
            String passwordEncodedDb = company.getPassword();
            validatePasswordLogin(companyAuthentication.getPassword(), passwordEncodedDb);
            return Optional.of(company);
        }else {
            throw new LoginException("CNPJ não cadastrado!");
        }
    }

    public void validatePasswordLogin(String password, String passwordEncoded){
        if(!passwordEncoder.matches(password, passwordEncoded)) {
            throw new LoginException("Senha incorreta!");
        }
    }

    public Company processUpdateCompany(CompanyDto companyDto) {
        Optional<Company> companyOptional = companyRepository.findByCnpj(companyDto.getCnpj());
        companyOptional.get().setName(companyDto.getName());
        companyOptional.get().setName(companyDto.getName());
        companyOptional.get().setName(companyDto.getName());
        companyOptional.get().setName(companyDto.getName());
        return companyRepository.save(companyOptional.get());
    }
}
