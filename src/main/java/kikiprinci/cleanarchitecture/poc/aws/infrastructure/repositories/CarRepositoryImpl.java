package kikiprinci.cleanarchitecture.poc.aws.infrastructure.repositories;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kikiprinci.cleanarchitecture.poc.aws.application.PaginatedResult;
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

import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;

@Repository
public class CarRepositoryImpl implements CarRepository {
    private final DynamoDbClient dynamoDbClient;
    private final CarMapper<CarDynamoDbModel> carMapper;
    private static final String TABLE_NAME = "cars";
    private static final String PARTITION_KEY = "id";
    private static final String SORT_KEY = "brand";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    public CarRepositoryImpl(DynamoDbClient dynamoDbClient, CarMapper<CarDynamoDbModel> carMapper) {
        this.dynamoDbClient = dynamoDbClient;
        this.carMapper = carMapper;
    }

    @Override
    public Car findById(String id) {
        HashMap<String, AttributeValue> keyToGet = new HashMap<String, AttributeValue>();
        keyToGet.put("id", AttributeValue.builder().s(id).build());

        GetItemRequest request = GetItemRequest.builder()
                .key(keyToGet)
                .tableName(TABLE_NAME)
                .build();

        GetItemResponse response = dynamoDbClient.getItem(request);
        Map<String, AttributeValue> item = response.item();

        return mapItem(item);
    }

    @Override
    public PaginatedResult<Car> findAll(int limit, String lastEvaluatedKey) {
        ScanRequest.Builder scanRequestBuilder = ScanRequest.builder()
                .tableName(TABLE_NAME)
                .limit(limit);
                
        if (lastEvaluatedKey != null && !lastEvaluatedKey.isEmpty()) {
            Map<String, AttributeValue> map = new HashMap<>();
            map.put("id", AttributeValue.builder().s(lastEvaluatedKey).build());
            scanRequestBuilder.exclusiveStartKey(map);
        }

        ScanRequest scanRequest = scanRequestBuilder.build();
        ScanResponse scanResponse = dynamoDbClient.scan(scanRequest);
        List<Map<String, AttributeValue>> items = scanResponse.items();

        List<Car> cars = new ArrayList<>();
        for (Map<String, AttributeValue> item : items) {
            cars.add(mapItem(item));
        }

        return new PaginatedResult<Car>(cars, scanResponse.lastEvaluatedKey().get("id").s());
    }

    @Override
    public void save(Car car) {
        Map<String, AttributeValue> item = new HashMap<>();
        Instant creationTimeStamp = Instant.now();

        item.put(PARTITION_KEY, AttributeValue.builder().s(car.getId()).build());
        item.put(SORT_KEY, AttributeValue.builder().s(car.getBrand()).build());
        item.put("model", AttributeValue.builder().s(car.getModel()).build());
        item.put("version", AttributeValue.builder().s(car.getVersion()).build());
        item.put("licensePlate", AttributeValue.builder().s(car.getLicensePlate()).build());
        item.put("available", AttributeValue.builder().bool(car.isAvailable()).build());
        item.put("manufacterYear", AttributeValue.builder().n(Integer.toString(car.getManufacterYear())).build());
        item.put("creationTimeStamp",
                AttributeValue.builder().n(String.valueOf(creationTimeStamp.toEpochMilli())).build());
        item.put("creationDate", AttributeValue.builder().s(DATE_FORMAT.format(Date.from(creationTimeStamp))).build());

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

    @Override
    public void delete(Car car) {
        // Implementación para actualizar un coche en DynamoDB
    }

    private Car mapItem(Map<String, AttributeValue> item) {

        if (item != null && !item.isEmpty()) {

            Instant creationTimeStamp = item.get("creationTimeStamp").n() != null
                    ? Instant.ofEpochMilli(Long.parseLong(item.get("creationTimeStamp").n().toString()))
                    : null;
            Date creationDate = creationTimeStamp != null ? Date.from(creationTimeStamp) : null;

            CarDynamoDbModel model = new CarDynamoDbModel(
                    item.get("id").s(),
                    item.get("brand").s(),
                    item.get("model").s(),
                    item.get("version").s(),
                    item.get("licensePlate").s(),
                    Boolean.parseBoolean(item.get("available").bool().toString()),
                    Integer.parseInt(item.get("manufacterYear").n()),
                    creationTimeStamp,
                    creationDate);
            return carMapper.toDomain(model);
        }
        return new Car();
    }

}