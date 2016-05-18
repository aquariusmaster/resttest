package com.aquariusmaster.resttest.controllers;

import com.aquariusmaster.resttest.entity.Contact;
import com.aquariusmaster.resttest.services.IContactsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by harkonnen on 16.05.16.
 */
@RestController
@RequestMapping("/hello")
public class ContactsController {

    @Autowired
    IContactsService contactsService;

    @RequestMapping(headers="Accept=application/json",  produces = "application/json")
    public @ResponseBody String ready() {

        return "Ready";
    }

    @RequestMapping(value = "/contacts", method = RequestMethod.GET, headers = "Accept=application/json")
    public List<Contact> getPersonDetail(@RequestParam(value = "nameFilter",required = true) String nameFilter,
                                         @RequestParam(value = "limit",required = true) Long limit,
                                         @RequestParam(value = "page",required = false,
                                                 defaultValue = "1") Integer page) {

        System.out.println("In Controller: nameFilter=" + nameFilter);
        long start = System.currentTimeMillis();
        List<Contact> contacts = contactsService.findContacts(nameFilter, limit, page);
        System.out.println("Server proc time:" + (System.currentTimeMillis() - start));
        System.out.println("Return count: " + contacts.size());
        return contacts;
    }

    @RequestMapping(value = "/contact", headers="Accept=application/json",  produces = "application/json")
    public @ResponseBody Contact getPersonDetail() {

        System.out.println("In Controller: contact");
        Contact contact = new Contact(1, "contact");
        System.out.println("In Controller: contact=" + contact);
        return contact;
    }

    @RequestMapping(value = "/fill", headers="Accept=application/json",  produces = "application/json")
    public @ResponseBody String fillRandom(@RequestParam(value = "ins",required = true) int numberOfInserts) {

        contactsService.fillRandom(numberOfInserts);
        System.out.println("In Controller: fiil DB");
        return "ok";
    }

    @RequestMapping(value = "/add", headers="Accept=application/json")
    public @ResponseBody String addContact(@RequestParam(value = "name",required = true) String name) {

        if (name == null){
            return "error";
        }
        Contact contact = new Contact(name);
        contactsService.create(contact);

        return "ok";
    }

}
