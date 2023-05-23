package com.ContactInformation.Payload;

import lombok.Data;

import javax.persistence.Column;
@Data
public class ContactDto {

    private String fullName;
    private  String dateOfBirth;
    private AddressDto address;
    }

