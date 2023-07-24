package br.com.workforall.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "Job")
public class Job {
    @Id
    String id;

    @Indexed(unique = true)
    String cnpj;

    String title;

    @Field("number_jobs")
    Integer numberJobs;

    @Field("type_contract")
    String typeContract;

    @Field("way_working")
    String wayWorking;

    Double salary;

    @Field("occupation_area")
    String occupationArea;

    String level;

    String position;

    String description;

    String responsability;

    String requirements;

    String benefits;
}
