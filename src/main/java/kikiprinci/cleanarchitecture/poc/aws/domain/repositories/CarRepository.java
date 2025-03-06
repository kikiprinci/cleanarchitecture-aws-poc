package kikiprinci.cleanarchitecture.poc.aws.domain.repositories;

import java.util.List;
import java.util.Map;

import kikiprinci.cleanarchitecture.poc.aws.application.PaginatedResult;
import kikiprinci.cleanarchitecture.poc.aws.domain.entities.Car;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

public interface CarRepository {
    Car findById(String id);
    PaginatedResult<Car> findAll(int limit, String lastEvaluatedKey);
    void save(Car car);
    void update(Car car);
    void delete(Car car);
}
