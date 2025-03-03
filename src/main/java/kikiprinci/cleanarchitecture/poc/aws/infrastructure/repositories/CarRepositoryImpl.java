package kikiprinci.cleanarchitecture.poc.aws.infrastructure.repositories;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kikiprinci.cleanarchitecture.poc.aws.application.dtos.CarDto;
import kikiprinci.cleanarchitecture.poc.aws.domain.entities.Car;
import kikiprinci.cleanarchitecture.poc.aws.domain.mappers.CarMapper;
import kikiprinci.cleanarchitecture.poc.aws.domain.repositories.CarRepository;
import kikiprinci.cleanarchitecture.poc.aws.infrastructure.models.CarDynamoDbModel;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.GetItemResponse;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemResponse;
import software.amazon.awssdk.services.dynamodb.model.QueryRequest;
import software.amazon.awssdk.services.dynamodb.model.QueryResponse;
import software.amazon.awssdk.services.dynamodb.model.TableClass;

@Repository
public class CarRepositoryImpl implements CarRepository {
    private final DynamoDbClient dynamoDbClient;
    private final CarMapper<CarDynamoDbModel> carMapper;
    private static final String TABLE_NAME = "cars";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    public CarRepositoryImpl(DynamoDbClient dynamoDbClient, CarMapper<CarDynamoDbModel> carMapper) {
        this.dynamoDbClient = dynamoDbClient;
        this.carMapper = carMapper;
    }

    @Override
    public Car findById(String id) {

//         QueryRequest query = QueryRequest.builder()
//             .tableName(TABLE_NAME)
//             .keyConditionExpression("id = :id")
//             .expressionAttributeValues(Map.of(":id", AttributeValue.builder().s(id).build()))
//             .build();

// AttributeValue.builder().s("ValorBuscado").build()))
//     .build();

// QueryResponse response = ddb.query(queryRequest);

// List<Map<String, AttributeValue>> items = response.items();



//         GetItemRequest request = GetItemRequest.builder()
//             .tableName(TABLE_NAME)
//             .key(Map.of("id", AttributeValue.builder().s(id).build()))
//             .key(Map.of("branch", AttributeValue.builder().s(id).build()))
//             .build();

//         GetItemResponse response = dynamoDbClient.getItem(request);
//         Map<String, AttributeValue> item = response.item();
        
//         if (item != null && !item.isEmpty()) {
//             CarDynamoDbModel model = new CarDynamoDbModel(
//                 item.get("id").s(),
//                 item.get("brand").s(),
//                 item.get("model").s(),
//                 item.get("version").s(),
//                 item.get("licensePlate").s(),
//                 Boolean.parseBoolean(item.get("available").bool().toString()),
//                 Integer.parseInt(item.get("manufacterYear").n()),
//                 Instant.parse(item.get("creationTimeStamp").s()), 
//                 null
//             );
//             return carMapper.toDomain(model);
//         }
        return null;
    }

    @Override
    public List<Car> findAll() {
        // GetItemRequest request = GetItemRequest.builder()
        //     .tableName(TABLE_NAME)
        //     .key(Map.of("id", AttributeValue.builder().s(id).build()))
        //     .build();

        // GetItemResponse response = dynamoDbClient.getItem(request);
        // Map<String, AttributeValue> item = response.item();
        
        // if (item != null && !item.isEmpty()) {
        //     CarDynamoDbModel model = new CarDynamoDbModel(
        //         item.get("id").s(),
        //         item.get("model").s(),
        //         item.get("licensePlate").s(),
        //         Boolean.parseBoolean(item.get("available").bool().toString()),
        //         Integer.parseInt(item.get("manufacterYear").n()),
        //         Instant.parse(item.get("creationTimeStamp").s()),
        //         item.get("creationDate") != null ? DATE_FORMAT.parse(item.get("creationDate").s()) : new Date()
        //     );
        //     return carMapper.toDomain(model);
        // }
        return null;
    }

    @Override
    public void save(Car car) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("id", AttributeValue.builder().s(car.getId()).build());
        item.put("branch", AttributeValue.builder().s(car.getBrand()).build());
        item.put("brand", AttributeValue.builder().s(car.getBrand()).build());
        item.put("model", AttributeValue.builder().s(car.getModel()).build());
        item.put("version", AttributeValue.builder().s(car.getVersion()).build());
        item.put("licensePlate", AttributeValue.builder().s(car.getLicensePlate()).build());
        item.put("available", AttributeValue.builder().bool(car.isAvailable()).build());
        item.put("manufacterYear", AttributeValue.builder().n(Integer.toString(car.getManufacterYear())).build());
        item.put("creationTimeStamp", AttributeValue.builder().n(String.valueOf(Instant.now().toEpochMilli())).build());
        item.put("creationDate", AttributeValue.builder().s(DATE_FORMAT.format(Date.from(Instant.now()))).build());

        PutItemRequest request = PutItemRequest.builder()
            .tableName(TABLE_NAME)
            .item(item)
            .build();

        PutItemResponse response = dynamoDbClient.putItem(request);
    }

    @Override
    public void update(Car car) {
        // Implementación para actualizar un coche en DynamoDB
    }
}