package com.dsniatecki.yourfleetmanager.domains;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "vehicle_responder")
public class VehicleResponder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="contact_details_id")
    private ContactDetails contactDetails;

    public VehicleResponder() { }

    public VehicleResponder(String firstName, String lastName, ContactDetails contactDetails) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactDetails = contactDetails;
    }
}
