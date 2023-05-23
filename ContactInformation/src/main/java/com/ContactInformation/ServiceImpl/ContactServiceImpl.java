package com.ContactInformation.ServiceImpl;

import com.ContactInformation.Entity.Address;
import com.ContactInformation.Entity.Contact;
import com.ContactInformation.Payload.AddressDto;
import com.ContactInformation.Payload.ContactDto;
import com.ContactInformation.Repository.AddressRepository;
import com.ContactInformation.Repository.ContactRepository;
import com.ContactInformation.Service.ContactService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ContactServiceImpl implements ContactService {
    private static final Logger logger= Logger.getLogger(ContactServiceImpl.class.getName());

    private final AddressRepository addressRepository;
    private final ContactRepository contactRepository;

    public ContactServiceImpl(AddressRepository addressRepository, ContactRepository contactRepository) {
        this.addressRepository = addressRepository;
        this.contactRepository = contactRepository;
    }
        @Override
        public ContactDto createContact(ContactDto contactDto) {
            Contact contact = new Contact();
            contact.setFullName(contactDto.getFullName());
            contact.setDateOfBirth(contactDto.getDateOfBirth());

            AddressDto addressDto = contactDto.getAddress();
            Address address = new Address();
            address.setState(addressDto.getState());
            address.setDist(addressDto.getDist());
            address.setCity(addressDto.getCity());
            address.setPostalCode(addressDto.getPostalCode());

            addressRepository.save(address); // Save the address entity first

            contact.setAddress(address); // Set the address in the contact entity

            Contact createdContact = contactRepository.save(contact);

            ContactDto createdContactDTO = new ContactDto();
            createdContactDTO.setFullName(createdContact.getFullName());
            createdContactDTO.setDateOfBirth(createdContact.getDateOfBirth());

            Address createdAddress = createdContact.getAddress();
            AddressDto createdAddressDTO = new AddressDto();
            createdAddressDTO.setState(createdAddress.getState());
            createdAddressDTO.setCity(createdAddress.getCity());
            createdAddressDTO.setDist(createdAddress.getDist());
            createdAddressDTO.setPostalCode(createdAddress.getPostalCode());

            createdContactDTO.setAddress(createdAddressDTO);
            long endTime=System.currentTimeMillis();
            long startTime=0;
            long executionTime=endTime-startTime;
            logger.info("createContact execution time:"+executionTime+"ms");



            return createdContactDTO;
        }

    @Override
    public List<ContactDto> getContactBypostalCode(String postalCode) {
        List<Contact> contacts =contactRepository.findByAddressPostalCode(postalCode);

        return mapContactEntitiesToDtos(contacts);
    }

    @Override
    public List<ContactDto> getAll() {
        List<Contact> all = contactRepository.findAll();
        return mapContactEntitiesToDtos(all);
    }

    private List<ContactDto> mapContactEntitiesToDtos(List<Contact> contacts){
        List<ContactDto> contactDtos = new ArrayList<>();
        for (Contact contact : contacts) {
            ContactDto contactDto = new ContactDto();
            contactDto.setFullName(contact.getFullName());
            contactDto.setDateOfBirth(contact.getDateOfBirth());

            // Map the address details
            AddressDto addressDto = new AddressDto();
            Address address = contact.getAddress();
            if (address != null) {
                addressDto.setState(address.getState());
                addressDto.setDist(address.getDist());
                addressDto.setCity(address.getCity());
                addressDto.setPostalCode(address.getPostalCode());
            }
            contactDto.setAddress(addressDto);

            contactDtos.add(contactDto);
        }
        return contactDtos;
    }

    }






