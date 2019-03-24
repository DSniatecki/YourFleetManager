package com.dsniatecki.yourfleetmanager.services;

import com.dsniatecki.yourfleetmanager.domains.Car;

public interface CarService {
    Car getById(Long id);
    Car saveWithDepartment(Car car, Long departmentId);
    void deleteById(Long id);
}
