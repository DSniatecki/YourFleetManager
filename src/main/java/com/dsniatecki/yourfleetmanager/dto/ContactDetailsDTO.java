package com.dsniatecki.yourfleetmanager.dto;

import com.dsniatecki.yourfleetmanager.validators.ValidString;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
class ContactDetailsDTO {
    private Long id;

    @ValidString(min=8, max=20)
    private String telephoneNumber;

    @Email
    @NotBlank(message = "is required")
    @Length(max=40)
    private String emailAddress;

}