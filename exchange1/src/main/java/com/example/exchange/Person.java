package com.example.exchange;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
     private String firstname;
     private String lastname;
     private String username;
     private String email;
     private String phoneNumber;
     private String password;
     private String repeatedPassword;
     public static Person[] people = new Person[100];
     public static int peoplecounter = 0;

     public void setFirstName(String firstname){
          this.firstname = firstname;
     }
     public String getFirstName(){
          return firstname;
     }

     public void setEmail(String email) {
          this.email = email;
     }

     public String getEmail() {
          return email;
     }

     public void setLastName(String lastname){
          this.lastname = lastname;
     }
     public String getLastName(){
          return firstname;
     }
     public void setUsername(String username) {
          this.username = username;
     }
     public String getUsername(){
          return username;
     }

     public void setPassword(String password) {
          this.password = password;
     }
     public String getPassword(){
          return password;
     }

     public void setPhoneNumber(String phoneNumber) {
          this.phoneNumber = phoneNumber;
     }

     public String getPhoneNumber() {
          return phoneNumber;
     }

     public void setRepeatedPassword(String repeatedPassword) {
          this.repeatedPassword = repeatedPassword;
     }

     public String getRepeatedPassword() {
          return repeatedPassword;
     }

     public static boolean iswordvalid (String word) {
          Pattern pattern = Pattern.compile("^[a-zA-Z]{1,18}$");
          Matcher matcher = pattern.matcher(word);
          return matcher.find() ;
     }

     public static boolean ispasswordvalid (String password) {
          Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{8,12}$");
          Matcher matcher = pattern.matcher(password);
          return matcher.find() ;
     }


     public static boolean isphonenumbervalid (String phone) {
          Pattern pattern = Pattern.compile("^09[0-9]{9}$");
          Matcher matcher = pattern.matcher(phone) ;
          return matcher.find();
     }


     public static boolean isusernamevalid (String username) {
          Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{5,12}$");
          Matcher matcher = pattern.matcher(username);
          return matcher.find() ;
         // 09^[0-9]{9}$
     }

     public static boolean isemailvalid(String email) {
         final String regexEmail = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z-]+\\.)+.[a-zA-Z]{2,7}$";
         Pattern pattern = Pattern.compile(regexEmail);
         Matcher matcher = pattern.matcher(email);
         return matcher.matches();

     }
     @Override
     public String toString(){
          return "{ " + username + ", " + password + ", " + firstname + ", " + lastname + ", " + email + ", " + phoneNumber + " }";
      }

}

