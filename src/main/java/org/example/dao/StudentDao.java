package org.example.dao;

public class StudentDao {
    private static final StudentDao INSTANCE = new StudentDao();

    private StudentDao(){

    }
    public static StudentDao getInstance(){
        return INSTANCE;
    }



}
