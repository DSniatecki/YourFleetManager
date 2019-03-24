package com.dsniatecki.yourfleetmanager.services;

import com.dsniatecki.yourfleetmanager.domains.Car;
import com.dsniatecki.yourfleetmanager.domains.Department;
import com.dsniatecki.yourfleetmanager.exceptions.NotFoundException;
import com.dsniatecki.yourfleetmanager.repositories.CarRepository;
import com.dsniatecki.yourfleetmanager.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final DepartmentRepository departmentRepository;

    public CarServiceImpl(CarRepository carRepository, DepartmentRepository departmentRepository){
        this.carRepository = carRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Car getById(Long id) {
        Optional<Car> carOptional =  carRepository.findById(id);
        if(!carOptional.isPresent()) {
            throw new NotFoundException("Car[ id: " + id + " ] was not found .");
        }
        return carOptional.get();
    }

    @Override
    public Car saveWithDepartment(Car car, Long departmentId) {
        Optional<Department> departmentOptional = departmentRepository.findById(departmentId);

        if(!(departmentOptional.isPresent())) {
            throw new NotFoundException("Department[ id: " + departmentId + " ] was not found .");
        }
        car.setDepartment(departmentOptional.get());
        return carRepository.save(car);

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
