package com.aquariusmaster.resttest.tests;

import com.aquariusmaster.resttest.entity.Contact;
import com.aquariusmaster.resttest.services.IContactsService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by harkonnen on 18.05.16.
 */
public class ContactTest extends TestCase {

    @Test
    public void testContact(){

        Contact contact = new Contact(1,"Alexander");
        assertEquals(1, contact.getId());
        assertEquals("Alexander", contact.getName());

        Contact contact1 = new Contact();
        contact1.setId(2);
        contact1.setName("Cohen");
        assertEquals(2, contact1.getId());
        assertEquals("Cohen", contact1.getName());

        assertNotEquals(contact, contact1);

    }


}