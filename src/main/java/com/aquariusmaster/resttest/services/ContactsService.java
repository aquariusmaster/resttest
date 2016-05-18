package com.aquariusmaster.resttest.services;

import com.aquariusmaster.resttest.dao.ContactsDao;
import com.aquariusmaster.resttest.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by harkonnen on 16.05.16.
 */
@Service("contactsService")
public class ContactsService implements IContactsService{

    @Autowired
    private ContactsDao concactsDao;

    @Override
    public List<Contact> findContacts(String nameFilter, long limit, int page) {
        return concactsDao.findContacts(nameFilter, limit, page);
    }

    @Override
    public void fillRandom(int numberOfInserts) {concactsDao.fillRandom(numberOfInserts); }

    @Override
    public boolean create(Contact contact) {return concactsDao.create(contact); }

}
