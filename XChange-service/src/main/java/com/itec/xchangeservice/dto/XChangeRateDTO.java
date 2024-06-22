package com.itec.xchangeservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter

public class XChangeRateDTO {

    @NotBlank(message = "Base currency cannot be blank")
    @Schema(description = "Base currency", example = "USD")
    private String baseCurrency;

    @NotBlank(message = "Target currency cannot be blank")
    @Schema(description = "Target currency", example = "EUR")
    private String targetCurrency;

}
