package br.com.workforall.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProfesionalProfileDto {

    @JsonProperty("id_user")
    String idUser;

    @JsonProperty("institution_name")
    String institutionName;

    @JsonProperty("course_name")
    String courseName;

    @JsonProperty("course_level")
    String courseLevel;

    @JsonProperty("start_date_course")
    String startDateCourse;

    @JsonProperty("end_date_course")
    String endDateCourse;

    @JsonProperty("company_name")
    String companyName;

    @JsonProperty("name_position")
    String namePosition;

    @JsonProperty("start_date_position")
    String startDatePosition;

    @JsonProperty("end_date_position")
    String endDatePosition;
}
