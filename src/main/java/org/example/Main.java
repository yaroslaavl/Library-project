package org.example;

import org.example.dao.StudentDao;
import org.example.entity.Student;
import org.example.exception.DaoException;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
           int choice;
           while (true){
               System.out.println("MENU");
               System.out.println("If you are a student PRESS 1");
               System.out.println("If you are a Administrator PRESS 2");
               choice = scanner.nextInt();

               if(choice == 1){
                   System.out.println("STUDENT");
                   System.out.println("Do you want to leave alone or with someone?");
                   System.out.println("Press 1 if alone");
                   System.out.println("Press 2 if not alone");
                   int choiceTypeOfRoom = scanner.nextInt();
                    if(choiceTypeOfRoom == 1){
                        System.out.println("Trying to find a room....");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        var freeRoom = StudentDao.getInstance();
                        try{
                            Optional<Student> resultSoloRoom = freeRoom.checkSoloRoom();
                            System.out.println("Choose a room");
                            int roomNumber = scanner.nextInt();
                            if(resultSoloRoom.isPresent()){
                                System.out.println("If you wish to reserve a room PRESS 1");
                                System.out.println("Back to choosing a room type PRESS 2");
                                int choice1 = scanner.nextInt();
                                if(choice1 == 1){
                                    System.out.println("Please fill in your information");
                                    add(scanner);

                                    var sql = """
                                            UPDATE library.room
                                            SET occupants_count = 1
                                               WHERE room_number = ? AND occupants_count = 0    
                                            """;
                                    try(var connection = ConnectionManager.get();
                                        var preparedStatement = connection.prepareStatement(sql);
                                        ){
                                        preparedStatement.setInt(1,roomNumber);
                                        preparedStatement.executeUpdate();
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                    var sql1 = """
                                            UPDATE library.student
                                            SET room = (SELECT library.room.id FROM library.room WHERE room_number = ?)
                                            WHERE settlement_date = (SELECT MAX(settlement_date) FROM library.student)
                                            """;
                                    try(var connection = ConnectionManager.get();
                                        var preparedStatement = connection.prepareStatement(sql1);
                                    ){
                                        preparedStatement.setInt(1,roomNumber);
                                        preparedStatement.executeUpdate();
                                } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                     }
                                    var sql2 = """
                                            UPDATE library.student
                                            SET living_status_id = ?
                                            WHERE settlement_date = (SELECT MAX(settlement_date) FROM library.student);
                                            """;
                                    try(var connection = ConnectionManager.get();
                                        var preparedStatement = connection.prepareStatement(sql2);
                                    ){
                                        preparedStatement.setInt(1,choiceTypeOfRoom);
                                        preparedStatement.executeUpdate();
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                    var sql3 = """
                                            SELECT library.student.id,first_name,last_name,settlement_date,home_phone,room_number,living_status,price_per_month
                                            FROM library.student
                                                     RIGHT JOIN library.room_type rt on rt.id = student.living_status_id
                                                     JOIN library.room r on r.id = student.room
                                            WHERE settlement_date = (SELECT MAX(settlement_date) FROM library.student);
                                            
                                            """;
                                    try(var connection = ConnectionManager.get();
                                        var preparedStatement = connection.prepareStatement(sql3);
                                    ){
                                        var resultSet= preparedStatement.executeQuery();
                                        while (resultSet.next()) {
                                            int id = resultSet.getInt("id");
                                            String firstName = resultSet.getString("first_name");
                                            String lastName = resultSet.getString("last_name");
                                            LocalDate date = LocalDate.now();
                                            System.out.print(date);
                                            String homePhone = resultSet.getString("home_phone");
                                            int roomNumber1 = resultSet.getInt("room_number");
                                            String livingStatus= resultSet.getString("living_status");
                                            double price = resultSet.getDouble("price_per_month");
                                            System.out.println
                                                    ("Your information = " +
                                                            "{ " +
                                                            "ID: "+id+
                                                            ", First Name: "+firstName+
                                                            ", Last Name: "+lastName+
                                                            ", Date: "+date+
                                                            ", Phone: "+homePhone+
                                                            ", Room: "+roomNumber1+
                                                            ", Living Status: "+livingStatus+
                                                            ", Price Per Month: "+price+
                                                            " }");
                                        }
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                } else if(choice1 == 2){
                                        continue;
                                }
                            } else {
                                System.out.println("No free room");
                            }
                        } catch (DaoException e){
                            throw new RuntimeException();
                        }
                    } else if(choiceTypeOfRoom == 2){
                        System.out.println("Trying to find a room....");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        var freeRoom = StudentDao.getInstance();
                        try{
                             Optional<Student> resultNotSoloRoom = freeRoom.checkRoom();
                            System.out.println("Choose a room");
                            int roomNumber = scanner.nextInt();
                            if(resultNotSoloRoom.isPresent()){
                                System.out.println("If you wish to reserve a room PRESS 1");
                                System.out.println("Back to choosing a room type PRESS 2");
                                int choice1 = scanner.nextInt();
                                if(choice1 == 1){
                                    System.out.println("Please fill in your information");
                                    add(scanner);
                                    var sql = """
                                            UPDATE library.room
                                            SET occupants_count = 2
                                               WHERE room_number = ? AND occupants_count = 1    
                                            """;
                                    try(var connection = ConnectionManager.get();
                                        var preparedStatement = connection.prepareStatement(sql);
                                        ){
                                        preparedStatement.setInt(1,roomNumber);
                                        preparedStatement.executeUpdate();

                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                    var sql1 = """
                                            UPDATE library.student
                                            SET room = (SELECT library.room.id FROM library.room WHERE room_number = ?)
                                            WHERE settlement_date = (SELECT MAX(settlement_date) FROM library.student)
                                            """;
                                    try(var connection = ConnectionManager.get();
                                        var preparedStatement = connection.prepareStatement(sql1);
                                    ){
                                        preparedStatement.setInt(1,roomNumber);
                                        preparedStatement.executeUpdate();
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                    var sql2= """
                                            UPDATE library.student
                                            SET living_status_id = ?
                                            WHERE settlement_date = (SELECT MAX(settlement_date) FROM library.student);
                                            """;
                                    try(var connection = ConnectionManager.get();
                                        var preparedStatement = connection.prepareStatement(sql2);
                                    ){
                                        preparedStatement.setInt(1,choiceTypeOfRoom);
                                        preparedStatement.executeUpdate();
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                    var sql3 = """
                                            SELECT library.student.id,first_name,last_name,settlement_date,home_phone,room_number,living_status,price_per_month
                                            FROM library.student
                                                     RIGHT JOIN library.room_type rt on rt.id = student.living_status_id
                                                     JOIN library.room r on r.id = student.room
                                            WHERE settlement_date = (SELECT MAX(settlement_date) FROM library.student);
                                            """;
                                    try(var connection = ConnectionManager.get();
                                        var preparedStatement = connection.prepareStatement(sql3);
                                    ){
                                       var resultSet= preparedStatement.executeQuery();
                                       while (resultSet.next()){
                                           int id = resultSet.getInt("id");
                                           String firstName = resultSet.getString("first_name");
                                           String lastName = resultSet.getString("last_name");
                                           LocalDate date = LocalDate.now();
                                           System.out.print(date);
                                           String homePhone = resultSet.getString("home_phone");
                                           int roomNumber1 = resultSet.getInt("room_number");
                                           String livingStatus = resultSet.getString("living_status");
                                           double price = resultSet.getDouble("price_per_month");
                                           System.out.println
                                                   ("Your information = " +
                                                           "{ " +
                                                           "ID: "+id+
                                                           ", First Name: "+firstName+
                                                           ", Last Name: "+lastName+
                                                           ", Date: "+date+
                                                           ", Phone: "+homePhone+
                                                           ", Room: "+roomNumber1+
                                                           ", Living Status: "+livingStatus+
                                                           ", Price Per Month: "+price+
                                                           " }");
                                       }
                                    } catch (SQLException e) {
                                        throw new RuntimeException(e);
                                    }
                                } else if(choice1 == 2){
                                    continue;
                                }
                            } else {
                                System.out.println("No free room");
                            }
                        } catch (DaoException e){
                            throw new RuntimeException();
                        }
                    }
               } else if (choice == 2){
                   System.out.println("Administrator");
                   System.out.println("To connect write a password");
               }
           }
    }
    private static void add(Scanner scanner){
        var studentDao = StudentDao.getInstance();
        Student student = new Student();
        System.out.println("Enter your name: ");
        student.setFirstName(scanner.next());
        System.out.println("Enter your last name: ");
        student.setLastName(scanner.next());
        student.setSettlementDate(new Timestamp(System.currentTimeMillis()));
        System.out.println("Enter your phone number: ");
        student.setHomePhone(scanner.next());
         var result = studentDao.addNewStudent(student);

    }
}