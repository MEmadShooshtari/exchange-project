package com.example.exchange;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Duration;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Random;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginPage implements Initializable {
    String cap;

    @FXML
    Label time, login, creat, notAMember, ForgotPassForgot, EnterEmail, invalidcaptcha,invalidcaptchasignup, passwordInvalidrecieved, passwordInvalid, repeatedpasswordInvalidforget;
    @FXML
    Label timelable, emailnotfound, usernameSignupInvalid, firstnameInvalid, lastnameInvalid, emailInvalid, phonenumberInvalid, repeatedpasswordInvalid, passwordSignupInvalid, usernameloginInvalid, passwordLoginInvalid;

    @FXML
    TextField passwordfieldlogin,captchatextfield, captchatextfieldsignup, usernamefieldlogin, usernamefieldsignup, passwordfieldsignup, passwordfieldsignuprepeated, email, phonenumber, firstname, lastname, EmailForgottextfield;
    @FXML
    private ImageView captchaimage, captchaimagesignup;

    @FXML
    TextField passwordfieldforget, newpasswordfieldforget, repeatnewpasswordfieldforget;

    @FXML
    Button sumbit, signuplogin, signupsignup, exit, back, forgotpass, continueButton,refresh, refreshsignup, forgetcheckbutton;
    public static int linenumber = 0;
    public static int indexxx = 0;
    public static int number = 0;
    public String newPassword = "";
    String dataAddress = "jdbc:Mysql://localhost:3306/infos";
    String dataUser = "root";
    String dataPassword = "";


    public void onSubmitClicked(ActionEvent e) throws IOException {
        invalidcaptcha.setVisible(false);
        //cap = captchaMaker.generateCaptchaString();
        captchaMaker.captcha(cap, 77, 255,  82);
        if (getloginInfo()) {
            //if (cap.equals(captchatextfield.getText())) {
            Parent backParent = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            Scene backScene = new Scene(backParent);
            Stage window = (Stage) sumbit.getScene().getWindow();
            window.setScene(backScene);
            window.show();
            //}
                //else
                    //invalidcaptcha.setVisible(true);

        }

    }
    public  void oncaptchaclicked(ActionEvent e){//refresh login
        cap = captchaMaker.generateCaptchaString();
        try {
            captchaMaker.captcha(cap, 231, 62,  234);
        } catch (IOException u) {
            throw new RuntimeException(u);
        }
        File file = new File("captcha.png");
        Image image = new Image(file.toURI().toString());
        captchaimage.setImage(image);
        invalidcaptcha.setVisible(false);
        captchatextfield.clear();
    }

    public  void oncaptchasignupclicked(ActionEvent e){//refresh signup
        cap = captchaMaker.generateCaptchaString();
        try {
            captchaMaker.captcha(cap, 231, 62,  234);
        } catch (IOException i) {
            throw new RuntimeException(i);
        }
        File file = new File("captcha.png");
        Image image = new Image(file.toURI().toString());
        captchaimagesignup.setImage(image);
        invalidcaptchasignup.setVisible(false);
        captchatextfieldsignup.clear();
    }

    public boolean getloginInfo() {
        String username = usernamefieldlogin.getText();
        String password = passwordfieldlogin.getText();

        String dataAddress = "jdbc:Mysql://localhost:3306/infos";
        String dataUser = "root";
        String dataPassword = "";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dataAddress, dataUser, dataPassword);
            Statement statement = connection.createStatement();
            //noinspection SqlResolve
            String query = "SELECT `index`, `username` ,`password` FROM `person` ";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                if (resultSet.getString("username").equals(username)) {
                    if (resultSet.getString("password").equals(password)) {
                        usernameloginInvalid.setVisible(false);
                        passwordLoginInvalid.setVisible(false);
                        number = resultSet.getInt("index");
                        return true;
                    } else {
                        usernameloginInvalid.setVisible(false);
                        passwordLoginInvalid.setVisible(true);
                        return false;
                    }
                }
            }

            usernameloginInvalid.setVisible(true);
            passwordLoginInvalid.setVisible(false);
            linenumber = -1;
            return false;


        } catch (SQLException e) {
            System.out.println("error in get info");
            e.printStackTrace();
        }
        return  false;
    }


    public void onSignupLoginClicked(ActionEvent e) {
        cap = captchaMaker.generateCaptchaString();
        try {
            captchaMaker.captcha(cap, 231, 62,  234);
        } catch (IOException i) {
            throw new RuntimeException(i);
        }
        File file = new File("captcha.png");
        Image image = new Image(file.toURI().toString());
        captchaimagesignup.setImage(image);


        login.setVisible(false);
        notAMember.setVisible(false);
        passwordfieldlogin.setVisible(false);
        usernamefieldlogin.setVisible(false);
        sumbit.setVisible(false);
        signuplogin.setVisible(false);
        forgotpass.setVisible(false);
        back.setVisible(false);
        ForgotPassForgot.setVisible(false);
        EnterEmail.setVisible(false);
        EmailForgottextfield.setVisible(false);
        continueButton.setVisible(false);
        back.setVisible(true);
        creat.setVisible(true);
        usernamefieldsignup.setVisible(true);
        passwordfieldsignup.setVisible(true);
        passwordfieldsignuprepeated.setVisible(true);
        signupsignup.setVisible(true);
        firstname.setVisible(true);
        lastname.setVisible(true);
        email.setVisible(true);
        phonenumber.setVisible(true);
        usernameloginInvalid.setVisible(false);
        passwordLoginInvalid.setVisible(false);
        invalidcaptcha.setVisible(false);
        captchaimage.setVisible(false);
        captchatextfield.setVisible(false);
        invalidcaptcha.setVisible(false);
        captchaimagesignup.setVisible(true);
        captchatextfieldsignup.setVisible(true);
        refresh.setVisible(false);
        refreshsignup.setVisible(true);
        passwordfieldforget.setVisible(false);
        newpasswordfieldforget.setVisible(false);
        repeatnewpasswordfieldforget.setVisible(false);
        forgetcheckbutton.setVisible(false);
        emailnotfound.setVisible(false);
    }

    public void onBackClicked(ActionEvent e) {
        cap = captchaMaker.generateCaptchaString();
        try {
            captchaMaker.captcha(cap, 231, 62,  234);
        } catch (IOException q) {
            throw new RuntimeException(q);
        }
        File file = new File("captcha.png");
        Image image = new Image(file.toURI().toString());
        captchaimage.setImage(image);


        login.setVisible(true);
        notAMember.setVisible(true);
        passwordfieldlogin.setVisible(true);
        usernamefieldlogin.setVisible(true);
        sumbit.setVisible(true);
        signuplogin.setVisible(true);
        forgotpass.setVisible(true);
        back.setVisible(false);
        creat.setVisible(false);
        usernamefieldsignup.setVisible(false);
        passwordfieldsignup.setVisible(false);
        passwordfieldsignuprepeated.setVisible(false);
        signupsignup.setVisible(false);
        firstname.setVisible(false);
        lastname.setVisible(false);
        email.setVisible(false);
        phonenumber.setVisible(false);
        back.setVisible(false);
        ForgotPassForgot.setVisible(false);
        EnterEmail.setVisible(false);
        EmailForgottextfield.setVisible(false);
        continueButton.setVisible(false);
        firstnameInvalid.setVisible(false);
        lastnameInvalid.setVisible(false);
        passwordSignupInvalid.setVisible(false);
        repeatedpasswordInvalid.setVisible(false);
        emailInvalid.setVisible(false);
        phonenumberInvalid.setVisible(false);
        usernameSignupInvalid.setVisible(false);
        invalidcaptchasignup.setVisible(false);
        captchaimagesignup.setVisible(false);
        captchatextfieldsignup.setVisible(false);
        invalidcaptchasignup.setVisible(false);
        captchaimage.setVisible(true);
        captchatextfield.setVisible(true);
        refresh.setVisible(true);
        refreshsignup.setVisible(false);
        emailnotfound.setVisible(false);
    }
    public void onForgotClicked(ActionEvent e){
        EmailForgottextfield.clear();
        login.setVisible(false);
        passwordfieldlogin.setVisible(false);
        usernamefieldlogin.setVisible(false);
        sumbit.setVisible(false);
        forgotpass.setVisible(false);
        back.setVisible(true);
        ForgotPassForgot.setVisible(true);
        EnterEmail.setVisible(true);
        EmailForgottextfield.setVisible(true);
        continueButton.setVisible(true);
        usernameloginInvalid.setVisible(false);
        passwordLoginInvalid.setVisible(false);
        captchatextfield.setVisible(false);
        captchaimage.setVisible(false);
        refresh.setVisible(false);
    }

    public void onSignUpClicked(ActionEvent e) throws IOException {//sigunp signup

        invalidcaptchasignup.setVisible(false);


        if (cap.equals(captchatextfieldsignup.getText())) {
                if (getInfo()) {
                    if(indexxx != 0)
                        number = indexxx - 1 ;
                    else
                        number = indexxx;

                Parent backParent = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
                Scene backScene = new Scene(backParent);
                Stage window = (Stage) signupsignup.getScene().getWindow();
                window.setScene(backScene);
                window.show();
            }
        }
        else invalidcaptchasignup.setVisible(true);

    }
    public  void checkindex() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dataAddress, dataUser, dataPassword);
            Statement statement = connection.createStatement();
            //noinspection SqlResolve
            String query = "SELECT `index` FROM `person` ";
            ResultSet resultSet = statement.executeQuery(query);
            if (indexxx == 0) {
                while (resultSet.next()) {
                    indexxx = resultSet.getInt("index") + 1;
                }


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
        public void onExitClicked(ActionEvent e){
        System.exit(0);
    }
    public boolean getInfo() {
        Person person = new Person();

        String username = usernamefieldsignup.getText();
        if (Person.isusernamevalid(username)) {
            person.setUsername(username);
            usernameSignupInvalid.setVisible(false);
        } else {
            usernameSignupInvalid.setVisible(true);
            return false;
        }

        String fname = firstname.getText();
        if (Person.iswordvalid(fname)) {
            person.setFirstName(fname);
            firstnameInvalid.setVisible(false);
        } else {
            firstnameInvalid.setVisible(true);
            return false;
        }

        String lname = lastname.getText();
        if (Person.iswordvalid(lname)) {
            person.setLastName(lname);
            lastnameInvalid.setVisible(false);
        } else {
            lastnameInvalid.setVisible(true);
            return false;
        }
        String pass = passwordfieldsignup.getText();
        if (Person.ispasswordvalid(pass)) {
            person.setPassword(pass);
            passwordSignupInvalid.setVisible(false);
        } else {
            passwordSignupInvalid.setVisible(true);
            return false;
        }

        String reppass = passwordfieldsignuprepeated.getText();
        if (reppass.equals(pass)) {
            person.setRepeatedPassword(reppass);
            repeatedpasswordInvalid.setVisible(false);
        } else {
            repeatedpasswordInvalid.setVisible(true);
            return false;
        }

        String em = email.getText();
        if (Person.isemailvalid(em)) {
            person.setEmail(em);
            emailInvalid.setVisible(false);
        } else {
            emailInvalid.setVisible(true);
            return false;
        }

        String phone = phonenumber.getText();
        if (Person.isphonenumbervalid(phone)) {
            person.setPhoneNumber(phone);
            phonenumberInvalid.setVisible(false);
        } else {
            phonenumberInvalid.setVisible(true);
            return false;
        }

        String dataAddress = "jdbc:Mysql://localhost:3306/infos";
        String dataUser = "root";
        String dataPassword = "";
        int id = walletidmaker();

        try {
            checkindex();
            Connection connection = DriverManager.getConnection(dataAddress, dataUser,dataPassword);
            //noinspection SqlResolve
            String query = "INSERT INTO `person`(`index`, `username`, `password`, `lastname`, `firstname`, `email`, `phonenumber`, `walletid`, `usd`, `eur`, `toman`, `yen`, `gbp`, `usdtrade`, `eurtrade`, `tomantrade`, `yentrade`, `gbptrade`, `usdlasttrade`, `eurlasttrade`, `tomanlasttrade`, `yenlasttrade`, `gbplasttrade`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,indexxx ++);
            preparedStatement.setString(2,username);
            preparedStatement.setString(3,pass);
            preparedStatement.setString(4,lname);
            preparedStatement.setString(5,fname);
            preparedStatement.setString(6,em);
            preparedStatement.setString(7,phone);
            preparedStatement.setInt(8,id);
            preparedStatement.setDouble(9,0);
            preparedStatement.setDouble(10,0);
            preparedStatement.setDouble(11,0);
            preparedStatement.setDouble(12,0);
            preparedStatement.setDouble(13,0);
            preparedStatement.setDouble(14,0);
            preparedStatement.setDouble(15,0);
            preparedStatement.setDouble(16,0);
            preparedStatement.setDouble(17,0);
            preparedStatement.setDouble(18,0);
            preparedStatement.setString(19,"");
            preparedStatement.setString(20,"");
            preparedStatement.setString(21,"");
            preparedStatement.setString(22,"");
            preparedStatement.setString(23,"");
            preparedStatement.executeUpdate();
            System.out.println("information inserted.");

        } catch (SQLException e) {
            System.out.println("error in get connection");
            e.printStackTrace();
        }
        return true;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cap = captchaMaker.generateCaptchaString();
        try {
            captchaMaker.captcha(cap, 231, 62,  234);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File file = new File("captcha.png");
        Image image = new Image(file.toURI().toString());
        captchaimage.setImage(image);
        initClock();
    }
    private String RandomPasswordMaker() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        Random rand = new Random();

        while (password.length() < 8) { // Password length
            int example = rand.nextInt(62);
            password.append(chars.charAt(example));
        }
        return password.toString();
    }
    public  void onforgetcheckbuttonclicked(ActionEvent e) throws IOException {
        String rec = passwordfieldforget.getText();
        String pass = newpasswordfieldforget.getText();
        String reppass = repeatnewpasswordfieldforget.getText();
        if (!rec.equals(newPassword)) {
            passwordInvalidrecieved.setVisible(true);
        } else {
            passwordInvalidrecieved.setVisible(false);
            if (Person.ispasswordvalid(pass)) {
                String firstname = "";
                String lastname = "";
                String username = "";
                String email2 = "";
                String password = "";
                String phonenumber = "";

                Person person = new Person();
                person.setPassword(pass);

                if (reppass.equals(pass)) {
                    person.setRepeatedPassword(reppass);
                    passwordInvalidrecieved.setVisible(false);
                    passwordInvalid.setVisible(false);
                    repeatedpasswordInvalidforget.setVisible(false);
                    login.setVisible(true);
                    usernamefieldlogin.setVisible(true);
                    passwordfieldlogin.setVisible(true);
                    captchatextfield.setVisible(true);
                    refresh.setVisible(true);
                    sumbit.setVisible(true);
                    forgotpass.setVisible(true);
                    notAMember.setVisible(true);
                    signuplogin.setVisible(true);
                    captchaimage.setVisible(true);
                    forgetcheckbutton.setVisible(false);
                    passwordfieldforget.setVisible(false);
                    newpasswordfieldforget.setVisible(false);
                    repeatnewpasswordfieldforget.setVisible(false);
                    ForgotPassForgot.setVisible(false);

                    try {
                        Connection connection = DriverManager.getConnection(dataAddress, dataUser, dataPassword);
                        //noinspection SqlResolve
                        String query = " UPDATE `person` SET `password` = ? WHERE `index` = ?";
                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setString(1, pass);
                        preparedStatement.setInt(2, LoginPage.number);
                        preparedStatement.executeUpdate();
                    } catch (SQLException p) {
                        System.out.println("error in get connection");
                        p.printStackTrace();
                    }
                } else {
                    repeatedpasswordInvalidforget.setVisible(true);
                }


            } else{
                passwordInvalid.setVisible(true);
            }
        }
    }

    public void OnForgetPassClicked(ActionEvent e) throws IOException {//continue
        emailnotfound.setVisible(false);
        String email = EmailForgottextfield.getText();
        if (email.isEmpty() || !checkemailforgot(email)) {
            emailnotfound.setVisible(true);
        }
        else {
            passwordfieldforget.clear();
            newpasswordfieldforget.clear();
            repeatnewpasswordfieldforget.clear();
            emailnotfound.setVisible(false);
            newPassword = RandomPasswordMaker();
            sendEmail(email, newPassword);
            passwordfieldforget.setVisible(true);
            newpasswordfieldforget.setVisible(true);
            repeatnewpasswordfieldforget.setVisible(true);
            forgetcheckbutton.setVisible(true);
            EmailForgottextfield.setVisible(false);
            EnterEmail.setVisible(false);
            continueButton.setVisible(false);
            back.setVisible(false);
        }
    }


    public boolean checkemailforgot(String email) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dataAddress, dataUser, dataPassword);
            Statement statement = connection.createStatement();
            //noinspection SqlResolve
            String query = "SELECT  `email` FROM `person` ";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                if (resultSet.getString("email").equals(email)) {

                    return  true;
                }
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


        private void sendEmail(String email, String newPassword) {
        final String username = "hmdpsw23@gmail.com";
        final String password = "sliqronbweatmbbg";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("hmdpsw23@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Password Reset");
            message.setText("Your new password is: " + newPassword);

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public int walletidmaker(){
        Random random = new Random();
        String a = "";
        for(int i = 0;i < 8;i ++){
            a += String.valueOf(random.nextInt(10));
        }
        return Integer.parseInt(a);
    }
    private void initClock() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            time.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
}
