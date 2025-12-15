package org.example.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteFromDatabase {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/jdbc";
        String username = "postgres";
        String password = "root5113";
        String query = "DELETE FROM employee WHERE id=4;";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            int rowsAffected = statement.executeUpdate(query);

            if(rowsAffected > 0){
                System.out.println("DELETE Successfull " + rowsAffected + " Row's Affected");
            } else {
                System.out.println("DELETION FAILED");
            }

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
