package kikiprinci.cleanarchitecture.poc.aws.application.usecases;

import java.util.List;

import org.springframework.stereotype.Service;

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

    public List<CarDto> getAll() {
        // List<CarDto> carDtos = carRepository.findAll();
        // return carMapper.
        return null;
    }
}