package org.example.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connectivity {
    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/jdbc";
        String user = "postgres";
        String password = "root5113";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to PostgreSQL successfully!");

            } catch (SQLException e) {
               e.printStackTrace();
        }
    }
}


