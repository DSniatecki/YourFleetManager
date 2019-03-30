package com.dsniatecki.yourfleetmanager.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "contact_details")
class ContactDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @Column(name = "email_address")
    private String emailAddress;

    ContactDetails(){}

}