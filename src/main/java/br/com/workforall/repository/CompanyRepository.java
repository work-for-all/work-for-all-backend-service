package br.com.workforall.repository;

import br.com.workforall.model.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyRepository extends MongoRepository<Company, Long> {
}
