package org.example.dao;
import org.example.ConnectionManager;
import org.example.entity.Student;
import org.example.exception.DaoException;
import java.sql.SQLException;
import java.util.Optional;

public class StudentDao {
    private static final StudentDao INSTANCE = new StudentDao();

    private static final String CHECK_FREE_ROOM_SQL = """
                    SELECT room_number
                    from library.room
                    WHERE occupants_count = ?
                    """;
    private static final String CHECK_SOLO_ROOM_SQL = """
                    SELECT room_number
                    from library.room
                    WHERE occupants_count = ?
                    """;
    private static final String NEW_STUDENT_SQL = """
            INSERT into library.student(first_name, last_name, settlement_date, home_phone)
             values(?,?,current_date,?)
            """;
    public StudentDao(){

    }
    public static StudentDao getInstance() {
        return INSTANCE;
    }

   public Optional<Student> checkRoom() {
       try (var connection = ConnectionManager.open();
            var preparedStatement = connection.prepareStatement(CHECK_FREE_ROOM_SQL)) {
           preparedStatement.setObject(1, 1);

           var resultSet = preparedStatement.executeQuery();
           Student freeRoom = null;
           while (resultSet.next()) {
               int count = resultSet.getInt(1);
               if (count > 0) {
                   System.out.println("Room where only 1 student: " + count);
                   freeRoom = new Student();
               }
           }
           return Optional.ofNullable(freeRoom);
       } catch (DaoException | SQLException e) {
           throw new RuntimeException(e);
       }
   }
    public Optional<Student> checkSoloRoom() {
        try (var connection = ConnectionManager.open();
             var preparedStatement = connection.prepareStatement(CHECK_SOLO_ROOM_SQL)) {
            preparedStatement.setObject(1, 0);

            var resultSet = preparedStatement.executeQuery();
            Student freeRoom = null;
            while (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count > 0) {
                    System.out.println("Free room: " + count);
                    freeRoom = new Student();
                }
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

}

