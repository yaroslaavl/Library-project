package org.example.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Student {
    private Integer id;
    private String firstName;
    private String lastName;
    private Date settlementDate;
    private String homePhone;
    private Integer room;
    private Integer livingStatusId;

    public Student(Integer id, String firstName, String lastName, Timestamp settlementDate, String homePhone, Integer room,Integer roomTypeId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.settlementDate = settlementDate;
        this.homePhone = homePhone;
        this.room = room;
        this.livingStatusId = roomTypeId;
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
                ", roomTypeId=" + livingStatusId +
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

    public Date getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Date settlementDate) {
        this.settlementDate = settlementDate;
    }

    public String getHomePhone() {
        return homePhone;
    }

//    public void setHomePhone(String homePhone) {
//        String formattedNumberOfPhone = homePhone.replaceAll("[^0-9]","");
//         if(formattedNumberOfPhone.length() <=3){
//             this.homePhone = formattedNumberOfPhone;
//             return;
//         }
//        StringBuilder formattedBuilder = new StringBuilder();
//        int count = 0;
//        for(char c:formattedNumberOfPhone.toCharArray()){
//            if(count == 3){
//                formattedBuilder.append("-");
//                count = 0;
//            }
//            formattedBuilder.append(c);
//            count++;
//        }
//        this.homePhone = formattedBuilder.toString();
//    }
    public void setHomePhone(String homePhone){
        String formattedNumberOfPhone = homePhone.replaceAll("[^0-9]","");
        Pattern regex = Pattern.compile("\\d{9}");
        Matcher matcher = regex.matcher(formattedNumberOfPhone);
        try{
            if (matcher.matches()) {
                String homePhoneFormatted = formattedNumberOfPhone.substring(0, 3) + "-" +
                        formattedNumberOfPhone.substring(3, 6) + "-" +
                        formattedNumberOfPhone.substring(6);
               this.homePhone = homePhoneFormatted;
            }
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public Integer getLivingStatusId() {
        return livingStatusId;
    }

    public void setLivingStatusId(Integer livingStatusId) {
        this.livingStatusId = livingStatusId;
    }
}

