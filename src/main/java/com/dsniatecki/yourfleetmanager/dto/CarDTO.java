package com.dsniatecki.yourfleetmanager.dto;

import com.dsniatecki.yourfleetmanager.entities.Department;
import com.dsniatecki.yourfleetmanager.validators.ValidString;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
public class CarDTO {

    private Long id;

    @ValidString(min=2, max=60)
    private String brand;

    @ValidString(min=2, max=60)
    private String model;

    @Min(value = 1900, message = "Can not be less than 1900")
    @Max(value = 2020, message = "Can not  be greater than 2020")
    private int productionYear;

    @ValidString(min=2, max=40)
    private String registrationNumber;

    private Department department;

    @Valid
    private VehicleResponderDTO vehicleResponder;

    public CarDTO(){
       this.vehicleResponder = new VehicleResponderDTO();
    }

}