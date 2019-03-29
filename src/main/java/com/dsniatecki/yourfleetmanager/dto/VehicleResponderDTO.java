package com.dsniatecki.yourfleetmanager.dto;

import com.dsniatecki.yourfleetmanager.validators.ValidString;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;

@Getter
@Setter
class VehicleResponderDTO {

    private Long id;

    @ValidString(min=2, max=60)
    private String firstName;

    @ValidString(min=2, max=60)
    private String lastName;

    @Valid
    private ContactDetailsDTO contactDetails;

    VehicleResponderDTO(){
        this.contactDetails = new ContactDetailsDTO();
    }
}