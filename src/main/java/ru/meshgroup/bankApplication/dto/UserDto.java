package ru.meshgroup.bankApplication.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDto {

    private Long id;

    private String name;

    private LocalDate dateOfBirth;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 8, max = 500)
    @NotNull
    private String password;

    @Valid
    private AccountDto account;

    @Valid
    private List<EmailDataDto> emails;

    @Valid
    private List<PhoneDataDto> phones;

    public UserDto(String name, LocalDate dateOfBirth, String password) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
    }

}


