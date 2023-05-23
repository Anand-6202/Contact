package com.ContactInformation.Payload;


import lombok.Data;

@Data
public class AddressDto {
    private String state;
    private String dist;
    private String city;
    private  String postalCode;
}
