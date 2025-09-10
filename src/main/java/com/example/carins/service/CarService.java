package com.example.carins.service;

import com.example.carins.model.Car;
import com.example.carins.repo.CarRepository;
import com.example.carins.repo.InsurancePolicyRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final InsurancePolicyRepository policyRepository;

    public CarService(CarRepository carRepository, InsurancePolicyRepository policyRepository) {
        this.carRepository = carRepository;
        this.policyRepository = policyRepository;
    }

    public List<Car> listCars() {
        return carRepository.findAll();
    }

    public boolean isInsuranceValid(Long carId, LocalDate date) {
        carRepository.findById(carId).orElseThrow(() -> new IllegalArgumentException("carId not found"));
        return policyRepository.existsActiveOnDate(carId, date);
    }

    public Car saveCar(Car car) {
        carRepository.findByVin(car.getVin())
                .ifPresent(existing -> {
                    throw new IllegalArgumentException("VIN already exists: " + car.getVin());
                });
        return carRepository.save(car);
    }
}
