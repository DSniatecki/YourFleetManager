package com.dsniatecki.yourfleetmanager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class VehicleResponderDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private ContactDetailsDTO contactDetails;

    VehicleResponderDTO(){
        this.contactDetails = new ContactDetailsDTO();
    }
}