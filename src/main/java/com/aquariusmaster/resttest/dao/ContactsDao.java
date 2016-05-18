package com.aquariusmaster.resttest.dao;

import com.aquariusmaster.resttest.entity.Contact;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by harkonnen on 16.05.16.
 */
public interface ContactsDao {

    List<Contact> findContacts(String nameFilter, long limit, int page);
    boolean create(Contact contact);
    void create(List<Contact> contacts);
    void fillRandom(int numberOfInserts);

}
