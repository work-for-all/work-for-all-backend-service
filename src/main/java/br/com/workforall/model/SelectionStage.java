package br.com.workforall.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "SelectionStage")
public class SelectionStage {
    @Id
    String id;

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
