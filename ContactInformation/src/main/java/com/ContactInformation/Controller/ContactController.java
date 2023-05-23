package com.ContactInformation.Controller;

import com.ContactInformation.Payload.ContactDto;
import com.ContactInformation.Service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }
    @PostMapping("/save")
    public ResponseEntity<ContactDto> createContact(@RequestBody ContactDto contactDto){
        ContactDto contact = contactService.createContact(contactDto);
        return  ResponseEntity.ok(contact);

    }
    @GetMapping("/{postalCode}")
    public ResponseEntity<List<ContactDto>> getContactByPostalCode(@PathVariable("postalCode")
                                                                   String postalCode){
        List<ContactDto> contact = contactService.getContactBypostalCode(postalCode);
        return ResponseEntity.ok(contact);

    }
    @GetMapping("/get")
    public ResponseEntity<List<ContactDto>> getAll(){
        List<ContactDto> all = contactService.getAll();
        return ResponseEntity.ok(all);

    }
}
