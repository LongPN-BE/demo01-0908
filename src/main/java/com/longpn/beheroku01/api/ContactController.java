package com.longpn.beheroku01.api;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.longpn.beheroku01.entity.Contact;
import com.longpn.beheroku01.repository.ContactRepository;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ContactController {
    public static Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    ContactRepository contactRepository;

    @RequestMapping(value = "/contact/", method = RequestMethod.GET)
    public ResponseEntity<List<Contact>> listAllContact(){
        List<Contact> listContact= contactRepository.findAll();
        if(listContact.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Contact>>(listContact, HttpStatus.OK);
    }

    @RequestMapping(value = "/contact/{id}", method = RequestMethod.GET)
    public Contact findContact(@PathVariable("idContact")  long id) {
        Contact contact= contactRepository.getOne(id);
        if(contact == null) {
            ResponseEntity.notFound().build();
        }
        return contact;
    }

    @RequestMapping(value = "/contact/", method = RequestMethod.POST)
    public Contact saveContact(@Valid @RequestBody Contact contact) {
        return contactRepository.save(contact);
    }

    @RequestMapping(value = "/contact/", method = RequestMethod.PUT)
    public ResponseEntity<Contact> updateContact(@PathVariable(value = "idContact") long contactId,
                                                 @Valid @RequestBody Contact contactForm) {
        Contact contact = contactRepository.getOne(contactId);
        if(contact == null) {
            return ResponseEntity.notFound().build();
        }
        contact.setName(contactForm.getName());
        contact.setAge(contactForm.getAge());

        Contact updatedContact = contactRepository.save(contact);
        return ResponseEntity.ok(updatedContact);
    }

    @RequestMapping(value = "/contact/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Contact> deleteContact(@PathVariable(value = "idContact") long id) {
        Contact contact = contactRepository.getOne(id);
        if(contact == null) {
            return ResponseEntity.notFound().build();
        }

        contactRepository.delete(contact);
        return ResponseEntity.ok().build();
    }
}

