package com.ContactInformation.Service;

import com.ContactInformation.Payload.ContactDto;

import java.util.List;

public interface ContactService {
    ContactDto createContact(ContactDto contactDto);

   List<ContactDto> getContactBypostalCode(String postalCode);

    List<ContactDto> getAll();
}
