package com.paysafe.anoop.cardPayment.dto.input;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerInputDto {
    public CustomerInputDto() {

    }

    @Data
    @AllArgsConstructor
    class DateOfBirth {
        int day;
        int month;
        int year;

        public DateOfBirth() {

        }
    }
    String merchantCustomerId;
    String locale;
    String firstName;
    String middleName;
    String lastName;
    String email;
    DateOfBirth dateOfBirth;
    String phone;
    String ip;
    String gender;
    String nationality;
    String cellPhone;
    public void setDateOfBirth(int day, int month, int year){
        DateOfBirth db = new DateOfBirth();
        db.setMonth(month);db.setDay(day);db.setYear(year);
        this.dateOfBirth = db;
    }
}
