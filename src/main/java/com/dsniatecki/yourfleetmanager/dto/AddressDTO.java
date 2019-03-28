package com.dsniatecki.yourfleetmanager.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
class AddressDTO {

    private Long id;
    private String street;
    private String buildingNumber;
    private String city;
    private String zipCode;
    private String country;

}