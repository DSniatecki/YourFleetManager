package com.dsniatecki.yourfleetmanager.domains;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "company_department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "c_d_name")
    private String name;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="contact_details_id")
    private ContactDetails contactDetails;

    @ManyToOne
    @JoinColumn(name="company_id")
    private Company company;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private Set<Car> cars;

    public void addCar(Car car){
        if(this.cars == null) this.cars = new HashSet<>();
        car.setDepartment(this);
        this.cars.add(car);
    }

    public Department() { }

    public Department(String name, ContactDetails contactDetails) {
        this.name = name;
        this.contactDetails = contactDetails;
    }
}
