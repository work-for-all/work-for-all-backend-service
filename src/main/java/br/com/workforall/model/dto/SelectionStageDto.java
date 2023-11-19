package br.com.workforall.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SelectionStageDto {
    @JsonProperty("id_user")
    String idUser;

    @JsonProperty("id_job")
    String idJob;

    @JsonProperty("selection_indicator")
    boolean selectionIndicator;

    @JsonProperty("test_description")
    String testDescription;

    @JsonProperty("interview_description")
    String interviewDescription;

    @JsonProperty("result_indicator")
    boolean resultIndicator;
}
