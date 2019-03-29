package com.dsniatecki.yourfleetmanager.dto;

import com.dsniatecki.yourfleetmanager.domains.Car;
import com.dsniatecki.yourfleetmanager.domains.Company;
import com.dsniatecki.yourfleetmanager.validators.ValidString;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.util.Set;

@Getter
@Setter
public class DepartmentDTO {

    private Long id;

    @ValidString(min=2, max=100)
    private String name;

    @Valid
    private ContactDetailsDTO contactDetails;

    @Valid
    private Company company;
    private Set<Car> cars;

    public DepartmentDTO(){
        this.contactDetails = new ContactDetailsDTO();
    }

}
