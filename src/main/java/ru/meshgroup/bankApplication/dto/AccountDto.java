package ru.meshgroup.bankApplication.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountDto {

    @JsonIgnore
    private Long id;
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal balance;

    public AccountDto(BigDecimal balance) {
        this.balance = balance;
    }

    public AccountDto(Long id) {
        this.id = id;
    }
}
