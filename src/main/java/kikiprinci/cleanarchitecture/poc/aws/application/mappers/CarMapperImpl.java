package kikiprinci.cleanarchitecture.poc.aws.application.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import kikiprinci.cleanarchitecture.poc.aws.application.dtos.CarDto;
import kikiprinci.cleanarchitecture.poc.aws.domain.entities.Car;
import kikiprinci.cleanarchitecture.poc.aws.domain.mappers.CarMapper;

@Component
public class CarMapperImpl implements CarMapper<CarDto> {
    @Override
    public Car toDomain(CarDto dto) {
        return new Car(dto.getId(), dto.getBrand(), dto.getModel(), dto.getVersion(),dto.getLicensePlate(), dto.isAvailable(),dto.getManufacterYear());
    }

    @Override
    public CarDto fromDomain(Car car) {
        return new CarDto(car.getId(),car.getBrand(),car.getModel(),car.getVersion(),car.getLicensePlate(),car.isAvailable(),car.getManufacterYear(),car.getCreationTimeStamp(),car.getCreationDate());
    }

    @Override
    public List<Car> toDomain(List<CarDto> source) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toDomain'");
    }

    @Override
    public List<CarDto> fromDomain(List<Car> car) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fromDomain'");
    }
}