package com.dsniatecki.yourfleetmanager.dto;

import com.dsniatecki.yourfleetmanager.domains.Car;
import com.dsniatecki.yourfleetmanager.domains.Company;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class DepartmentDTO {

    private Long id;
    private String name;
    private ContactDetailsDTO contactDetails;
    private Company company;
    private Set<Car> cars;

    public DepartmentDTO(){
        this.contactDetails = new ContactDetailsDTO();
    }

}
