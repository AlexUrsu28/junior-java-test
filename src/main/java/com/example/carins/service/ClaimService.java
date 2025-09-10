package com.example.carins.service;

import com.example.carins.model.Car;
import com.example.carins.model.Claim;
import com.example.carins.repo.CarRepository;
import com.example.carins.repo.ClaimRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class ClaimService {

    private final ClaimRepository claimRepo;
    private final CarRepository carRepo;

    public ClaimService(ClaimRepository claimRepo, CarRepository carRepo) {
        this.claimRepo = claimRepo;
        this.carRepo = carRepo;
    }

    @Transactional
    public Claim createClaim(Long carId, LocalDate date, String description, BigDecimal amount) {
        Car car = carRepo.findById(carId).orElseThrow(() -> new IllegalArgumentException("carId not found"));
        Claim c = new Claim();
        c.setCar(car);
        c.setClaimDate(date);
        c.setDescription(description);
        c.setAmount(amount);
        return claimRepo.save(c);
    }
}
