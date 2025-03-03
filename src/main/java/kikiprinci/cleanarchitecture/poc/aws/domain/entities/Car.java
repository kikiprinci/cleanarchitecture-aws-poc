package kikiprinci.cleanarchitecture.poc.aws.domain.entities;

import java.time.Instant;
import java.util.Date;

public class Car {
    private String id;
    private String brand;
    private String model;
    private String version;
    private String licensePlate;
    private boolean available;
    private int manufacterYear;
    private Instant creationTimeStamp;
    private Date creationDate;

    public Car() {}

    public Car(String id, String brand, String model, String version, String licensePlate, boolean available, int manufacterYear) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.version = version;
        this.licensePlate = licensePlate;
        this.available = available;
        this.manufacterYear = manufacterYear;
    }

    public Car(String id, String brand, String model, String version, String licensePlate, boolean available, int manufacterYear, Instant creationTimeStamp, Date creationDate) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.version = version;
        this.licensePlate = licensePlate;
        this.available = available;
        this.manufacterYear = manufacterYear;
        this.creationDate = creationDate;
        this.creationTimeStamp = creationTimeStamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getManufacterYear() {
        return manufacterYear;
    }

    public void setManufacterYear(int manufacterYear) {
        this.manufacterYear = manufacterYear;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Instant getCreationTimeStamp() {
        return creationTimeStamp;
    }

    public void setCreationTimeStamp(Instant creationTimeStamp) {
        this.creationTimeStamp = creationTimeStamp;
    }
}
