package org.example.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PreparedStatements {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/jdbc";
        String username = "postgres";
        String password = "root5113";
        String query = "INSERT INTO employee (id, name, job_title, salary) VALUES (?, ?, ?, ?);";

        try{
            Connection connection = DriverManager.getConnection(url,username,password);
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the ID : ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter the Name : ");
            String name = scanner.nextLine();
            System.out.print("Enter the Job Title : ");
            String job_title = scanner.nextLine();
            System.out.print("Enter the Salary : ");
            double salary = scanner.nextDouble();

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,name);
            preparedStatement.setString(3,job_title);
            preparedStatement.setDouble(4,salary);

            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("Insert Successful " + rowsAffected + " Row's Affected");
            } else {
                System.out.println("Insertion Failed");
            }

            preparedStatement.close();
            connection.close();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
