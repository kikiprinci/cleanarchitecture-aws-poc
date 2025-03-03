package kikiprinci.cleanarchitecture.poc.aws.domain.mappers;

import java.util.List;

import kikiprinci.cleanarchitecture.poc.aws.domain.entities.Car;

public interface CarMapper<T> {
    Car toDomain(T source);
    T fromDomain(Car car);
    List<Car> toDomain(List<T> source);
    List<T> fromDomain(List<Car> car);    
}
