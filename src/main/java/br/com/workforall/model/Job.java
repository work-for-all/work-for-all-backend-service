package br.com.workforall.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Document(collection = "Job")
public class Job {
    @Id
    String id;

    String cnpj;

    String title;

    @JsonProperty("number_jobs")
    @Field("number_jobs")
    Integer numberJobs;

    @JsonProperty("type_contract")
    @Field("type_contract")
    String typeContract;

    @JsonProperty("way_working")
    @Field("way_working")
    String wayWorking;

    Double salary;

    @JsonProperty("occupation_area")
    @Field("occupation_area")
    String occupationArea;

    String level;

    String position;

    String description;

    String responsability;

    String requirements;

    String benefits;

    boolean status;

    List<String> candidates;

    String local;
}
