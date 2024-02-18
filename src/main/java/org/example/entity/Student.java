package org.example.entity;

import java.time.LocalDateTime;

public class Student {
    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDateTime settlementDate;
    private String homePhone;
    private Integer room;
    private Integer roomTypeId;

    public Student(Integer id, String firstName, String lastName, LocalDateTime settlementDate, String homePhone, Integer room,Integer roomTypeId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.settlementDate = settlementDate;
        this.homePhone = homePhone;
        this.room = room;
        this.roomTypeId = roomTypeId;
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

    public LocalDateTime getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(LocalDateTime settlementDate) {
        this.settlementDate = settlementDate;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
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

