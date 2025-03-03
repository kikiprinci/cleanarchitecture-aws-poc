package kikiprinci.cleanarchitecture.poc.aws.application.usecases;

import org.springframework.stereotype.Service;

import kikiprinci.cleanarchitecture.poc.aws.application.dtos.CarDto;
import kikiprinci.cleanarchitecture.poc.aws.domain.entities.Car;
import kikiprinci.cleanarchitecture.poc.aws.domain.mappers.CarMapper;
import kikiprinci.cleanarchitecture.poc.aws.domain.repositories.CarRepository;

import com.github.f4b6a3.uuid.UuidCreator;

@Service
public class CreateCarUseCase {
    private final CarRepository carRepository;
    private final CarMapper<CarDto> carMapper;

    public CreateCarUseCase(CarRepository carRepository, CarMapper<CarDto> carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    public CarDto execute(CarDto carDto) {
        carDto.setId(generateId());
        Car car = carMapper.toDomain(carDto);
        carRepository.save(car);
        return carMapper.fromDomain(car);
    }

    private String generateId() {
        return UuidCreator.getTimeOrderedEpoch().toString();
    }
}