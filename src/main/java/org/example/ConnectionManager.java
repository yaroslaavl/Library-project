package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class ConnectionManager {
    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";
    private static final String URL_KEY = "db.url";
    private static ArrayBlockingQueue<Connection> pool;
    private static List<Connection> sourceConnections;

    static {
        try {
            Class.forName("org.postgres.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private ConnectionManager(){

    }
    public static Connection get(){
        try {
            return pool.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static void closePool(){
        for(Connection sourceConnection : sourceConnections){
            try {
                sourceConnection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private static Connection open(){
        try {
            return DriverManager.getConnection(
                    PropertiesUtil.get(URL_KEY),
                    PropertiesUtil.get(PASSWORD_KEY),
                    PropertiesUtil.get(USERNAME_KEY)

            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
