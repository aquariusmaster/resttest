package com.aquariusmaster.resttest.dao;

import com.aquariusmaster.resttest.entity.Contact;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;


/**
 * Created by harkonnen on 16.05.16.
 */

@Repository("contactsDao")
public class JdbcContactsDao implements ContactsDao{

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Contact> findContacts(String nameFilter, long limit, int page) {

        long start = System.currentTimeMillis();

        String sql = "SELECT * FROM resttest.contacts";

        List<Contact> list = jdbcTemplate.query(new StreamingStatementCreator(sql), new ContactsResultSetExtractor<List<Contact>>(nameFilter, limit, page));
        System.out.println("DAO procesing time: " + (System.currentTimeMillis() - start));
        return list;

    }

    public boolean create(Contact contact){
        return jdbcTemplate.update("insert into resttest.contacts (name) values (?)", new Object[] { contact.getName()}) == 1;
    }


    public void create(List<Contact> contacts){


        final StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO resttest.contacts(name) VALUES ");

        for (int i = 0; i < contacts.size(); i++) {

            sb.append("('" + contacts.get(i).getName() + "')");

            if(i < contacts.size() - 1){
                sb.append(",");
            }else{
                sb.append(";");
            }

        }

        jdbcTemplate.update(sb.toString());

    }

    public void fillRandom(int numberOfInserts){

        String[] names = {"Ivan", "Peter", "Andrey", "Anna", "Lena", "Eugene", "Lexa", "Vlad", "Yaroslav", "Anton", "Victor"};
        List<Contact> contacts = new ArrayList<>(numberOfInserts);
        Random rand = new Random();
        for (int i = 0; i < numberOfInserts; i++){
            contacts.add(new Contact(names[(rand.nextInt(names.length))]));
        }
        create(contacts);
    }


}
