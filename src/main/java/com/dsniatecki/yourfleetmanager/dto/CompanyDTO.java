package com.dsniatecki.yourfleetmanager.dto;

import com.dsniatecki.yourfleetmanager.domains.Department;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CompanyDTO {

    private Long id;
    private String name;
    private ContactDetailsDTO contactDetails;
    private AddressDTO address;
    private Set<Department> departments;

    public CompanyDTO(){
        this.contactDetails = new ContactDetailsDTO();
        this.address = new AddressDTO();
    }

}
