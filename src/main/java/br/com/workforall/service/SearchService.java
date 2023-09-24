package br.com.workforall.service;

import br.com.workforall.repository.CompanyRepository;
import br.com.workforall.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private JobRepository jobRepository;

    public List<Object> findJobOrCompanyByParameters(String parameter, Boolean immmigrants,
                                                     Boolean fiftyYears, Boolean deficient,
                                                     Boolean transsexual, Boolean blackOrIndigenous,
                                                     Boolean woman){
        //TODO: fazer consulta por itens de vaga afirmativa
        List<Object> jobList = jobRepository
                .findByTitleStartingWithAndWomanAndImmigrantsAndFiftyYearsAndDeficientAndTranssexualAndBlackIndigenous(
                        parameter, woman, immmigrants, fiftyYears, deficient, transsexual, blackOrIndigenous);

        if(jobList.isEmpty())
            jobList = companyRepository.findByNameStartingWith(parameter);

        return jobList;
    }
}