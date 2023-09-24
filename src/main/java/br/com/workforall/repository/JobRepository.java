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

    List<Object> findByWoman(Boolean woman);

    List<Object> findByFiftyYears(Boolean fiftyYears);

    List<Object> findByDeficient(Boolean deficient);

    List<Object> findByBlackIndigenous(Boolean fiftyYears);

    List<Object> findByTranssexual(Boolean transsexual);

    List<Object> findByImmigrants(Boolean transsexual);
}