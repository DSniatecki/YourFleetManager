package com.dsniatecki.yourfleetmanager.domains;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "production_year")
    private int productionYear;

    @Column(name = "registration_number")
    private String registrationNumber;

    @ManyToOne
    @JoinColumn(name="company_department_id")
    private Department department;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="vehicle_responder_id")
    private VehicleResponder vehicleResponder;

    public Car() {
        this.vehicleResponder = new VehicleResponder();
    }

    public Car(String brand, String model, int productionYear, String registrationNumber, VehicleResponder vehicleResponder) {
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
        this.registrationNumber = registrationNumber;
        this.vehicleResponder = vehicleResponder;
    }
}
