package com.faceit.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity()
@Table(name="msi")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Msi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="state")
    private String state;
    @Column(name="name")
    private String name;
    @Column(name="institution_type")
    private String institutionType;
    @Column(name="phone_number")
    private String phoneNumber;
    @Column(name="website")
    private String website;

    public Msi(String state, String name, String institutionType, String phoneNumber, String website) {
        this.state = state;
        this.name = name;
        this.institutionType = institutionType;
        this.phoneNumber = phoneNumber;
        this.website = website;
    }
}
