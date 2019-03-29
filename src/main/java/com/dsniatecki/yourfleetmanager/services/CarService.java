package com.dsniatecki.yourfleetmanager.services;

import com.dsniatecki.yourfleetmanager.dto.CarDTO;

public interface CarService {
    CarDTO getById(Long id);
    CarDTO saveWithDepartment(CarDTO car, Long departmentId);
    void deleteById(Long id);
}
