package br.com.workforall.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class JobDto {
    String cnpj;

    String title;

    @JsonProperty("number_jobs")
    Integer numberJobs;

    @JsonProperty("type_contract")
    String typeContract;

    @JsonProperty("way_working")
    String wayWorking;

    Double salary;

    @JsonProperty("occupation_area")
    String occupationArea;

    String level;

    String position;

    String description;

    String responsability;

    String requirements;

    String benefits;

    String local;

    boolean neuro;

    boolean transsexual;

    @JsonProperty("black_or_indigenous")
    boolean blackIndigenous;

    boolean deficient;

    @JsonProperty("fifty_years_or_more")
    boolean fiftyYears;
}
