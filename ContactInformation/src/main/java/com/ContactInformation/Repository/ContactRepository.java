package com.ContactInformation.Repository;

import com.ContactInformation.Entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact,Long> {
    List<Contact>findByAddressPostalCode(String postalCode);
}
