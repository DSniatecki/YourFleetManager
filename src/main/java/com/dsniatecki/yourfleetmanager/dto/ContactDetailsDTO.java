package com.dsniatecki.yourfleetmanager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
class ContactDetailsDTO {
    private Long id;
    private String telephoneNumber;
    private String emailAddress;

}