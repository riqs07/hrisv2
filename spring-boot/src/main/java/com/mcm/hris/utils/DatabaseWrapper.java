package com.mcm.hris.utils;

import java.sql.SQLDataException;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

public class DatabaseWrapper {
    private static final Logger log = LoggerFactory.getLogger(DatabaseWrapper.class);
    private static JdbcTemplate db;

    private DataSource dataSource;

    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

//
//    public static DatabaseWrapper getInstance() {
//        if (instance == null) {
//            instance = new DatabaseWrapper();
//        }
//        return instance;
//    }

}
