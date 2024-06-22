package com.itec.xchangeservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter

public class ExchangeRateDTO {

    @NotBlank(message = "Base currency cannot be blank")
    private String baseCurrency;

    @NotBlank(message = "Target currency cannot be blank")
    private String targetCurrency;

    @NotBlank(message = "Exchange rate cannot be blank")
    private String exchangeRate;

    @NotBlank(message = "Date cannot be blank")
    private LocalDateTime timestamp;

}
