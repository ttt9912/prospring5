package ch11.service;

import ch11.entity.Car;

import java.util.List;

public interface CarService {
    List<Car> findAll();

    Car save(Car car);

    void updateCarAgeJob();

    boolean isDone();
}
