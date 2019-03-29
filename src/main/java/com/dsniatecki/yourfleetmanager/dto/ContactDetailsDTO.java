package com.dsniatecki.yourfleetmanager.dto;

import com.dsniatecki.yourfleetmanager.validators.ValidString;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
class ContactDetailsDTO {


    private Long id;

    @Pattern(regexp = "^\\(?[\\d]{3}\\)?[\\s-]?[\\d]{3}[\\s-]?[\\d]{3,4}$", message = "must be well-formed telephone number")
    private String telephoneNumber;

    @Email
    @NotBlank(message = "is required")
    @Length(max=40)
    private String emailAddress;

}