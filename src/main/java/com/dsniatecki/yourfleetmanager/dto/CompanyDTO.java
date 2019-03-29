package com.dsniatecki.yourfleetmanager.dto;

import com.dsniatecki.yourfleetmanager.domains.Department;
import com.dsniatecki.yourfleetmanager.validators.ValidString;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
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
