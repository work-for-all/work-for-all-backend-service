package br.com.workforall.repository;

import br.com.workforall.model.Job;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JobRepository extends MongoRepository<Job, String> {
    List<Job> findByCnpj(String cnpj);

    List<Object> findByTitleStartingWithAndWomanAndImmigrantsAndFiftyYearsAndDeficientAndTranssexualAndBlackIndigenous(
            String title,
            Boolean woman,
            Boolean immigrants,
            Boolean fiftyYears,
            Boolean deficient,
            Boolean transsexual,
            Boolean blackIndigenous
    );
}