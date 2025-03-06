package kikiprinci.cleanarchitecture.poc.aws.application.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import kikiprinci.cleanarchitecture.poc.aws.application.dtos.CarDto;
import kikiprinci.cleanarchitecture.poc.aws.domain.entities.Car;
import kikiprinci.cleanarchitecture.poc.aws.domain.mappers.CarMapper;

@Component
public class CarMapperImpl implements CarMapper<CarDto> {
    @Override
    public Car toDomain(CarDto dto) {
        return new Car(
                dto.getId(),
                dto.getBrand(),
                dto.getModel(),
                dto.getVersion(),
                dto.getLicensePlate(),
                dto.isAvailable(),
                dto.getManufacterYear());
    }

    @Override
    public CarDto fromDomain(Car car) {
        return new CarDto(
                car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getVersion(),
                car.getLicensePlate(),
                car.isAvailable(),
                car.getManufacterYear(),
                car.getCreationTimeStamp(),
                car.getCreationDate());
    }

    @Override
    public List<Car> toDomain(List<CarDto> source) {
        return source.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarDto> fromDomain(List<Car> source) {
        return source.stream()
                .map(this::fromDomain)
                .collect(Collectors.toList());
    }
}