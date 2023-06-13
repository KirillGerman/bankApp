package ru.meshgroup.bankApplication.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Validated
public class PhoneDataDto {

    @JsonIgnore
    private Long id;

    @Pattern(regexp = "^[0-9]{13}$")
    private String phone;

    public PhoneDataDto(String phone) {
        this.phone = phone;
    }
}
