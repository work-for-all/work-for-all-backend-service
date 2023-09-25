package br.com.workforall.service;

import br.com.workforall.model.Job;
import br.com.workforall.repository.CompanyRepository;
import br.com.workforall.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private JobRepository jobRepository;

    public List<Job> findJob(String title, Boolean immigrants,
                             Boolean fiftyYears, Boolean deficient,
                             Boolean transsexual, Boolean blackIndigenous,
                             Boolean woman){

        Query query = new Query();
        query.addCriteria(Criteria.where("title").regex("^" + title));

        if (immigrants) {
            query.addCriteria(Criteria.where("immigrants").is(true));
        }

        if (fiftyYears) {
            query.addCriteria(Criteria.where("fiftyYears").is(true));
        }

        if (deficient) {
            query.addCriteria(Criteria.where("deficient").is(true));
        }

        if (transsexual) {
            query.addCriteria(Criteria.where("transsexual").is(true));
        }

        if (woman) {
            query.addCriteria(Criteria.where("woman").is(true));
        }

        if (blackIndigenous) {
            query.addCriteria(Criteria.where("blackIndigenous").is(true));
        }

        return mongoTemplate.find(query, Job.class);
    }
}