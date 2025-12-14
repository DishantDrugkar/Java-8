package org.example.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertIntoDatabase {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/jdbc";
        String username = "postgres";
        String password = "root5113";
       // String query = "INSERT INTO employee(id, name, job_title, salary) VALUES (1, 'Dishant', 'Software Engineer', 45000.0);";

        String query =
                "INSERT INTO employee (id, name, job_title, salary) VALUES " +
                        "(2, 'Rohit', 'DevOps Engineer', 43000.0), " +
                        "(3, 'Rahul', 'Backend Engineer', 50000.0), " +
                        "(4, 'Amit', 'Data Analyst', 48000.0);";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            int rowsAffected = statement.executeUpdate(query);

            if(rowsAffected > 0){
                System.out.println("Insert Successfully " + rowsAffected + " row's Affected");
            } else{
                System.out.println("Insertion Failed !!!");
            }

            statement.close();
            connection.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
