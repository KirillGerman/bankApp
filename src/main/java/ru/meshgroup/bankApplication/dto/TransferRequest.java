package ru.meshgroup.bankApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransferRequest {

    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal amount;

}
