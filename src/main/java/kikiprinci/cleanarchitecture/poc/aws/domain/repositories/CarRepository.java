package kikiprinci.cleanarchitecture.poc.aws.domain.repositories;

import java.util.List;

import kikiprinci.cleanarchitecture.poc.aws.domain.entities.Car;

public interface CarRepository {
    Car findById(String id);
    List<Car> findAll();
    void save(Car car);
    void update(Car car);
}
