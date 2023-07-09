package br.com.workforall.repository;

import br.com.workforall.model.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CompanyRepository extends MongoRepository<Company, String> {
    Optional<Company> findByCnpj(String cnpj);
}
