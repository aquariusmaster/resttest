package com.aquariusmaster.resttest.dao;

import com.aquariusmaster.resttest.entity.Contact;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by harkonnen on 17.05.16.
 */
public class JsonResultSetExtractor implements ResultSetExtractor<ObjectMapper> {

    private String nameFilter;

    public JsonResultSetExtractor(String nameFilter) {
        this.nameFilter = nameFilter;
    }

    public ObjectMapper extractData(ResultSet resultSet) throws SQLException, DataAccessException {

        Pattern regex = Pattern.compile(nameFilter, Pattern.COMMENTS | Pattern.DOTALL);
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = mapper.createArrayNode();


//        resultSet.setFetchSize(1000000);
//
        System.out.println("ResultSet Fetch size(changed)=" + resultSet.getFetchSize());

        while(resultSet.next()){

            Matcher regexMatcher = regex.matcher(resultSet.getString("name"));

            if(!regexMatcher.matches()){

                ObjectNode objectNode = mapper.createObjectNode();
                objectNode.put("id", resultSet.getLong("id"));
                objectNode.put("name", resultSet.getString("name"));

                arrayNode.add(objectNode);
            }
        }
        return mapper;

    }


}
