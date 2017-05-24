package com.ecoach.app.commapp.Models;

/**
 * Created by David on 5/23/2017.
 */

public class Account {
    public String first_name;
    public String last_name;
    public String email;
    public String phone_number;
    public String dob;
    public String account_type;
    public String Gender;
    public String Address;
    public String institution_code;

    public Account(String fName, String lName, String mail, String phoneNumber, String date, String accountType, String gender
    ,String address, String instiCode){
        first_name = fName;
        last_name = lName;
        email = mail;
        phone_number = phoneNumber;
        dob = date;
        account_type = accountType;
        Gender = gender;
        Address = address;
        institution_code = instiCode;
    }
}
