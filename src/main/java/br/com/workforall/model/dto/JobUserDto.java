package br.com.workforall.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class JobUserDto {
    @JsonProperty("id_user")
    String idUser;
}
