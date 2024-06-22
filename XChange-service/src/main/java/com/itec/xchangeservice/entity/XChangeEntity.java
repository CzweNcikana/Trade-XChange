package com.itec.xchangeservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "currencies")
@Getter
@Setter


public class XChangeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, precision = 0)
    private Long id;

    @Column(name = "status", nullable = true)
    private String status;

    @Column(name = "From_currency", nullable = false)
    private String baseCurrency;

    @Column(name = "target_currency", nullable = false)
    private String targetCurrency;

    @Column(name = "exchange_rate", nullable = false)
    private double exchangeRate;

    @Column(name = "previous_exchange_rate", nullable = false)
    private double previousExchangeRate;

    @Column(name = "last_Update")
    private LocalDateTime lastUpdated;

}
