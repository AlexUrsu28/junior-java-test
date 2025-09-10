package com.example.carins.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Claim {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Car car;

    @NotNull
    @Column(nullable = false)
    private LocalDate claimDate;

    @Column(length = 500)
    private String description;

    @Column(precision = 14, scale = 2)
    private BigDecimal amount;

    public Long getId() { return id; }
    public Car getCar() { return car; }
    public void setCar(Car car) { this.car = car; }
    public LocalDate getClaimDate() { return claimDate; }
    public void setClaimDate(LocalDate claimDate) { this.claimDate = claimDate; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
}
