package br.com.workforall.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

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
}
