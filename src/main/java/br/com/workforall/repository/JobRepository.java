package br.com.workforall.repository;

import br.com.workforall.model.Job;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JobRepository extends MongoRepository<Job, String> {
}
