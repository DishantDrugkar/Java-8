package org.example.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateDataIntoDatabase {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/jdbc";
        String username = "postgres";
        String password = "root5113";
        String query = "UPDATE employee SET job_title = 'AI Engineer', salary = 86000.0 WHERE id = 3;";

        try{
            Connection connection = DriverManager.getConnection(url,username,password);
            Statement statement = connection.createStatement();
            int rowsAffected = statement.executeUpdate(query);

            if(rowsAffected > 0){
                System.out.println("UPDATE Successfull " + rowsAffected + " Row's Affected");
            } else {
                System.out.println("UPDATION FAILED");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
