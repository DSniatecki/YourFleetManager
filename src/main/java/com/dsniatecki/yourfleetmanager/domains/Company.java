package com.dsniatecki.yourfleetmanager.domains;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "c_name")
    private String name;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="contact_details_id")
    private ContactDetails contactDetails;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="address_id")
    private Address address;

    @OneToMany( mappedBy = "company", cascade = CascadeType.ALL)
    private Set<Department> departments;

    public void addDepartment(Department department){
        if(this.departments == null) this.departments = new HashSet<>();
        department.setCompany(this);
        this.departments.add(department);
    }

    public Company() {
        this.contactDetails = new ContactDetails();
        this.address = new Address();
    }


    public Company(String name, ContactDetails contactDetails, Address address) {
        this.name = name;
        this.contactDetails = contactDetails;
        this.address = address;
    }
}
