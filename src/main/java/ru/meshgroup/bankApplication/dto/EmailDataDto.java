package ru.meshgroup.bankApplication.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmailDataDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Schema(example = "test@mail.com" )
    @Email
    private String email;

    public EmailDataDto(String email) {
        this.email = email;
    }
}
