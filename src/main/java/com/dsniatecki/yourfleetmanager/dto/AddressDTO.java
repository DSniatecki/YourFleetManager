package com.dsniatecki.yourfleetmanager.dto;


import com.dsniatecki.yourfleetmanager.validators.ValidString;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
class AddressDTO {

    private Long id;

    @ValidString(min=2, max=80)
    private String street;

    @ValidString(min=1, max=20)
    private String buildingNumber;

    @ValidString(min=2, max=80)
    private String city;

    @ValidString(min=2, max=20)
    private String zipCode;

    @ValidString(min=2, max=40)
    private String country;

}