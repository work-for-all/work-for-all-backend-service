package br.com.workforall.repository;

import br.com.workforall.model.ProfesionalProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProfesionalProfileRepository extends MongoRepository<ProfesionalProfile, String> {
    Optional<ProfesionalProfile> findByIdUser(String idUser);
}
