package org.example.dao;
import org.example.ConnectionManager;
import org.example.entity.Room;
import org.example.entity.Student;
import org.example.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StudentDao {
    private static final StudentDao INSTANCE = new StudentDao();

    private static final String CHECK_FREE_ROOM_SQL = """
                    SELECT room_number
                    from library.room
                    WHERE occupants_count = ?
                    order by room.room_number asc
                    """;
    private static final String CHECK_SOLO_ROOM_SQL = """
                    SELECT room_number
                    from library.room
                    WHERE occupants_count = ?
                    order by room.room_number asc
                    """;
    private static final String NEW_STUDENT_SQL = """
            INSERT into library.student(first_name, last_name, settlement_date, home_phone)
             values(?,?,current_date,?)
            """;
    private static final String STUDENTS = """
             SELECT *
             from library.student
            """;
    public StudentDao(){

    }
    public static StudentDao getInstance() {
        return INSTANCE;
    }

   public Optional<Integer> checkRoomWithOneStudent() {
       try (var connection = ConnectionManager.open();
            var preparedStatement = connection.prepareStatement(CHECK_FREE_ROOM_SQL)) {
           preparedStatement.setObject(1, 1);

           var resultSet = preparedStatement.executeQuery();
           Integer freeRoom = null;
           while (resultSet.next()) {
               int roomNum = resultSet.getInt(1);
                   System.out.println("Room where only 1 student: " + roomNum);
                   freeRoom = roomNum;
           }
           return Optional.ofNullable(freeRoom);
       } catch (DaoException | SQLException e) {
           throw new RuntimeException(e);
       }
   }
    public Optional<Integer> checkFreeRoom() {
        try (var connection = ConnectionManager.open();
             var preparedStatement = connection.prepareStatement(CHECK_SOLO_ROOM_SQL)) {
            preparedStatement.setObject(1, 0);

            var resultSet = preparedStatement.executeQuery();
            Integer freeRoom = null;
            while (resultSet.next()) {
                int roomNum = resultSet.getInt(1);
                    System.out.println("Free room: " + roomNum);
                    freeRoom = roomNum;
            }
            return Optional.ofNullable(freeRoom);
        } catch (DaoException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
   public Student addNewStudent(Student student) {
        try(var connection = ConnectionManager.get();
            var preparedStatement = connection.prepareStatement(NEW_STUDENT_SQL)){
             preparedStatement.setString(1,student.getFirstName());
             preparedStatement.setString(2,student.getLastName());
             preparedStatement.setString(3,student.getHomePhone());
             int resultSet = preparedStatement.executeUpdate();
             if(resultSet == 1){
                 System.out.println("You were added successfully");
                 return student;
             } else {
                 System.out.println("Something went wrong");
                 return null;
             }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
   }
    public List<Student> students(){
        try (var connection = ConnectionManager.get();
             var preparedStatement = connection.prepareStatement(STUDENTS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setFirstName(resultSet.getString("first_name"));
                student.setLastName(resultSet.getString("last_name"));
                student.setRoom(resultSet.getInt("room"));
                student.setSettlementDate(resultSet.getDate("settlement_date"));
                student.setHomePhone(resultSet.getString("home_phone"));
                student.setLivingStatusId(resultSet.getInt("living_status_id"));
                students.add(student);
            }
            List<Student> filteredStudent = students.stream()
                    .filter(student -> student.getRoom() != null)
                    .sorted(Comparator.comparingInt(Student::getId))
                    .collect(Collectors.toList());
            return filteredStudent;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

