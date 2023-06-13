package ru.meshgroup.bankApplication.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmailDataDto {

    private Long id;

    @Email
    private String email;

    public EmailDataDto(String email) {
        this.email = email;
    }
}
