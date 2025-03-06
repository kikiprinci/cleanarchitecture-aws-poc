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
        return new Car(
            model.getId(), 
            model.getBrand(), 
            model.getModel(), 
            model.getVersion(), 
            model.getLicensePlate(), 
            model.isAvailable(),
            model.getManufacterAge(),
            model.getcreationTimeStamp(),
            model.getcreationDate()
        );
    }
    
    @Override
    public CarDynamoDbModel fromDomain(Car car) {
        return new CarDynamoDbModel(
            car.getId(),
            car.getBrand(),
            car.getModel(),
            car.getVersion(),
            car.getLicensePlate(),
            car.isAvailable(),
            car.getManufacterYear(),
            car.getCreationTimeStamp(),
            car.getCreationDate()
        );

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