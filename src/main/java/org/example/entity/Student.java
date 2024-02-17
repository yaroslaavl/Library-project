package org.example.entity;

import java.time.LocalDateTime;

public class Student {
    private Integer id;
    private String firstName;
    private String lastName;
    private LocalDateTime settlementDate;
    private String homePhone;
    private Long roomNumber;
    private Integer roomNumberId;

    public Student(Integer id, String firstName, String lastName, LocalDateTime settlementDate, String homePhone, Long roomNumber,Integer roomNumberId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.settlementDate = settlementDate;
        this.homePhone = homePhone;
        this.roomNumber = roomNumber;
        this.roomNumberId = roomNumberId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", settlementDate=" + settlementDate +
                ", homePhone='" + homePhone + '\'' +
                ", roomNumber=" + roomNumber +
                ", roomNumberId=" + roomNumberId +
                '}';
    }
//
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

    public Long getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Long roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getRoomNumberId() {
        return roomNumberId;
    }

    public void setRoomNumberId(Integer roomNumberId) {
        this.roomNumberId = roomNumberId;
    }
}

