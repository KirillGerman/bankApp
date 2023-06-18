package ru.meshgroup.bankApplication.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PhoneUpdateDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Pattern(regexp = "^[0-9]{13}$")
    @NotNull
    private String phone;

}
