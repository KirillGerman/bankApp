package ru.meshgroup.bankApplication.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmailUpdateDto {


    @Email
    private String email;

    @Email
    private String currentEmail;

    @Email
    private String updateEmail;

}
