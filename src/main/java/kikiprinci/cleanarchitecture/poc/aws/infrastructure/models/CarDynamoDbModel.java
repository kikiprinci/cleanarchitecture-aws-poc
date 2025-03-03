package kikiprinci.cleanarchitecture.poc.aws.infrastructure.models;

import java.time.Instant;
import java.util.Date;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class CarDynamoDbModel {
    private String id;
    private String brand;
    private String model;
    private String version;
    private String licensePlate;
    private boolean available;
    private int manufacterYear;
    private Instant creationTimeStamp;
    private Date creationDate;
    

    public CarDynamoDbModel() {}

    public CarDynamoDbModel(String id,String brand,String model,String version,String licensePlate,boolean available,int manufacterYear) {
        this.id=id;
        this.brand=brand;
        this.model=model;
        this.version=version;
        this.licensePlate=licensePlate;
        this.available=available;
        this.manufacterYear=manufacterYear;
        this.creationTimeStamp=Instant.now();
        this.creationDate=Date.from(Instant.now());
    }

    public CarDynamoDbModel(String id,String brand,String model,String version,String licensePlate,boolean available,int manufacterYear, Instant creationTimeStamp,Date creationDate) {
        this.id=id;
        this.brand=brand;
        this.model=model;
        this.version=version;
        this.licensePlate=licensePlate;
        this.available=available;
        this.manufacterYear=manufacterYear;
        this.creationDate=creationDate;
        this.creationTimeStamp=creationTimeStamp;
    }

    @DynamoDbPartitionKey
    @DynamoDbAttribute("id")
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    @DynamoDbAttribute("brand")
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    @DynamoDbAttribute("model")
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    @DynamoDbAttribute("version")
    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }

    @DynamoDbAttribute("manufacterYear")
    public int getManufacterAge() { return manufacterYear; }
    public void setManufacterAge(int manufacterYear) { this.manufacterYear = manufacterYear; }

    @DynamoDbAttribute("licensePlate")
    public String getLicensePlate() { return licensePlate; }
    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }

    @DynamoDbAttribute("available")
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    @DynamoDbAttribute("creationDate")
    public Date getcreationDate() { return creationDate; }
    public void setcreationDate(Date creationDate) { this.creationDate = creationDate; }

    @DynamoDbAttribute("creationTimeStamp")
    public Instant getcreationTimeStamp() { return creationTimeStamp; }
    public void setcreationTimeStamp(Instant creationTimeStamp) { this.creationTimeStamp = creationTimeStamp; }
}