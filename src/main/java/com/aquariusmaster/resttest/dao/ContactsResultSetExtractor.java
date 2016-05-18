package com.aquariusmaster.resttest.dao;

import com.aquariusmaster.resttest.entity.Contact;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by harkonnen on 16.05.16.
 */
public class ContactsResultSetExtractor<T> implements ResultSetExtractor<List<Contact>>{

    private String nameFilter;
    private long limit;
    private int page;
    long start;

    public ContactsResultSetExtractor(String nameFilter, long limit, int page) {

        this.nameFilter = nameFilter;
        this.limit = limit;
        this.page = page;
        start = System.currentTimeMillis();
    }

    public List<Contact> extractData(ResultSet resultSet) throws SQLException, DataAccessException {

        Pattern regex = Pattern.compile(nameFilter, Pattern.COMMENTS | Pattern.DOTALL);
        List<Contact> list = new ArrayList<>();

        long skip = 0;
        if (page > 1){
            skip = (page-1)*limit;
        }
        while(resultSet.next()){

            if (limit == 0) {
                break;
            }

            Matcher regexMatcher = regex.matcher(resultSet.getString(2));
            if(!regexMatcher.matches() & skip <= 0){
                Contact contact = new Contact();
                contact.setId(resultSet.getLong(1));
                contact.setName(resultSet.getString(2));
                list.add(contact);
                limit--;
            }else if (!regexMatcher.matches()){
                skip--;
            }
        }
        System.out.println("ResultSet processing time: " + (System.currentTimeMillis() - start));
        return list;
    }

}
