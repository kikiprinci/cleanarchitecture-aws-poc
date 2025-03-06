package kikiprinci.cleanarchitecture.poc.aws.application.usecases;

import java.util.List;

import org.springframework.stereotype.Service;

import kikiprinci.cleanarchitecture.poc.aws.application.PaginatedResult;
import kikiprinci.cleanarchitecture.poc.aws.application.dtos.CarDto;
import kikiprinci.cleanarchitecture.poc.aws.domain.entities.Car;
import kikiprinci.cleanarchitecture.poc.aws.domain.mappers.CarMapper;
import kikiprinci.cleanarchitecture.poc.aws.domain.repositories.CarRepository;

@Service
public class GetCarUseCase {
    private final CarRepository carRepository;
    private final CarMapper<CarDto> carMapper;

    public GetCarUseCase(CarRepository carRepository, CarMapper<CarDto> carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    public CarDto getById(String id) {
        Car car = carRepository.findById(id);
        return carMapper.fromDomain(car);
    }

    public PaginatedResult<CarDto> getAll(int limit, String lastEvaluatedKey) {
        PaginatedResult<Car> result = carRepository.findAll(limit, lastEvaluatedKey);
        return new PaginatedResult<CarDto>(
            carMapper.fromDomain(result.getItems()),
            result.getLastEvaluatedKey()
        );
    }

}