package ru.meshgroup.bankApplication.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Schema(example = "1000.00" )
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal balance;

    public AccountDto(BigDecimal balance) {
        this.balance = balance;
    }

    public AccountDto(Long id) {
        this.id = id;
    }
}
