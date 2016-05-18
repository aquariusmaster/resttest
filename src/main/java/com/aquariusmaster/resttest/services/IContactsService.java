package com.aquariusmaster.resttest.services;

import com.aquariusmaster.resttest.entity.Contact;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * Created by harkonnen on 16.05.16.
 */
public interface IContactsService {

    List<Contact> findContacts(String nameFilter, long limit, int page);
    void fillRandom(int numberOfInserts);
    boolean create(Contact contact);

//    Contact findContact(String name);
}
