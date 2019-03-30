package com.dsniatecki.yourfleetmanager.dto;

import com.dsniatecki.yourfleetmanager.entities.Department;
import com.dsniatecki.yourfleetmanager.validators.ValidString;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.util.Set;

@Getter
@Setter
public class CompanyDTO {

    private Long id;

    @ValidString(min=2, max=100)
    private String name;

    @Valid
    private ContactDetailsDTO contactDetails;

    @Valid
    private AddressDTO address;

    private Set<Department> departments;

    public CompanyDTO(){
        this.contactDetails = new ContactDetailsDTO();
        this.address = new AddressDTO();
    }

}
