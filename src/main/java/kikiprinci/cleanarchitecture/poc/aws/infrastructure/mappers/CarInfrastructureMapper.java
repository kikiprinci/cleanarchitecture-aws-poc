package kikiprinci.cleanarchitecture.poc.aws.infrastructure.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import kikiprinci.cleanarchitecture.poc.aws.domain.entities.Car;
import kikiprinci.cleanarchitecture.poc.aws.domain.mappers.CarMapper;
import kikiprinci.cleanarchitecture.poc.aws.infrastructure.models.CarDynamoDbModel;

@Component
public class CarInfrastructureMapper implements CarMapper<CarDynamoDbModel> {
    @Override
    public Car toDomain(CarDynamoDbModel model) {
        // return new Car(model.getId(), model.getModel(), model.getLicensePlate(), model.isAvailable());
        return null;
    }

    @Override
    public CarDynamoDbModel fromDomain(Car car) {
        CarDynamoDbModel model = new CarDynamoDbModel();
        // model.setId(car.getId());
        // model.setModel(car.getModel());
        // model.setLicensePlate(car.getLicensePlate());
        // model.setAvailable(car.isAvailable());
        return model;
    }

    @Override
    public List<Car> toDomain(List<CarDynamoDbModel> source) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toDomain'");
    }

    @Override
    public List<CarDynamoDbModel> fromDomain(List<Car> car) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fromDomain'");
    }
}