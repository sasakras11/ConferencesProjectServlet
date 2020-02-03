package com;


import com.dao.CrudPageableConferenceDao;
import com.dao.DataSource;
import com.dao.impl.CrudPageableDaoConferenceImpl;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;

public class Starter {

    public static void main(String[] args) {


        CrudPageableConferenceDao conferenceDao = new CrudPageableDaoConferenceImpl(new DataSource("src/main/resources/db.properties"));
        DataSource dataSource = new DataSource("src/main/resources/db.properties");
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            String dbSchemaQuery = new String(Files.readAllBytes(Paths.get("src/main/resources/dbSchema.sql")));
            System.out.println(dbSchemaQuery);
            statement.executeUpdate(dbSchemaQuery);

            String dbAddQuery = new String(Files.readAllBytes(Paths.get("src/main/resources/addDBValues.sql")));
            System.out.println(dbAddQuery);
            statement.executeUpdate(dbAddQuery);
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}



