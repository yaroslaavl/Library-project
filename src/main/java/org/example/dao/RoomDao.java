package org.example.dao;

public class RoomDao {
    private static final RoomDao INSTANCE = new RoomDao();

    private RoomDao(){

    }
    public static RoomDao getInstance() {
        return INSTANCE;
    }
}
