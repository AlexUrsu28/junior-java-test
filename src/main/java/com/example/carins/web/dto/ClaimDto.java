package com.example.carins.web.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDate;

public record ClaimDto(
        @NotNull LocalDate claimDate,
        String description,
        @NotNull @PositiveOrZero BigDecimal amount
) {}
