package com.dsniatecki.yourfleetmanager.services;

import com.dsniatecki.yourfleetmanager.domains.Car;
import com.dsniatecki.yourfleetmanager.domains.Department;
import com.dsniatecki.yourfleetmanager.dto.CarDTO;
import com.dsniatecki.yourfleetmanager.exceptions.NotFoundException;
import com.dsniatecki.yourfleetmanager.repositories.CarRepository;
import com.dsniatecki.yourfleetmanager.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public CarServiceImpl(CarRepository carRepository, DepartmentRepository departmentRepository){
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.carRepository = carRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public CarDTO getById(Long id) {
        Optional<Car> carOptional =  carRepository.findById(id);
        if(!carOptional.isPresent()) {
            throw new NotFoundException("Car[ id: " + id + " ] was not found .");
        }
        return modelMapper.map(carOptional.get(), CarDTO.class);
    }

    @Override
    public CarDTO saveWithDepartment(CarDTO carDTO, Long departmentId) {
        Optional<Department> departmentOptional = departmentRepository.findById(departmentId);

        if(!(departmentOptional.isPresent())) {
            throw new NotFoundException("Department[ id: " + departmentId + " ] was not found .");
        }
        Car car = modelMapper.map(carDTO, Car.class);
        car.setDepartment(departmentOptional.get());
        Car savedCar = carRepository.save(car);
        return modelMapper.map(savedCar, CarDTO.class);

    }

    @Override
    public void deleteById(Long id) {
        try{
            carRepository.deleteById(id);
        }catch(IllegalArgumentException e ){
            throw new NotFoundException("Car[ id: " + id + " ] was not found .");
        }
    }
}
