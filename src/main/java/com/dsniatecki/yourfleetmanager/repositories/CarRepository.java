package com.dsniatecki.yourfleetmanager.repositories;

import com.dsniatecki.yourfleetmanager.domains.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {
}
