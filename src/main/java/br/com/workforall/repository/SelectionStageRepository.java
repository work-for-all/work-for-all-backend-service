package br.com.workforall.repository;


import br.com.workforall.model.SelectionStage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SelectionStageRepository extends MongoRepository<SelectionStage, String> {
    SelectionStage findByIdUserAndIdJob(String idUser, String idJob);
}