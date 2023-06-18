package ru.meshgroup.bankApplication.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransferRequest {

    @Schema(example = "2" )
    @NotNull
    private Long receiverId;

    @Schema(example = "100.0" )
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal amount;

}
