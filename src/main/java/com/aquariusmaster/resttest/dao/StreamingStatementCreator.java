package com.aquariusmaster.resttest.dao;

import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by harkonnen on 17.05.16.
 */
class StreamingStatementCreator implements PreparedStatementCreator {
    private final String sql;

    public StreamingStatementCreator(String sql) {
        this.sql = sql;
    }

    @Override
    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
        final PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        statement.setFetchSize(Integer.MIN_VALUE);
        return statement;
    }
}

