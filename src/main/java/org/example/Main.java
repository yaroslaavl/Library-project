package org.example;

import org.example.dao.StudentDao;
import org.example.entity.Student;
import org.example.exception.DaoException;

import java.sql.SQLException;
import java.sql.Timestamp;
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
                   choice = scanner.nextInt();
                    if(choice == 1){
                        System.out.println("Trying to find a room....");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        var freeRoom = StudentDao.getInstance();
                        try{
                            Optional<Student> resultSoloRoom = freeRoom.checkSoloRoom();
                            if(resultSoloRoom.isPresent()){
                                System.out.println("If you wish to reserve a room PRESS 1");
                                System.out.println("Back to choosing a room type PRESS 2");
                                int choice1 = scanner.nextInt();
                                if(choice1 == 1){
                                    System.out.println("Please fill in your information");
                                    add(scanner);
                                    //нужно добавить вывод всей информации
                                    //нужно настроить таблицу ROOM
                                    var sql = """
                                            UPDATE library.room
                                            SET occupants_count = 
                                              CASE
                                               WHEN occupants_count = 1
                                                THEN occupants_count = 2
                                               WHEN occupants_count = 0
                                                THEN occupants_count = 1
                                               END 
                                            """;
                                    try(var connection = ConnectionManager.get();
                                        var preparedStatement = connection.prepareStatement(sql)){
                                        preparedStatement.executeUpdate();

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
                    } else if(choice == 2){
                        System.out.println("Trying to find a room....");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        var freeRoom = StudentDao.getInstance();
                        try{
                             Optional<Student> resultNotSoloRoom = freeRoom.checkRoom();
                            if(resultNotSoloRoom.isPresent()){
                                System.out.println("If you wish to reserve a room PRESS 1");
                                System.out.println("Back to choosing a room type PRESS 2");
                                int choice1 = scanner.nextInt();
                                if(choice1 == 1){
                                    System.out.println("Please fill in your information");
                                    add(scanner);
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
        System.out.println(result);
    }
}