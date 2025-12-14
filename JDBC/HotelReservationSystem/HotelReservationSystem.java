package org.example.jdbc.hotelReservationSystem;

import java.sql.*;
import java.util.Scanner;

public class HotelReservationSystem {
    private static final String url = "jdbc:postgresql://localhost:5432/jdbc";
    private static final String username = "postgres";
    private static final String password = "root5113";


    public static void main(String[] args) throws InterruptedException{

        try{
            Connection connection = DriverManager.getConnection(url,username,password);
            while(true){
                System.out.println();
                System.out.println("HOTEL RESERVATION SYSTEM");
                Scanner scanner = new Scanner(System.in);
                System.out.println("1. Reserve a Room");
                System.out.println("2. View Reservations");
                System.out.println("3. Get Room Number");
                System.out.println("4. Update Reservations");
                System.out.println("5. Delete Reservations");
                System.out.println("0. EXIT");
                System.out.print("Choose an Option : ");
                int choice = scanner.nextInt();

                switch(choice){
                    case 1:
                        reserveRoom(connection, scanner);
                        break;

                    case 2:
                        viewReservations(connection);
                        break;

                    case 3:
                        getRoomNumber(connection, scanner);
                        break;

                    case 4:
                        updateReservations(connection, scanner);
                        break;

                    case 5:
                        deleteReservations(connection, scanner);
                        break;

                    case 0:
                        exit();
                        scanner.close();
                        return;

                    default:
                        System.out.println("INVALID CHOICE !!!");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void reserveRoom(Connection connection, Scanner scanner){
        try{
            System.out.print("Enter Guest Name : ");
            String guestName = scanner.next();
            scanner.nextLine();
            System.out.print("Enter Room Number : ");
            int roomNumber = scanner.nextInt();
            System.out.print("Enter Contact Number : ");
            String contactNumber = scanner.next();


            String sql = "INSERT INTO reservations (guest_name, room_number, contact_number) " +
                    "VALUES ('" + guestName + "' , " + roomNumber + ", '" + contactNumber + "')";

            try(Statement statement = connection.createStatement()){
                int rowsAffected = statement.executeUpdate(sql);

                if(rowsAffected > 0){
                    System.out.println("Reservation Successful !!!");
                } else {
                    System.out.println("Reservation Failed");
                }
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }



    public static void viewReservations(Connection connection) throws SQLException{
        String sql = "SELECT reservation_id, guest_name, room_number, contact_number, reservation_date FROM reservations;";

        try(Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);

            System.out.println("Current Reservations");
            System.out.println("+----------------+----------------+-------------+----------------+------------------------------------+");
            System.out.println("| Reservation ID |   Guest Name   | Room Number | Contact Number |          Reservation Date          |");
            System.out.println("+----------------+----------------+-------------+----------------+------------------------------------+");

            while(resultSet.next()){
                int reservationId = resultSet.getInt("reservation_id");
                String name = resultSet.getString("guest_name");
                int roomNumber = resultSet.getInt("room_number");
                String contactNumber = resultSet.getString("contact_number");
                String reservationDate = resultSet.getTimestamp("reservation_date").toString();

                System.out.printf("| %-14d | %-15s | %-13d | %-20s | %-19s |\n",
                                  reservationId, name, roomNumber, contactNumber, reservationDate);
            }
            System.out.println("+----------------+----------------+-------------+----------------+-------------------------------------+");

        }
    }

    public static void getRoomNumber(Connection connection, Scanner scanner){
        try{
            System.out.print("Enter Reservation Id : ");
            int reservationId = scanner.nextInt();
            System.out.print("Enter Guest Name : ");
            String name = scanner.next();

            String sql = "SELECT room_number FROM reservations " +
                    "WHERE reservation_id = " + reservationId +
                    " AND guest_name = '" + name +"'";

            try(Statement statement = connection.createStatement()){
                ResultSet resultSet = statement.executeQuery(sql);

                if(resultSet.next()){
                    int roomNumber = resultSet.getInt("room_number");
                    System.out.println("Room Number for Reservation Id " + reservationId +
                            " and Guest " + name + " is : " + roomNumber);
                } else {
                    System.out.println("Reservation NOT FOUND for given Reservation Id and Guest Name");
                }
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateReservations(Connection connection, Scanner scanner){
        try{
            System.out.print("Enter Reservation Id to Update : ");
            int reservationId = scanner.nextInt();
            scanner.nextLine();

            if(!reservationExists(connection, reservationId)){
                System.out.println("Reservation ID NOT FOUND");
                return;
            }

            System.out.print("Enter NEW Guest Name :");
            String guestName = scanner.next();
            System.out.print("Enter Your New Room Number : ");
            int roomNumber = scanner.nextInt();
            System.out.print("Enter NEW Contact Number : ");
            String contactNumber = scanner.next();

            String sql = "UPDATE reservations SET guest_name = '" + guestName + "', " +
                    "room_number = " + roomNumber + ", " +
                    "contact_number = '" + contactNumber + "' " +
                    "WHERE reservation_id = " + reservationId;

            try(Statement statement = connection.createStatement()){
                int rowsAffected = statement.executeUpdate(sql);
                if(rowsAffected > 0 ){
                    System.out.println("UPDATE Successfull ");
                }else {
                    System.out.println("Reservation Failed");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteReservations(Connection connection, Scanner scanner){
        try{
            System.out.print("Enter Reservation ID to Delete : ");
            int reservationId = scanner.nextInt();

            if(!reservationExists(connection,reservationId)){
                System.out.println("Reservation Not Found For given Id");
                return;
            }

            String sql = "DELETE FROM reservations WHERE reservation_id = " + reservationId;
            try(Statement statement = connection.createStatement()){
                int rowsAffected = statement.executeUpdate(sql);

                if(rowsAffected > 0){
                    System.out.println("DELETE Successfully");
                }else {
                    System.out.println("DELETION Failed");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean reservationExists(Connection connection, int reservationId){
        try{
            String sql = "SELECT reservation_id FROM reservations WHERE reservation_id = " + reservationId;

            try(Statement statement = connection.createStatement()){
                ResultSet resultSet = statement.executeQuery(sql);

                return resultSet.next(); // If there is result, reservation exists
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void exit() throws InterruptedException{
        System.out.print("Exiting System");
        int i=5;
        while(i!=0){
            System.out.print(".");
            Thread.sleep(450);
            i--;
        }
        System.out.println();
        System.out.println("THANK YOU For Using Hotel Reservation System. ");
    }
}
