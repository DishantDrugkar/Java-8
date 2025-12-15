package org.example.jdbc;

import java.sql.*;

public class ViewFromDatabase {
    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/jdbc";
        String username = "postgres";
        String password = "root5113";
        String query = "SELECT * FROM employee;";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection established sucessfully !");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String job_title = resultSet.getString("job_title");
                double salary = resultSet.getDouble("salary");
                System.out.println();
                System.out.println("===========================================");
                System.out.println("ID : " + id);
                System.out.println("Name : " + name);
                System.out.println("Job Title : " + job_title);
                System.out.println("Salary : " + salary);
            }
            resultSet.close();
            statement.close();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }
}
