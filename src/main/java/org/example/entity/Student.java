package org.example.entity;

import java.sql.Timestamp;

public class Student {
    private Integer id;
    private String firstName;
    private String lastName;
    private Timestamp settlementDate;
    private String homePhone;
    private Integer room;
    private Integer roomTypeId;

    public Student(Integer id, String firstName, String lastName, Timestamp settlementDate, String homePhone, Integer room,Integer roomTypeId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.settlementDate = settlementDate;
        this.homePhone = homePhone;
        this.room = room;
        this.roomTypeId = roomTypeId;
    }

    public Student() {
        
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", settlementDate=" + settlementDate +
                ", homePhone='" + homePhone + '\'' +
                ", room=" + room +
                ", roomTypeId=" + roomTypeId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Timestamp getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Timestamp settlementDate) {
        this.settlementDate = settlementDate;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        String formattedNumberOfPhone = homePhone.replaceAll("[^0-9]","");
         if(formattedNumberOfPhone.length() <=3){
             this.homePhone = formattedNumberOfPhone;
             return;
         }
        StringBuilder formattedBuilder = new StringBuilder();
        int count = 0;
        for(char c:formattedNumberOfPhone.toCharArray()){
            if(count == 3){
                formattedBuilder.append("-");
                count = 0;
            }
            formattedBuilder.append(c);
            count++;
        }
        this.homePhone = formattedBuilder.toString();
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public Integer getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Integer roomTypeId) {
        this.roomTypeId = roomTypeId;
    }
}

