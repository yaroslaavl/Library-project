package org.example.dao;

import org.example.ConnectionManager;
import org.example.exception.DaoException;

import java.sql.SQLException;
import java.util.Optional;

public class StudentDao {
    private static final StudentDao INSTANCE = new StudentDao();

    public StudentDao(){

    }
    public static StudentDao getInstance() {
        return INSTANCE;
    }

   public Optional<StudentDao> spaceСheck(){
            var sql = """
                    SELECT count(room_number)
                    from room
                    WHERE occupants_count = ?
                    """;
            try(var connection = ConnectionManager.get();
                var preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setObject(1,0);
                var resultSet =  preparedStatement.executeQuery();
                StudentDao freeRoom = new StudentDao();
                if(resultSet.next()){
                    int count = resultSet.getInt(1);
                    if(count > 0){
                        System.out.println(count);
                    }
                }
                  return Optional.ofNullable(freeRoom);
            } catch (DaoException | SQLException e) {
                throw new RuntimeException(e);
            }
   }


}
