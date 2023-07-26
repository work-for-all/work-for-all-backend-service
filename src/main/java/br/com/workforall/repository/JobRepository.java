package br.com.workforall.repository;

import br.com.workforall.model.Job;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JobRepository extends MongoRepository<Job, String> {
    List<Job> findByCnpj(String cnpj);
}
