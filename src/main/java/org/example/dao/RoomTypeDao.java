package org.example.dao;

public class RoomTypeDao {
    private static final RoomTypeDao INSTANCE = new RoomTypeDao();

    private RoomTypeDao(){

    }
    public static RoomTypeDao getInstance() {
        return INSTANCE;
    }
}
