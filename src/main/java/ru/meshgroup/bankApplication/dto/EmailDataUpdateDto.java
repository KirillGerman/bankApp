package ru.meshgroup.bankApplication.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmailDataUpdateDto {

    @Schema(example = "test@mail.com" )
    @Email
    @NotNull
    private String currentEmail;

    @Schema(example = "test2@mail.com" )
    @Email
    @NotNull
    private String updateEmail;

}
