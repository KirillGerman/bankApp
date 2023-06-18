package ru.meshgroup.bankApplication.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthDto {

    @Schema(example = "Ivan" )
    private String name;

    @Schema(example = "123456789" )
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 8, max = 500)
    @NotNull
    private String password;

    @Schema(example = "test@mail.com" )
    @Email
    private String email;

    @Schema(example = "7123456789012")
    @Pattern(regexp = "^[0-9]{13}$")
    private String phone;


}
