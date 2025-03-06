# JAVA Clean Architecture PoC

## Project structure


    ├── application
    │   ├── dtos
    │   │   └── CarDto.java
    │   ├── usecases
    │   │   ├── CreateCarUseCase.java
    │   │   └── GetCarUseCase.java
    │   └── PaginatedResult.java
    ├── domain
    │   ├── entities
    │   │   └── Car.java
    │   ├── mappers
    │   │   └── CarMapper.java
    │   └── repositories
    │       └── CarRepository.java
    ├── infrastructure
    │   ├── models
    │   │   └── CarDynamoDbModel.java
    │   └── repositories
    │       └── CarRepositoryImpl.java
    └── presentation
        └── controllers
            └── CarController.java

## Short Description of the Layers

### Presentation Layer (`presentation`):

- **Controllers**: Handle HTTP requests and delegate business logic to use cases.
  - `CarController.java`: Controller that handles requests related to cars.

### Application Layer (`application`):

- **DTOs**: Data Transfer Objects used to transfer data between layers.
  - `CarDto.java`: DTO for the `Car` entity.
- **Use Cases**: Contain the application-specific business logic.
  - `CreateCarUseCase.java`: Use case for creating a car.
  - `GetCarUseCase.java`: Use case for retrieving cars.
- **Utilities**: Utility classes used in the application layer.
  - `PaginatedResult.java`: Class for handling paginated results.

### Domain Layer (`domain`):

- **Entities**: Business objects that represent the main entities of the application.
  - `Car.java`: Entity representing a car.
- **Mappers**: Classes responsible for mapping between entities and DTOs.
  - `CarMapper.java`: Mapper for the `Car` entity.
- **Repositories**: Interfaces that define methods for accessing data.
  - `CarRepository.java`: Repository interface for the `Car` entity.

### Infrastructure Layer (`infrastructure`):

- **Models**: Classes representing the data models specific to the infrastructure.
  - `CarDynamoDbModel.java`: Data model for DynamoDB.
- **Repositories**: Implementations of repository interfaces that access data.
  - `CarRepositoryImpl.java`: Repository implementation for the `Car` entity using DynamoDB.