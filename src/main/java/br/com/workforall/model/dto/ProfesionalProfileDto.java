package br.com.workforall.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "ddMMyyyy")
    LocalDate startDateCourse;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "ddMMyyyy")
    LocalDate endDateCourse;

    @JsonProperty("company_name")
    String companyName;

    @JsonProperty("name_position")
    String namePosition;

    @JsonProperty("start_date_position")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "ddMMyyyy")
    LocalDate startDatePosition;

    @JsonProperty("end_date_position")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "ddMMyyyy")
    LocalDate endDatePosition;
}
