package com.example.exchange;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Profile implements Initializable {

    @FXML
    private TableView<Currencies> table;

    @FXML
    private TableColumn<Currencies, String> amount;

    @FXML
    private TableColumn<Currencies, String> currencies;

    @FXML
    private TableColumn<Currencies, String> asset;

    @FXML
    private TableColumn<Currencies, String> price;

    @FXML
    private Label emailInvalid, dollarlabel;

    @FXML
    private Button emailcheck;

    @FXML
    private Button emailedit;

    @FXML
    private Label emailprofile, timelabelprofile, totalasset, wealth;

    @FXML
    private TextField emailtextfield;

    @FXML
    private Label emailtitleprofile;

    @FXML
    private Label firstnameInvalid;

    @FXML
    private Button firstnamecheck;

    @FXML
    private Button firstnameedit;

    @FXML
    private Label firstnameprofile;

    @FXML
    private TextField firstnametextfield;

    @FXML
    private Label firstnametitleprofile;

    @FXML
    private Button historybutton;

    @FXML
    private Label lastnameInvalid;

    @FXML
    private Button lastnamecheck, backprofile;

    @FXML
    private Button lastnameedit;

    @FXML
    private Label lastnameprofile;

    @FXML
    private TextField lastnametextfield;

    @FXML
    private Label lastnametitleprofile;

    @FXML
    private Label passwordInvalid;

    @FXML
    private Button passwordcheck, backwalletprofile;

    @FXML
    private Button passwordedit;

    @FXML
    private PasswordField passwordfield;

    @FXML
    private Label passwordprofile;

    @FXML
    private Label passwordtitleprofile;

    @FXML
    private Label phonenumberInvalid;

    @FXML
    private Button phonenumbercheck, backcirclebutton;

    @FXML
    private Button phonenumberedit;

    @FXML
    private Label phonenumberprofile;

    @FXML
    private TextField phonenumbertextfield;

    @FXML
    private Label phonenumbertitleprofile;

    @FXML
    private Label profilelabel, walletlabel;

    @FXML
    private Line profileline;

    @FXML
    private Label repeatedpasswordInvalid;

    @FXML
    private PasswordField repeatpasswordfield;

    @FXML
    private Label usernameprofile;

    @FXML
    private Label usernametitleprofile;

    @FXML
    private Button walletbutton;

    String firstname = "";
    String lastname = "";
    String username = "";
    String email = "";
    String password = "";
    String phonenumber = "";

    String dataAddress = "jdbc:Mysql://localhost:3306/infos";
    String dataUser = "root";
    String dataPassword = "";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showinfo();
        initClock();

    }

    @FXML
    public void onemaileditclicked(ActionEvent event) {
        emailtextfield.clear();
        usernametitleprofile.setVisible(false);
        usernameprofile.setVisible(false);
        emailtextfield.setVisible(true);
        emailcheck.setVisible(true);
        walletbutton.setVisible(false);
        historybutton.setVisible(false);
        lastnametitleprofile.setVisible(false);
        lastnameedit.setVisible(false);
        lastnameprofile.setVisible(false);
        phonenumbertitleprofile.setVisible(false);
        phonenumberedit.setVisible(false);
        phonenumberprofile.setVisible(false);
        passwordprofile.setVisible(false);
        passwordtitleprofile.setVisible(false);
        passwordedit.setVisible(false);
        firstnametitleprofile.setVisible(false);
        firstnameedit.setVisible(false);
        firstnameprofile.setVisible(false);
        backcirclebutton.setVisible(true);
        emailedit.setVisible(false);
    }

    public void onemailcheckclicked(ActionEvent event) throws IOException {
        String em = emailtextfield.getText();
        if (Person.isemailvalid(em)) {
            try {
                Connection connection = DriverManager.getConnection(dataAddress, dataUser, dataPassword);
                String query = " UPDATE `person` SET `email` = ? WHERE `index` = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, em);
                preparedStatement.setInt(2, LoginPage.number);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("error in get connection");
                e.printStackTrace();
            }
            emailprofile.setText(em);
            walletbutton.setVisible(true);
            historybutton.setVisible(true);
            emailtextfield.setVisible(false);
            emailcheck.setVisible(false);
            firstnametextfield.setVisible(false);
            firstnamecheck.setVisible(false);
            lastnametextfield.setVisible(false);
            lastnamecheck.setVisible(false);
            passwordfield.setVisible(false);
            passwordcheck.setVisible(false);
            repeatpasswordfield.setVisible(false);
            phonenumbertextfield.setVisible(false);
            phonenumbercheck.setVisible(false);
            passwordtitleprofile.setVisible(true);
            passwordedit.setVisible(true);
            passwordprofile.setVisible(true);
            emailtitleprofile.setVisible(true);
            emailedit.setVisible(true);
            emailprofile.setVisible(true);
            firstnametitleprofile.setVisible(true);
            firstnameedit.setVisible(true);
            firstnameprofile.setVisible(true);
            lastnametitleprofile.setVisible(true);
            lastnameedit.setVisible(true);
            lastnameprofile.setVisible(true);
            phonenumbertitleprofile.setVisible(true);
            phonenumberedit.setVisible(true);
            phonenumberprofile.setVisible(true);
            backcirclebutton.setVisible(false);
            firstnameInvalid.setVisible(false);
            lastnameInvalid.setVisible(false);
            passwordInvalid.setVisible(false);
            repeatedpasswordInvalid.setVisible(false);
            emailInvalid.setVisible(false);
            phonenumberInvalid.setVisible(false);
        } else {
            emailInvalid.setVisible(true);
        }
    }

    @FXML
    public void onfirstnameeditclicked(ActionEvent event) {
        firstnametextfield.clear();
        usernametitleprofile.setVisible(false);
        usernameprofile.setVisible(false);
        firstnametextfield.setVisible(true);
        firstnamecheck.setVisible(true);
        firstnameedit.setVisible(false);
        walletbutton.setVisible(false);
        historybutton.setVisible(false);
        lastnametitleprofile.setVisible(false);
        lastnameedit.setVisible(false);
        lastnameprofile.setVisible(false);
        phonenumbertitleprofile.setVisible(false);
        phonenumberedit.setVisible(false);
        phonenumberprofile.setVisible(false);
        passwordtitleprofile.setVisible(false);
        passwordedit.setVisible(false);
        passwordprofile.setVisible(false);
        emailtitleprofile.setVisible(false);
        emailedit.setVisible(false);
        emailprofile.setVisible(false);
        backcirclebutton.setVisible(true);
        firstnameInvalid.setVisible(false);

    }
    public void onfirstnamecheckclicked(ActionEvent event) throws IOException {
        String fname = firstnametextfield.getText();
        if (Person.iswordvalid(fname)) {
            try {
                Connection connection = DriverManager.getConnection(dataAddress, dataUser, dataPassword);
                String query = " UPDATE `person` SET `firstname` = ? WHERE `index` = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, fname);
                preparedStatement.setInt(2, LoginPage.number);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("error in get connection");
                e.printStackTrace();
            }
            firstnameprofile.setText(fname);
            walletbutton.setVisible(true);
            historybutton.setVisible(true);
            emailtextfield.setVisible(false);
            emailcheck.setVisible(false);
            firstnametextfield.setVisible(false);
            firstnamecheck.setVisible(false);
            lastnametextfield.setVisible(false);
            lastnamecheck.setVisible(false);
            passwordfield.setVisible(false);
            passwordcheck.setVisible(false);
            repeatpasswordfield.setVisible(false);
            phonenumbertextfield.setVisible(false);
            phonenumbercheck.setVisible(false);
            passwordtitleprofile.setVisible(true);
            passwordedit.setVisible(true);
            passwordprofile.setVisible(true);
            emailtitleprofile.setVisible(true);
            emailedit.setVisible(true);
            emailprofile.setVisible(true);
            firstnametitleprofile.setVisible(true);
            firstnameedit.setVisible(true);
            firstnameprofile.setVisible(true);
            lastnametitleprofile.setVisible(true);
            lastnameedit.setVisible(true);
            lastnameprofile.setVisible(true);
            phonenumbertitleprofile.setVisible(true);
            phonenumberedit.setVisible(true);
            phonenumberprofile.setVisible(true);
            backcirclebutton.setVisible(false);
            firstnameInvalid.setVisible(false);
            lastnameInvalid.setVisible(false);
            passwordInvalid.setVisible(false);
            repeatedpasswordInvalid.setVisible(false);
            emailInvalid.setVisible(false);
            phonenumberInvalid.setVisible(false);
        } else {
            firstnameInvalid.setVisible(true);
        }
    }

    @FXML
    public void onlastnameeditclicked(ActionEvent event) {
        lastnametextfield.clear();
        usernametitleprofile.setVisible(false);
        usernameprofile.setVisible(false);
        lastnametextfield.setVisible(true);
        lastnamecheck.setVisible(true);
        walletbutton.setVisible(false);
        historybutton.setVisible(false);
        phonenumbertitleprofile.setVisible(false);
        phonenumberedit.setVisible(false);
        phonenumberprofile.setVisible(false);
        passwordtitleprofile.setVisible(false);
        passwordedit.setVisible(false);
        passwordprofile.setVisible(false);
        emailtitleprofile.setVisible(false);
        emailedit.setVisible(false);
        emailprofile.setVisible(false);
        firstnametitleprofile.setVisible(false);
        firstnameedit.setVisible(false);
        firstnameprofile.setVisible(false);
        backcirclebutton.setVisible(true);
        lastnameedit.setVisible(false);
        lastnameInvalid.setVisible(false);
    }

    public void onlastnamecheckclicked(ActionEvent event) throws IOException {
        String lname = lastnametextfield.getText();
        if (Person.iswordvalid(lname)) {
            try {
                Connection connection = DriverManager.getConnection(dataAddress, dataUser, dataPassword);
                String query = " UPDATE `person` SET `lastname` = ? WHERE `index` = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, lname);
                preparedStatement.setInt(2, LoginPage.number);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("error in get connection");
                e.printStackTrace();
            }
            lastnameprofile.setText(lname);
            walletbutton.setVisible(true);
            historybutton.setVisible(true);
            emailtextfield.setVisible(false);
            emailcheck.setVisible(false);
            firstnametextfield.setVisible(false);
            firstnamecheck.setVisible(false);
            lastnametextfield.setVisible(false);
            lastnamecheck.setVisible(false);
            passwordfield.setVisible(false);
            passwordcheck.setVisible(false);
            repeatpasswordfield.setVisible(false);
            phonenumbertextfield.setVisible(false);
            phonenumbercheck.setVisible(false);
            passwordtitleprofile.setVisible(true);
            passwordedit.setVisible(true);
            passwordprofile.setVisible(true);
            emailtitleprofile.setVisible(true);
            emailedit.setVisible(true);
            emailprofile.setVisible(true);
            firstnametitleprofile.setVisible(true);
            firstnameedit.setVisible(true);
            firstnameprofile.setVisible(true);
            lastnametitleprofile.setVisible(true);
            lastnameedit.setVisible(true);
            lastnameprofile.setVisible(true);
            phonenumbertitleprofile.setVisible(true);
            phonenumberedit.setVisible(true);
            phonenumberprofile.setVisible(true);
            backcirclebutton.setVisible(false);
            firstnameInvalid.setVisible(false);
            lastnameInvalid.setVisible(false);
            passwordInvalid.setVisible(false);
            repeatedpasswordInvalid.setVisible(false);
            emailInvalid.setVisible(false);
            phonenumberInvalid.setVisible(false);
        } else {
            lastnameInvalid.setVisible(true);
        }
    }

    @FXML
    public void onpasswordeditclicked(ActionEvent event) {
        passwordfield.clear();
        repeatpasswordfield.clear();
        usernametitleprofile.setVisible(false);
        usernameprofile.setVisible(false);
        passwordfield.setVisible(true);
        passwordcheck.setVisible(true);
        repeatpasswordfield.setVisible(true);
        walletbutton.setVisible(false);
        historybutton.setVisible(false);
        emailtitleprofile.setVisible(false);
        emailedit.setVisible(false);
        emailprofile.setVisible(false);
        firstnametitleprofile.setVisible(false);
        firstnameedit.setVisible(false);
        firstnameprofile.setVisible(false);
        phonenumbertitleprofile.setVisible(false);
        phonenumberedit.setVisible(false);
        phonenumberprofile.setVisible(false);
        lastnametitleprofile.setVisible(false);
        lastnameedit.setVisible(false);
        lastnameprofile.setVisible(false);
        backcirclebutton.setVisible(true);
        passwordedit.setVisible(false);
    }

    public void onpasswordcheckclicked(ActionEvent event) throws IOException {
        String pass = passwordfield.getText();
        if (Person.ispasswordvalid(pass)) {
            passwordInvalid.setVisible(false);
            String reppass = repeatpasswordfield.getText();
            if (reppass.equals(pass)) {
                try {
                    Connection connection = DriverManager.getConnection(dataAddress, dataUser, dataPassword);
                    String query = " UPDATE `person` SET `password` = ? WHERE `index` = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, pass);
                    preparedStatement.setInt(2, LoginPage.number);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("error in get connection");
                    e.printStackTrace();
                }
                repeatedpasswordInvalid.setVisible(false);
            } else {
                repeatedpasswordInvalid.setVisible(true);
                return;
            }
            passwordprofile.setText(pass);
            passwordInvalid.setVisible(false);
            repeatedpasswordInvalid.setVisible(false);
            walletbutton.setVisible(true);
            historybutton.setVisible(true);
            emailtextfield.setVisible(false);
            emailcheck.setVisible(false);
            firstnametextfield.setVisible(false);
            firstnamecheck.setVisible(false);
            lastnametextfield.setVisible(false);
            lastnamecheck.setVisible(false);
            passwordfield.setVisible(false);
            passwordcheck.setVisible(false);
            repeatpasswordfield.setVisible(false);
            phonenumbertextfield.setVisible(false);
            phonenumbercheck.setVisible(false);
            passwordtitleprofile.setVisible(true);
            passwordedit.setVisible(true);
            passwordprofile.setVisible(true);
            emailtitleprofile.setVisible(true);
            emailedit.setVisible(true);
            emailprofile.setVisible(true);
            firstnametitleprofile.setVisible(true);
            firstnameedit.setVisible(true);
            firstnameprofile.setVisible(true);
            lastnametitleprofile.setVisible(true);
            lastnameedit.setVisible(true);
            lastnameprofile.setVisible(true);
            phonenumbertitleprofile.setVisible(true);
            phonenumberedit.setVisible(true);
            phonenumberprofile.setVisible(true);
            backcirclebutton.setVisible(false);
            firstnameInvalid.setVisible(false);
            lastnameInvalid.setVisible(false);
            passwordInvalid.setVisible(false);
            repeatedpasswordInvalid.setVisible(false);
            emailInvalid.setVisible(false);
            phonenumberInvalid.setVisible(false);
        } else {
            passwordInvalid.setVisible(true);
        }
    }

    @FXML
    public void onphonenumbereditclicked(ActionEvent event) {
        phonenumbertextfield.clear();
        usernametitleprofile.setVisible(false);
        usernameprofile.setVisible(false);
        phonenumbertextfield.setVisible(true);
        phonenumbercheck.setVisible(true);
        walletbutton.setVisible(false);
        historybutton.setVisible(false);
        passwordtitleprofile.setVisible(false);
        passwordedit.setVisible(false);
        passwordprofile.setVisible(false);
        emailtitleprofile.setVisible(false);
        emailedit.setVisible(false);
        emailprofile.setVisible(false);
        firstnametitleprofile.setVisible(false);
        firstnameedit.setVisible(false);
        firstnameprofile.setVisible(false);
        lastnametitleprofile.setVisible(false);
        lastnameedit.setVisible(false);
        lastnameprofile.setVisible(false);
        backcirclebutton.setVisible(true);
        phonenumberedit.setVisible(false);
    }
    public void onphonenumbercheckclicked(ActionEvent event) throws IOException {
        String phone = phonenumbertextfield.getText();
        if (Person.isphonenumbervalid(phone)) {
            try {
                Connection connection = DriverManager.getConnection(dataAddress, dataUser, dataPassword);
                String query = " UPDATE `person` SET `phonenumber` = ? WHERE `index` = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, phone);
                preparedStatement.setInt(2, LoginPage.number);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println("error in get connection");
                e.printStackTrace();
            }
            phonenumberprofile.setText(phone);
            walletbutton.setVisible(true);
            historybutton.setVisible(true);
            emailtextfield.setVisible(false);
            emailcheck.setVisible(false);
            firstnametextfield.setVisible(false);
            firstnamecheck.setVisible(false);
            lastnametextfield.setVisible(false);
            lastnamecheck.setVisible(false);
            passwordfield.setVisible(false);
            passwordcheck.setVisible(false);
            repeatpasswordfield.setVisible(false);
            phonenumbertextfield.setVisible(false);
            phonenumbercheck.setVisible(false);
            passwordtitleprofile.setVisible(true);
            passwordedit.setVisible(true);
            passwordprofile.setVisible(true);
            emailtitleprofile.setVisible(true);
            emailedit.setVisible(true);
            emailprofile.setVisible(true);
            firstnametitleprofile.setVisible(true);
            firstnameedit.setVisible(true);
            firstnameprofile.setVisible(true);
            lastnametitleprofile.setVisible(true);
            lastnameedit.setVisible(true);
            lastnameprofile.setVisible(true);
            phonenumbertitleprofile.setVisible(true);
            phonenumberedit.setVisible(true);
            phonenumberprofile.setVisible(true);
            backcirclebutton.setVisible(false);
            firstnameInvalid.setVisible(false);
            lastnameInvalid.setVisible(false);
            passwordInvalid.setVisible(false);
            repeatedpasswordInvalid.setVisible(false);
            emailInvalid.setVisible(false);
            phonenumberInvalid.setVisible(false);
        } else {
            phonenumberInvalid.setVisible(true);
        }
    }


    public void showinfo() {

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dataAddress, dataUser, dataPassword);
            Statement statement = connection.createStatement();
            String query = "SELECT `index`, `username` , `password`, `firstname`, `lastname`, `email`, `phonenumber` FROM `person` ";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                if (resultSet.getInt("index") == LoginPage.number) {
                    username = resultSet.getString("username");
                    password = resultSet.getString("password");
                    firstname = resultSet.getString("firstname");
                    lastname = resultSet.getString("lastname");
                    email = resultSet.getString("email");
                    phonenumber = resultSet.getString("phonenumber");
                    break;
                }
            }
            usernameprofile.setText(username);
            firstnameprofile.setText(firstname);
            lastnameprofile.setText(lastname);
            passwordprofile.setText(password);
            emailprofile.setText(email);
            phonenumberprofile.setText(phonenumber);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
        public void onBackClicked(ActionEvent e) throws IOException {
        Parent backParent = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene backScene = new Scene(backParent);
        Stage window = (Stage) backprofile.getScene().getWindow();
        window.setScene(backScene);
        window.show();
    }

    public void onBackCirClicked(ActionEvent e) {
        walletbutton.setVisible(true);
        historybutton.setVisible(true);
        emailtextfield.setVisible(false);
        emailcheck.setVisible(false);
        firstnametextfield.setVisible(false);
        firstnamecheck.setVisible(false);
        lastnametextfield.setVisible(false);
        lastnamecheck.setVisible(false);
        passwordfield.setVisible(false);
        passwordcheck.setVisible(false);
        repeatpasswordfield.setVisible(false);
        phonenumbertextfield.setVisible(false);
        phonenumbercheck.setVisible(false);
        passwordtitleprofile.setVisible(true);
        passwordedit.setVisible(true);
        passwordprofile.setVisible(true);
        emailtitleprofile.setVisible(true);
        emailedit.setVisible(true);
        emailprofile.setVisible(true);
        firstnametitleprofile.setVisible(true);
        firstnameedit.setVisible(true);
        firstnameprofile.setVisible(true);
        lastnametitleprofile.setVisible(true);
        lastnameedit.setVisible(true);
        lastnameprofile.setVisible(true);
        phonenumbertitleprofile.setVisible(true);
        phonenumberedit.setVisible(true);
        phonenumberprofile.setVisible(true);
        backcirclebutton.setVisible(false);
        firstnameInvalid.setVisible(false);
        lastnameInvalid.setVisible(false);
        passwordInvalid.setVisible(false);
        repeatedpasswordInvalid.setVisible(false);
        emailInvalid.setVisible(false);
        phonenumberInvalid.setVisible(false);
    }

    private void initClock() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            timelabelprofile.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
    public void onWalletClicked(ActionEvent e){
        usernametitleprofile.setVisible(false);
        usernameprofile.setVisible(false);
        phonenumbertitleprofile.setVisible(false);
        phonenumberprofile.setVisible(false);
        phonenumberedit.setVisible(false);
        walletbutton.setVisible(false);
        historybutton.setVisible(false);
        passwordtitleprofile.setVisible(false);
        passwordedit.setVisible(false);
        passwordprofile.setVisible(false);
        emailtitleprofile.setVisible(false);
        emailedit.setVisible(false);
        emailprofile.setVisible(false);
        firstnametitleprofile.setVisible(false);
        firstnameedit.setVisible(false);
        firstnameprofile.setVisible(false);
        lastnametitleprofile.setVisible(false);
        lastnameedit.setVisible(false);
        lastnameprofile.setVisible(false);
        backcirclebutton.setVisible(false);
        walletlabel.setVisible(true);
        backprofile.setVisible(false);
        backwalletprofile.setVisible(true);
        profilelabel.setVisible(false);
        table.setVisible(true);
        wealth.setVisible(true);
        totalasset.setVisible(true);
        dollarlabel.setVisible(true);


        currencies.setCellValueFactory(new PropertyValueFactory<Currencies, String>("currencies"));
        amount.setCellValueFactory(new PropertyValueFactory<Currencies, String>("amount"));
        price.setCellValueFactory(new PropertyValueFactory<Currencies, String>("price"));
        asset.setCellValueFactory(new PropertyValueFactory<Currencies, String>("asset"));


        ObservableList<Currencies> List = FXCollections.observableArrayList();
        table.setItems(List);
        scheduler.scheduleAtFixedRate(task, 0, 10, TimeUnit.SECONDS);
    }

    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    String usdamount = "";
    String euramount = "";
    String tomanamount = "";
    String yenamount = "";
    String gbpamount = "";
    double totalAsset1 = 0.0;
    double totalAsset2 = 0.0;
    double totalAsset3 = 0.0;
    double totalAsset4 = 0.0;
    double totalAsset5 = 0.0;

    Runnable task = () -> {
        try {
            Connection connection = DriverManager.getConnection(dataAddress, dataUser, dataPassword);
            Statement statement = connection.createStatement();
            String query = "SELECT `index`, `usd`, `eur`, `toman`, `yen`, `gbp` FROM `person`";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                if (resultSet.getInt("index") == LoginPage.number) {
                    usdamount = resultSet.getString("usd");
                    euramount = resultSet.getString("eur");
                    tomanamount = resultSet.getString("toman");
                    yenamount = resultSet.getString("yen");
                    gbpamount = resultSet.getString("gbp");
                    break;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Platform.runLater(() -> {

            Currencies newData1 = getusd(usdamount);
            Currencies newData2 = geteur(euramount);
            Currencies newData3 = gettoman(tomanamount);
            Currencies newData4 = getyen(yenamount);
            Currencies newData5 = getgbp(gbpamount);

            if (table.getItems().size() > 4) {
                table.getItems().remove(0, 5);
            }
            double totalAsset = totalAsset1 + totalAsset2 + totalAsset3 + totalAsset4 + totalAsset5;
            String toTal = String.format("%.02f", totalAsset);
            wealth.setText(toTal);

            table.getItems().add(newData1);
            table.getItems().add(newData2);
            table.getItems().add(newData3);
            table.getItems().add(newData4);
            table.getItems().add(newData5);
        });
    };
    private Currencies getusd(String usdamount) {
        int i = 0;
        String asset = "";
        double doubleAsset = Double.parseDouble(usdamount) / Double.parseDouble(HomePage.usd);
        totalAsset1 = 0.0;
        if(!usdamount.equals("0")) {
            asset = String.format("%.02f", doubleAsset);
            totalAsset1 += Double.parseDouble(asset);
        }
        else asset = "0.00";
        return new Currencies("             USD", "               " + usdamount, "               " + HomePage.usd, "            " + asset);
    }

    private Currencies geteur(String euramount) {
        int i = 0;
        String asset =  "";
        double doubleAsset = Double.parseDouble(euramount) / Double.parseDouble(HomePage.eur);
        totalAsset2 = 0.0;
        if(!euramount.equals("0")) {
            asset = String.format("%.02f", doubleAsset);
            totalAsset2 += Double.parseDouble(asset);
        }
        else asset = "0.00";
        return new Currencies("             EUR","               " + euramount, "               " + HomePage.eur, "             " + asset);
    }

    private Currencies gettoman(String tomanamount) {
        int i = 0;
        String asset =  "";
        double doubleAsset = Double.parseDouble(tomanamount) / Double.parseDouble(HomePage.toman);
        totalAsset3 = 0.0;
        if(!tomanamount.equals("0")) {
            asset = String.format("%.02f", doubleAsset);
            totalAsset3 += Double.parseDouble(asset);
        }
        else asset = "0.00";
        return new Currencies("           TOMAN","               " +tomanamount , "            " + HomePage.toman, "               " +  asset);
    }

    private Currencies getyen(String yenamount) {
        int i = 0;
        String asset =  "";
        double doubleAsset = Double.parseDouble(yenamount) / Double.parseDouble(HomePage.yen);
        totalAsset4 = 0.0;
        if(!yenamount.equals("0")) {
            asset = String.format("%.02f", doubleAsset);
            totalAsset4 += Double.parseDouble(asset);
        }
        else asset = "0.00";
        return new Currencies("             YEN", "               " +yenamount, "             " + HomePage.yen, "               " + asset);
    }

    private Currencies getgbp(String gbpamount) {
        int i = 0;
        String asset =  "";
        double doubleAsset = Double.parseDouble(gbpamount) / Double.parseDouble(HomePage.gbp);
        totalAsset5 = 0.0;
        if(!gbpamount.equals("0")) {
            asset = String.format("%.02f", doubleAsset);
            totalAsset5 += Double.parseDouble(asset);
        }
        else asset = "0.00";
        return new Currencies("             GBP","                " +gbpamount , "               " + HomePage.gbp, "              " + asset);
    }
    public void onBackWalletClicked(ActionEvent e){
        backprofile.setVisible(true);
        usernametitleprofile.setVisible(true);
        usernameprofile.setVisible(true);
        phonenumberprofile.setVisible(true);
        phonenumberedit.setVisible(true);
        phonenumbertitleprofile.setVisible(true);
        walletbutton.setVisible(true);
        historybutton.setVisible(true);
        passwordtitleprofile.setVisible(true);
        passwordedit.setVisible(true);
        passwordprofile.setVisible(true);
        emailtitleprofile.setVisible(true);
        emailedit.setVisible(true);
        emailprofile.setVisible(true);
        firstnametitleprofile.setVisible(true);
        firstnameedit.setVisible(true);
        firstnameprofile.setVisible(true);
        lastnametitleprofile.setVisible(true);
        lastnameedit.setVisible(true);
        lastnameprofile.setVisible(true);
        walletlabel.setVisible(false);
        profilelabel.setVisible(true);
        backwalletprofile.setVisible(false);
        table.setVisible(false);
        wealth.setVisible(false);
        totalasset.setVisible(false);
        dollarlabel.setVisible(false);

    }
}
