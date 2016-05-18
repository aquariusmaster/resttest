package com.aquariusmaster.resttest.tests;

import com.aquariusmaster.resttest.dao.ContactsDao;
import com.aquariusmaster.resttest.entity.Contact;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by harkonnen on 18.05.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:database-context.xml"})
public class ContactsDaoTest extends TestCase {

    @Autowired
    private ContactsDao contactsDao;
    @Autowired
    private DataSource dataSource;

    private static long CONTACTS_LIMIT = 100;


    @Before
    public void setUp() throws Exception {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        jdbcTemplate.execute("delete from resttest.contacts");

    }

    @Test
    public void testFindContacts() throws Exception {

        contactsDao.fillRandom(5);
        List<Contact> contacts = contactsDao.findContacts("", CONTACTS_LIMIT, 1); // return all contacts
        assertEquals(5, contacts.size());

        String regex = "^[0-9]\\w+"; // filter name that start with digit
        contacts = contactsDao.findContacts(regex, CONTACTS_LIMIT, 1); // return all contacts which NOT match to regex
        assertEquals(5, contacts.size());

        contactsDao.create(new Contact("1_Ivan"));
        regex = "^\\D\\w+"; // filter name that start with digit
        contacts = contactsDao.findContacts(regex, CONTACTS_LIMIT, 1); // return all contacts which NOT match to regex
        assertEquals(1, contacts.size());

        contactsDao.create(new Contact("9 9 9"));
        regex = "^\\D\\w+"; // filter name that start with digit
        contacts = contactsDao.findContacts(regex, CONTACTS_LIMIT, 1); // return all contacts which NOT match to regex
        assertEquals(2, contacts.size());

        regex = "^\\D\\w+"; // filter name that start with digit
        contacts = contactsDao.findContacts(regex, 1, 1); // return all contacts which NOT match to regex and with output limit per page
        assertEquals(1, contacts.size());

        contactsDao.create(new Contact("777"));
        regex = "^\\D\\w+"; // filter name that start with digit
        contacts = contactsDao.findContacts(regex, CONTACTS_LIMIT, 1); // return all contacts which NOT match to regex
        assertEquals(3, contacts.size());

        contacts = contactsDao.findContacts(regex, 2, 2); // return all contacts which NOT match to regex and show second page and limit 2 contact per page
        assertEquals(1, contacts.size());

        contactsDao.create(new Contact("9DDD"));
        contacts = contactsDao.findContacts(regex, 2, 2); // return all contacts which NOT match to regex and show second page and limit 2 contact per page
        assertEquals(2, contacts.size());

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        regex = ".*"; // filter name that start with digit
        int result = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM resttest.contacts WHERE 'name' NOT REGEXP '" + regex + "'", Integer.class);
        contacts = contactsDao.findContacts(regex, CONTACTS_LIMIT, 1); // return all contacts which NOT match to regex
        assertEquals(result, contacts.size());

    }

    @Test
    public void testFillRandom() throws Exception {

        long initSize = contactsDao.findContacts("", CONTACTS_LIMIT, 1).size(); // size of all contacts
        contactsDao.fillRandom(5);
        long finalSize = contactsDao.findContacts("", CONTACTS_LIMIT, 1).size(); // size of all contacts
        assertEquals(initSize, finalSize - 5);
    }

    @Test
    public void testCreate() throws Exception {

        Contact contact = new Contact("Test Very dry beaver");
        contactsDao.create(contact);
        List<Contact> retrivedList = contactsDao.findContacts("", CONTACTS_LIMIT, 1); // return all contact
        Contact retrivedContact = retrivedList.get(retrivedList.size() - 1);
        assertEquals(contact.getName(), retrivedContact.getName());


    }
}