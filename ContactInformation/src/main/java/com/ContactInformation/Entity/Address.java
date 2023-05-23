package com.ContactInformation.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="State",nullable = false)
    private String state;
    @Column(name ="Dist",nullable = false)
    private String dist;
    @Column(name="City",nullable = false)
    private String city;
    @Column(name ="PostalCode",nullable = false)
    private  String postalCode;

}
