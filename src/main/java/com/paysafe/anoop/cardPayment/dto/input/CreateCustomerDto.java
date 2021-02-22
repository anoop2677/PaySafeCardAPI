package com.paysafe.anoop.cardPayment.dto.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class DateOfBirth{
        int day;int month;int year;

        public String getDat() {
            String month = String.valueOf(this.getMonth());
            String day = String.valueOf(this.getDay());
            if(month.length() == 1){
                month = "0" + month;
            }
            if(day.length() == 1){
                day = "0" + day;
            }
            return month+"/"+day+"/"+this.getYear();
        }
    }
    String id;
    String merchantCustomerId;
    String locale;
    String firstName;
    String lastName;
    String middleName;
    DateOfBirth dateOfBirth;
    String email;
    String phone;
    String ip;
    String gender;
    String nationality;
    String cellPhone;
    String status;
    String paymentToken;
    public void setDateOfBirth(int day, int month, int year){
        DateOfBirth db = new DateOfBirth();
        db.setMonth(month);db.setDay(day);db.setYear(year);
        this.dateOfBirth = db;
    }
}
