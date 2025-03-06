package kikiprinci.cleanarchitecture.poc.aws.presentation.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kikiprinci.cleanarchitecture.poc.aws.application.PaginatedResult;
import kikiprinci.cleanarchitecture.poc.aws.application.dtos.CarDto;
import kikiprinci.cleanarchitecture.poc.aws.application.usecases.CreateCarUseCase;
import kikiprinci.cleanarchitecture.poc.aws.application.usecases.GetCarUseCase;

@RestController
@RequestMapping("/cars")
public class CarController {
    private final CreateCarUseCase createCarUseCase;
    private final GetCarUseCase getCarUseCase;

    public CarController(CreateCarUseCase createCarUseCase, GetCarUseCase getCarUseCase) {
        this.createCarUseCase = createCarUseCase;
        this.getCarUseCase = getCarUseCase;
    }

    @PostMapping
    public CarDto createCar(@RequestBody CarDto carDto) {
        return createCarUseCase.execute(carDto);
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World!";
    }

    @GetMapping("/{id}")
    public CarDto getById(@PathVariable String id) {
        return getCarUseCase.getById(id);
    }

    @GetMapping
    public PaginatedResult<CarDto> get(@RequestParam(required = false) String lastId,@RequestParam(required = false) Integer limit) {
        int effectiveLimit = (limit != null) ? limit : 10;
        String effectiveULasId = (lastId != null) ? lastId : "";
        return getCarUseCase.getAll(effectiveLimit, effectiveULasId);
    }
}
