package com.dsniatecki.yourfleetmanager.dto;

import com.dsniatecki.yourfleetmanager.domains.Department;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarDTO {

    private Long id;
    private String brand;
    private String model;
    private int productionYear;
    private String registrationNumber;
    private Department department;
    private VehicleResponderDTO vehicleResponder;

    public CarDTO(){
       this.vehicleResponder = new VehicleResponderDTO();
    }

}