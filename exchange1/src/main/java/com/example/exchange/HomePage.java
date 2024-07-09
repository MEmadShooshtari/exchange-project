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
import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class HomePage implements Initializable {

    @FXML
    private TableView<Currencies> table;


    @FXML
    private TableColumn<Currencies, String> changes;

    @FXML
    private TableColumn<Currencies, String> currencies;

    @FXML
    private TableColumn<Currencies, String> highest;

    @FXML
    private TableColumn<Currencies, String> lowest;

    @FXML
    private TableColumn<Currencies, String> price;
    @FXML
    Label timelabel, homelabel, usdlabel, eurlabel, tomanlabel, yenlabel, gbplabel, usdpricelabel, pricetitle, changetitle, usdchangelabel;
    @FXML
    Label yenpricelabel, yenchangelabel, gbppricelabel, gbpchangelabel;
    @FXML
    Label eurpricelabel, eurchangelabel, tomanpricelabel, tomanchangelabel, checklabel;
    @FXML
    Line homeline;
    @FXML
    private Button signout;
    @FXML
    private Button eurbutton;
    @FXML
    private Button yenbutton;
    @FXML
    private Button gbpbutton;
    @FXML
    private Button tomanbutton;
    @FXML
    private Button profilebutton;
    @FXML
    private Button exchangebutton;
    @FXML
    private Button transferbutton, swapbutton;
    @FXML
    Button usdbutton, back, transfertransfer;
    @FXML
    Label walletnotfound, lasttradetitel, usdlasttrade, eurlasttrade, tomanlasttrade, yenlasttrade, gbplasttrade,  transferfee, fee, transferlabel, amountlabel, gbpvolume, usdvolume, yenvolume, eurvolume, tomanvolume, volumetitle;
    @FXML
    CheckBox usdcheck, eurcheck, tomancheck, yencheck, gbpcheck;
    @FXML
    TextField walletidfield, amountfield;

    public  static  final int adminWalletID = 59146445;

    String currencytype = "";

    String dataAddress = "jdbc:Mysql://localhost:3306/infos";
    String dataUser = "root";
    String dataPassword = "";

    String line = "";
     public static String usd = "";
     public static String eur = "";
     public static String toman = "";
     public static String yen = "";
     public static String gbp = "";

    String usdchange = "";
    String eurchange = "";
    String tomanchange = "";
    String yenchange = "";
    String gbpchange = "";

    String usdch = "";
    String eurch = "";
    String tomanch = "";
    String yench = "";
    String gbpch = "";

    String maxusd = "0";
    String maxeur = "0";
    String maxtoman = "0";
    String maxyen = "0";
    String maxgbp = "0";

    String minusd = "99999";
    String mineur = "99999";
    String mintoman = "99999";
    String minyen = "99999";
    String mingbp = "99999";
    double usdtr = 0;
    double eurtr = 0;
    double tomantr = 0;
    double yentr = 0;
    double gbptr = 0;

    String usdlt = "";
    String eurlt = "";
    String tomanlt = "";
    String yenlt = "";
    String gbplt = "";



    public void onSignOutClicked(ActionEvent e) throws IOException {
        Parent backParent = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        Scene backScene = new Scene(backParent);
        Stage window = (Stage) signout.getScene().getWindow();
        window.setScene(backScene);
        window.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initClock();
        currencies.setCellValueFactory(new PropertyValueFactory<Currencies, String>("currencies"));
        changes.setCellValueFactory(new PropertyValueFactory<Currencies, String>("changes"));
        price.setCellValueFactory(new PropertyValueFactory<Currencies, String>("price"));
        highest.setCellValueFactory(new PropertyValueFactory<Currencies, String>("highest"));
        lowest.setCellValueFactory(new PropertyValueFactory<Currencies, String>("lowest"));


        ObservableList<Currencies> List = FXCollections.observableArrayList();
        table.setItems(List);
        scheduler.scheduleAtFixedRate(task, 0, 10, TimeUnit.SECONDS);


    }
//
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    Runnable task = () -> {
        try {
            getPrice();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Platform.runLater(() -> {
            Currencies newData1 = getusd();
            Currencies newData2 = geteur();
            Currencies newData3 = gettoman();
            Currencies newData4 = getyen();
            Currencies newData5 = getgbp();

            if (table.getItems().size() > 4) {
                table.getItems().remove(0, 5);
            }
            usdpricelabel.setText(usd);
            usdchangelabel.setText(usdchange);
            eurpricelabel.setText(eur);
            eurchangelabel.setText(eurchange);
            tomanpricelabel.setText(toman);
            tomanchangelabel.setText(tomanchange);
            yenpricelabel.setText(yen);
            yenchangelabel.setText(yenchange);
            gbppricelabel.setText(gbp);
            gbpchangelabel.setText(gbpchange);

            table.getItems().add(newData1);
            table.getItems().add(newData2);
            table.getItems().add(newData3);
            table.getItems().add(newData4);
            table.getItems().add(newData5);

            try {
                Connection connection = DriverManager.getConnection(dataAddress, dataUser, dataPassword);
                Statement statement = connection.createStatement();
                String query = "SELECT  `walletID`, `usdtrade`, `eurtrade`, `tomantrade`, `yentrade`, `gbptrade`, `usdlasttrade`, `eurlasttrade`, `tomanlasttrade`, `yenlasttrade`, `gbplasttrade` FROM `person`";
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    if (resultSet.getInt("walletID") == adminWalletID) {
                        usdtr = resultSet.getDouble("usdtrade");
                        eurtr = resultSet.getDouble("eurtrade");
                        tomantr = resultSet.getDouble("tomantrade");
                        yentr = resultSet.getDouble("yentrade");
                        gbptr = resultSet.getDouble("gbptrade");

                        usdlt = resultSet.getString("usdlasttrade");
                        eurlt = resultSet.getString("eurlasttrade");
                        tomanlt = resultSet.getString("tomanlasttrade");
                        yenlt = resultSet.getString("yenlasttrade");
                        gbplt = resultSet.getString("gbplasttrade");
                        break;
                    }
                }
                if(usdlt.equals(""))
                    usdlt = "not any trade";
                if(eurlt.equals(""))
                    eurlt = "not any trade";
                if(tomanlt.equals(""))
                    tomanlt = "not any trade";
                if(yenlt.equals(""))
                    yenlt = "not any trade";
                if(gbplt.equals(""))
                    gbplt = "not any trade";
                usdvolume.setText(String.valueOf(usdtr));
                eurvolume.setText(String.valueOf(eurtr));
                tomanvolume.setText(String.valueOf(tomantr));
                yenvolume.setText(String.valueOf(yentr));
                gbpvolume.setText(String.valueOf(gbptr));

                usdlasttrade.setText(usdlt);
                eurlasttrade.setText(eurlt);
                tomanlasttrade.setText(tomanlt);
                yenlasttrade.setText(yenlt);
                gbplasttrade.setText(gbplt);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    };

    private Currencies getusd() {
        return new Currencies("             USD", "               " + usd,  "            " + usdchange, "               " + maxusd, "               " + minusd);
    }

    private Currencies geteur() {
        return new Currencies("             EUR", "               " + eur, "            " + eurchange, "               " + maxeur, "               " + mineur);
    }

    private Currencies gettoman() {
        return new Currencies("           TOMAN", "            " + toman, "            " + tomanchange, "            " + maxtoman, "            " + mintoman);
    }

    private Currencies getyen() {
        return new Currencies("             YEN", "             " + yen, "            " + yenchange, "              " + maxyen, "             " + minyen);
    }

    private Currencies getgbp() {
        return new Currencies("             GBP", "               " + gbp, "            " + gbpchange, "               " + maxgbp, "               " + mingbp);
    }


    private void initClock() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            timelabel.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public void getPrice() throws IOException {

        usd = "";
        eur = "";
        toman = "";
        yen = "";
        gbp = "";

        usdchange = "";
        eurchange = "";
        tomanchange = "";
        yenchange = "";
        gbpchange = "";
        usdch = "";
        eurch = "";
        tomanch = "";
        yench = "";
        gbpch = "";

        maxusd = "0";
        maxeur = "0";
        maxtoman = "0";
        maxyen = "0";
        maxgbp = "0";

        minusd = "99999";
        mineur = "99999";
        mintoman = "99999";
        minyen = "99999";
        mingbp = "99999";

        LocalTime currentTime = LocalTime.now();
        int hour = currentTime.getHour();
        int minute = currentTime.getMinute();
        int ans = hour * 60 + (minute - 35 + 1);
        //ans = 2;
        File file = new File("prices.txt");
        BufferedReader input = null;
        int k;
        try {
            input = new BufferedReader(new FileReader(file));

            String prevusd = "";
            String preveur = "";
            String prevtoman = "";
            String prevyen = "";
            String prevgbp = "";

            int i, j;
            for (k = 0; k < ans; k++) {
                line = input.readLine();
                usd = "";
                eur = "";
                toman = "";
                yen = "";
                gbp = "";

                for (i = 0; line.charAt(i) != '.'; i++) ; //first dot

                for (i = i + 1; line.charAt(i) != '.'; i++) ; //second dot

                for (j = i - 1; line.charAt(j) != ' '; j++) {
                    usd += line.charAt(j);
                }
                if (usd.compareTo(maxusd) > 0) {//find maxusd
                    maxusd = usd;
                } else if (usd.compareTo(minusd) < 0) {//find minusd
                    minusd = usd;
                }

                for (i = j; line.charAt(i) == ' '; i++) {// finding eur
                }

                for (j = i; line.charAt(j) != ' '; j++) {
                    eur += line.charAt(j);
                }

                if (eur.compareTo(maxeur) > 0) {
                    maxeur = eur;
                } else if (eur.compareTo(mineur) < 0) {
                    mineur = eur;
                }

                for (i = j; line.charAt(i) == ' '; i++) {// finding toman
                }

                for (j = i; line.charAt(j) != ' '; j++) {
                    toman += line.charAt(j);
                }

                if (toman.compareTo(maxtoman) > 0) {
                    maxtoman = toman;
                } else if (toman.compareTo(mintoman) < 0) {
                    mintoman = toman;
                }

                for (i = j; line.charAt(i) == ' '; i++) {// finding yen
                }

                for (j = i; line.charAt(j) != ' '; j++) {
                    yen += line.charAt(j);
                }

                if (Double.parseDouble(yen) > Double.parseDouble(maxyen)) {
                    maxyen = yen;
                } else if (Double.parseDouble(yen) < Double.parseDouble(minyen)) {
                    minyen = yen;
                }

                for (i = j; line.charAt(i) == ' '; i++) ;// finding gbp


                for (j = i; j < line.length(); j++) {
                    gbp += line.charAt(j);
                }

                if (gbp.compareTo(maxgbp) > 0) {
                    maxgbp = gbp;
                } else if (gbp.compareTo(mingbp) < 0) {
                    mingbp = gbp;
                }

                if (k == ans - 2) {
                    prevusd = usd;
                    preveur = eur;
                    prevtoman = toman;
                    prevyen = yen;
                    prevgbp = gbp;
                }
            }

            if ((Double.valueOf(usd) - Double.valueOf(prevusd)) * 100 / Double.valueOf(prevusd) > 0)
                usdch = "+" + String.valueOf((Double.valueOf(usd) - Double.valueOf(prevusd)) * 100 / Double.valueOf(prevusd));
            else
                usdch = String.valueOf((Double.valueOf(usd) - Double.valueOf(prevusd)) * 100 / Double.valueOf(prevusd));

            if ((Double.valueOf(eur) - Double.valueOf(preveur)) * 100 / Double.valueOf(preveur) > 0)
                eurch = "+" + String.valueOf((Double.valueOf(eur) - Double.valueOf(preveur)) * 100 / Double.valueOf(preveur));
            else
                eurch = String.valueOf((Double.valueOf(eur) - Double.valueOf(preveur)) * 100 / Double.valueOf(preveur));

            if ((Double.valueOf(toman) - Double.valueOf(prevtoman)) * 100 / Double.valueOf(prevtoman) > 0)
                tomanch = "+" + String.valueOf((Double.valueOf(toman) - Double.valueOf(prevtoman)) * 100 / Double.valueOf(prevtoman));
            else
                tomanch = String.valueOf((Double.valueOf(toman) - Double.valueOf(prevtoman)) * 100 / Double.valueOf(prevtoman));

            if ((Double.valueOf(yen) - Double.valueOf(prevyen)) * 100 / Double.valueOf(prevyen) > 0)
                yench = "+" + String.valueOf((Double.valueOf(yen) - Double.valueOf(prevyen)) * 100 / Double.valueOf(prevyen));
            else
                yench = String.valueOf((Double.valueOf(yen) - Double.valueOf(prevyen)) * 100 / Double.valueOf(prevyen));

            if ((Double.valueOf(gbp) - Double.valueOf(prevgbp)) * 100 / Double.valueOf(prevgbp) > 0)
                gbpch = "+" + String.valueOf((Double.valueOf(gbp) - Double.valueOf(prevgbp)) * 100 / Double.valueOf(prevgbp));
            else
                gbpch = String.valueOf((Double.valueOf(gbp) - Double.valueOf(prevgbp)) * 100 / Double.valueOf(prevgbp));


            for (i = 0; usdch.charAt(i) != '.'; i++) {
                usdchange += usdch.charAt(i);
            }
            if (!usdch.equals("0.0")) {
                for (k = 0; k < 3; k++, i++) {
                    usdchange += usdch.charAt(i);
                }
            } else {
                for (k = 0; k < 2; k++, i++) {
                    usdchange += usdch.charAt(i);
                }
                usdchange = "  " + usdchange + "0";
            }
            usdchange += "%";

            for (i = 0; eurch.charAt(i) != '.'; i++) {
                eurchange += eurch.charAt(i);
            }
            if (!eurch.equals("0.0")) {
                for (k = 0; k < 3; k++, i++) {
                    eurchange += eurch.charAt(i);
                }
            } else {
                for (k = 0; k < 2; k++, i++) {
                    eurchange += eurch.charAt(i);
                }
                eurchange = "  " + eurchange + "0";
            }
            eurchange += "%";

            for (i = 0; tomanch.charAt(i) != '.'; i++) {
                tomanchange += tomanch.charAt(i);
            }
            if (!tomanch.equals("0.0")) {
                for (k = 0; k < 3; k++, i++) {
                    tomanchange += tomanch.charAt(i);
                }
            } else {
                for (k = 0; k < 2; k++, i++) {
                    tomanchange += tomanch.charAt(i);
                }
                tomanchange = "  " + tomanchange + "0";

            }
            tomanchange += "%";

            for (i = 0; yench.charAt(i) != '.'; i++) {
                yenchange += yench.charAt(i);
            }
            if (!yench.equals("0.0")) {
                for (k = 0; k < 3; k++, i++) {
                    yenchange += yench.charAt(i);
                }
            } else {
                for (k = 0; k < 2; k++, i++) {
                    yenchange += yench.charAt(i);
                }
                yenchange = "  " + yenchange + "0";
            }
            yenchange += "%";

            for (i = 0; gbpch.charAt(i) != '.'; i++) {
                gbpchange += gbpch.charAt(i);
            }
            if (!gbpch.equals("0.0")) {
                for (k = 0; k < 3; k++, i++) {
                    gbpchange += gbpch.charAt(i);
                }
            } else {
                for (k = 0; k < 2; k++, i++) {
                    gbpchange += gbpch.charAt(i);
                }
                gbpchange = "  " + gbpchange + "0";
            }
            gbpchange += "%";

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onBackClicked(ActionEvent e) {
        swapbutton.setVisible(true);
        table.setVisible(true);
        homelabel.setVisible(true);
        eurbutton.setVisible(true);
        usdbutton.setVisible(true);
        tomanbutton.setVisible(true);
        yenbutton.setVisible(true);
        gbpbutton.setVisible(true);
        profilebutton.setVisible(true);
        exchangebutton.setVisible(true);
        transferbutton.setVisible(true);
        signout.setVisible(true);
        back.setVisible(false);
        usdpricelabel.setVisible(false);
        usdlabel.setVisible(false);
        usdchangelabel.setVisible(false);
        eurlabel.setVisible(false);
        eurpricelabel.setVisible(false);
        eurchangelabel.setVisible(false);
        tomanlabel.setVisible(false);
        tomanpricelabel.setVisible(false);
        tomanchangelabel.setVisible(false);
        yenlabel.setVisible(false);
        yenpricelabel.setVisible(false);
        yenchangelabel.setVisible(false);
        gbplabel.setVisible(false);
        gbppricelabel.setVisible(false);
        gbpchangelabel.setVisible(false);
        pricetitle.setVisible(false);
        changetitle.setVisible(false);

        transferlabel.setVisible(false);
        profilebutton.setVisible(true);
        exchangebutton.setVisible(true);
        transferbutton.setVisible(true);
        table.setVisible(true);
        usdbutton.setVisible(true);
        eurbutton.setVisible(true);
        tomanbutton.setVisible(true);
        yenbutton.setVisible(true);
        gbpbutton.setVisible(true);
        signout.setVisible(true);
        homelabel.setVisible(true);
        signout.setVisible(true);
        back.setVisible(false);
        usdcheck.setVisible(false);
        eurcheck.setVisible(false);
        tomancheck.setVisible(false);
        yencheck.setVisible(false);
        gbpcheck.setVisible(false);
        walletidfield.setVisible(false);
        amountfield.setVisible(false);
        transfertransfer.setVisible(false);
        transferfee.setVisible(false);
        fee.setVisible(false);
        walletnotfound.setVisible(false);
        checklabel.setVisible(false);
        amountlabel.setVisible(false);
        volumetitle.setVisible(false);
        usdvolume.setVisible(false);
        eurvolume.setVisible(false);
        tomanvolume.setVisible(false);
        yenvolume.setVisible(false);
        gbpvolume.setVisible(false);
        lasttradetitel.setVisible(false);
        usdlasttrade.setVisible(false);
        eurlasttrade.setVisible(false);
        tomanlasttrade.setVisible(false);
        yenlasttrade.setVisible(false);
        gbplasttrade.setVisible(false);
    }

    public void onUsdClicked(ActionEvent e) {
        table.setVisible(false);
        homelabel.setVisible(false);
        eurbutton.setVisible(false);
        usdbutton.setVisible(false);
        tomanbutton.setVisible(false);
        yenbutton.setVisible(false);
        gbpbutton.setVisible(false);
        profilebutton.setVisible(false);
        exchangebutton.setVisible(false);
        transferbutton.setVisible(false);
        signout.setVisible(false);
        back.setVisible(true);
        usdlabel.setVisible(true);
        usdpricelabel.setVisible(true);
        usdchangelabel.setVisible(true);
        pricetitle.setVisible(true);
        changetitle.setVisible(true);
        usdvolume.setVisible(true);
        volumetitle.setVisible(true);
        lasttradetitel.setVisible(true);
        usdlasttrade.setVisible(true);
    }

    public void onEurClicked(ActionEvent e) {
        table.setVisible(false);
        homelabel.setVisible(false);
        eurbutton.setVisible(false);
        usdbutton.setVisible(false);
        tomanbutton.setVisible(false);
        yenbutton.setVisible(false);
        gbpbutton.setVisible(false);
        profilebutton.setVisible(false);
        exchangebutton.setVisible(false);
        transferbutton.setVisible(false);
        signout.setVisible(false);
        back.setVisible(true);
        eurlabel.setVisible(true);
        eurpricelabel.setVisible(true);
        eurchangelabel.setVisible(true);
        pricetitle.setVisible(true);
        changetitle.setVisible(true);
        eurvolume.setVisible(true);
        volumetitle.setVisible(true);
        lasttradetitel.setVisible(true);
        eurlasttrade.setVisible(true);
    }

    public void onTomanClicked(ActionEvent e) {
        table.setVisible(false);
        homelabel.setVisible(false);
        eurbutton.setVisible(false);
        usdbutton.setVisible(false);
        tomanbutton.setVisible(false);
        yenbutton.setVisible(false);
        gbpbutton.setVisible(false);
        profilebutton.setVisible(false);
        exchangebutton.setVisible(false);
        transferbutton.setVisible(false);
        signout.setVisible(false);
        back.setVisible(true);
        tomanlabel.setVisible(true);
        tomanpricelabel.setVisible(true);
        tomanchangelabel.setVisible(true);
        pricetitle.setVisible(true);
        changetitle.setVisible(true);
        tomanvolume.setVisible(true);
        volumetitle.setVisible(true);
        lasttradetitel.setVisible(true);
        tomanlasttrade.setVisible(true);
    }

    public void onYenClicked(ActionEvent e) {
        table.setVisible(false);
        homelabel.setVisible(false);
        eurbutton.setVisible(false);
        usdbutton.setVisible(false);
        tomanbutton.setVisible(false);
        yenbutton.setVisible(false);
        gbpbutton.setVisible(false);
        profilebutton.setVisible(false);
        exchangebutton.setVisible(false);
        transferbutton.setVisible(false);
        signout.setVisible(false);
        back.setVisible(true);
        yenlabel.setVisible(true);
        yenpricelabel.setVisible(true);
        yenchangelabel.setVisible(true);
        pricetitle.setVisible(true);
        changetitle.setVisible(true);
        yenvolume.setVisible(true);
        volumetitle.setVisible(true);
        lasttradetitel.setVisible(true);
        yenlasttrade.setVisible(true);
    }

    public void onGbpClicked(ActionEvent e) {
        table.setVisible(false);
        homelabel.setVisible(false);
        eurbutton.setVisible(false);
        usdbutton.setVisible(false);
        tomanbutton.setVisible(false);
        yenbutton.setVisible(false);
        gbpbutton.setVisible(false);
        profilebutton.setVisible(false);
        exchangebutton.setVisible(false);
        transferbutton.setVisible(false);
        signout.setVisible(false);
        back.setVisible(true);
        gbplabel.setVisible(true);
        gbppricelabel.setVisible(true);
        gbpchangelabel.setVisible(true);
        pricetitle.setVisible(true);
        changetitle.setVisible(true);
        gbpvolume.setVisible(true);
        volumetitle.setVisible(true);
        lasttradetitel.setVisible(true);
        gbplasttrade.setVisible(true);
    }

    public void onProfileClicked(ActionEvent e) throws IOException {
        Parent backParent = FXMLLoader.load(getClass().getResource("Profile.fxml"));
        Scene backScene = new Scene(backParent);
        Stage window = (Stage) profilebutton.getScene().getWindow();
        window.setScene(backScene);
        window.show();

    }

    public void onExchangeClicked(ActionEvent e) throws IOException {
        Parent backParent = FXMLLoader.load(getClass().getResource("Exchange.fxml"));
        Scene backScene = new Scene(backParent);
        Stage window = (Stage) exchangebutton.getScene().getWindow();
        window.setScene(backScene);
        window.show();
    }

    public void onSwapClicked(ActionEvent e) throws IOException {
        Parent backParent = FXMLLoader.load(getClass().getResource("Swap.fxml"));
        Scene backScene = new Scene(backParent);
        Stage window = (Stage) swapbutton.getScene().getWindow();
        window.setScene(backScene);
        window.show();
    }

    public void onTransferClicked(ActionEvent e) throws IOException {
        transferlabel.setVisible(true);
        profilebutton.setVisible(false);
        exchangebutton.setVisible(false);
        transferbutton.setVisible(false);
        table.setVisible(false);
        usdbutton.setVisible(false);
        eurbutton.setVisible(false);
        tomanbutton.setVisible(false);
        yenbutton.setVisible(false);
        gbpbutton.setVisible(false);
        signout.setVisible(false);
        homelabel.setVisible(false);
        signout.setVisible(false);
        back.setVisible(true);
        usdcheck.setVisible(true);
        eurcheck.setVisible(true);
        tomancheck.setVisible(true);
        yencheck.setVisible(true);
        gbpcheck.setVisible(true);
        walletidfield.setVisible(true);
        amountfield.setVisible(true);
        transfertransfer.setVisible(true);
        transferfee.setVisible(true);
        fee.setText("0");
        fee.setVisible(true);
        walletnotfound.setVisible(false);
        checklabel.setVisible(false);
        amountlabel.setVisible(false);
        swapbutton.setVisible(false);
    }

    public void ontransfertransferClicked(ActionEvent e) throws IOException {
        double amount = 0;
        int walletid = 1;
        String walletid2 = walletidfield.getText();
        if(!walletid2.equals(""))
            walletid = Integer.parseInt(walletid2);
        String amount2 = amountfield.getText();
        if(amount2.equals(""))
            amountlabel.setVisible(true);
        else {
            amountlabel.setVisible(false);
            amount = Double.parseDouble(amount2);

            double prevamount = 0;
            double prevamountadmin = 0;

            if (checkwalletid(walletid)) {
                walletnotfound.setVisible(false);
                try {
                    Connection connection = DriverManager.getConnection(dataAddress, dataUser, dataPassword);
                    if (currencytype.equals("usd")) {
                        Statement statement = connection.createStatement();

                        double prevtradeamount = 0;
                        String query5 = "SELECT `usdtrade`, `walletID` FROM `person`";
                        ResultSet resultSet5 = statement.executeQuery(query5);
                        while (resultSet5.next()) {
                            if (resultSet5.getInt("walletID") == adminWalletID) {
                                prevtradeamount = resultSet5.getDouble("usdtrade");
                                break;
                            }
                        }
                        prevtradeamount += amount;
                        String query4 = " UPDATE `person` SET `usdtrade` = ? WHERE `walletID` = ?";


                        PreparedStatement preparedStatement4 = connection.prepareStatement(query4);
                        preparedStatement4.setDouble(1, prevtradeamount);
                        preparedStatement4.setInt(2, adminWalletID);
                        preparedStatement4.executeUpdate();

                        String query1 = "SELECT `usd`, `walletID` FROM `person`";
                        ResultSet resultSet = statement.executeQuery(query1);
                        while (resultSet.next()) {
                            if (resultSet.getInt("walletID") == walletid) {
                                prevamount = resultSet.getDouble("usd");
                                break;
                            }
                        }
                        ResultSet resultSet1 = statement.executeQuery(query1);
                        while (resultSet1.next()) {
                            if (resultSet1.getInt("walletID") == adminWalletID) {
                                prevamountadmin = resultSet1.getDouble("usd");
                                break;
                            }
                        }
                        prevamountadmin += amount / 100;
                        fee.setText( amount / 100 + " usd");
                        amount = amount * 99 / 100;
                        amount += prevamount;

                        String query = " UPDATE `person` SET `usd` = ? WHERE `walletID` = ?";


                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setDouble(1, amount);
                        preparedStatement.setInt(2, walletid);
                        preparedStatement.executeUpdate();

                        preparedStatement.setDouble(1, prevamountadmin);
                        preparedStatement.setInt(2, adminWalletID);
                        preparedStatement.executeUpdate();
                        checklabel.setVisible(false);


                    } else if (currencytype.equals("eur")) {
                        Statement statement = connection.createStatement();
                        double prevtradeamount = 0;
                        String query5 = "SELECT `eurtrade`, `walletID` FROM `person`";
                        ResultSet resultSet5 = statement.executeQuery(query5);
                        while (resultSet5.next()) {
                            if (resultSet5.getInt("walletID") == adminWalletID) {
                                prevtradeamount = resultSet5.getDouble("eurtrade");
                                break;
                            }
                        }
                        prevtradeamount += amount;
                        String query4 = " UPDATE `person` SET `eurtrade` = ? WHERE `walletID` = ?";


                        PreparedStatement preparedStatement4 = connection.prepareStatement(query4);
                        preparedStatement4.setDouble(1, prevtradeamount);
                        preparedStatement4.setInt(2, adminWalletID);
                        preparedStatement4.executeUpdate();

                        String query1 = "SELECT `eur`, `walletID` FROM `person`";
                        ResultSet resultSet = statement.executeQuery(query1);
                        while (resultSet.next()) {
                            if (resultSet.getInt("walletID") == walletid) {
                                prevamount = resultSet.getDouble("eur");
                                break;
                            }
                        }

                        ResultSet resultSet1 = statement.executeQuery(query1);
                        while (resultSet1.next()) {
                            if (resultSet1.getInt("walletID") == adminWalletID) {
                                prevamountadmin = resultSet1.getDouble("eur");
                                break;
                            }
                        }
                        prevamountadmin += amount / 100;
                        fee.setText( amount / 100 + " eur");
                        amount = amount * 99 / 100;
                        amount += prevamount;

                        String query = " UPDATE `person` SET `eur` = ? WHERE `walletID` = ?";


                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setDouble(1, amount);
                        preparedStatement.setInt(2, walletid);
                        preparedStatement.executeUpdate();

                        preparedStatement.setDouble(1, prevamountadmin);
                        preparedStatement.setInt(2, adminWalletID);
                        preparedStatement.executeUpdate();
                        checklabel.setVisible(false);
                    } else if (currencytype.equals("toman")) {
                        Statement statement = connection.createStatement();
                        double prevtradeamount = 0;
                        String query5 = "SELECT `tomantrade`, `walletID` FROM `person`";
                        ResultSet resultSet5 = statement.executeQuery(query5);
                        while (resultSet5.next()) {
                            if (resultSet5.getInt("walletID") == adminWalletID) {
                                prevtradeamount = resultSet5.getDouble("tomantrade");
                                break;
                            }
                        }
                        prevtradeamount += amount;
                        String query4 = " UPDATE `person` SET `tomantrade` = ? WHERE `walletID` = ?";


                        PreparedStatement preparedStatement4 = connection.prepareStatement(query4);
                        preparedStatement4.setDouble(1, prevtradeamount);
                        preparedStatement4.setInt(2, adminWalletID);
                        preparedStatement4.executeUpdate();

                        String query1 = "SELECT `toman`, `walletID` FROM `person`";
                        ResultSet resultSet = statement.executeQuery(query1);
                        while (resultSet.next()) {
                            if (resultSet.getInt("walletID") == walletid) {
                                prevamount = resultSet.getDouble("toman");
                                break;
                            }
                        }
                        ResultSet resultSet1 = statement.executeQuery(query1);
                        while (resultSet1.next()) {
                            if (resultSet1.getInt("walletID") == adminWalletID) {
                                prevamountadmin = resultSet1.getDouble("toman");
                                break;
                            }
                        }
                        prevamountadmin += amount / 100;
                        fee.setText( amount / 100 + " toman");
                        amount = amount * 99 / 100;
                        amount += prevamount;
                        String query = " UPDATE `person` SET `toman` = ? WHERE `walletID` = ?";


                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setDouble(1, amount);
                        preparedStatement.setInt(2, walletid);
                        preparedStatement.executeUpdate();

                        preparedStatement.setDouble(1, prevamountadmin);
                        preparedStatement.setInt(2, adminWalletID);
                        preparedStatement.executeUpdate();
                        checklabel.setVisible(false);
                    } else if (currencytype.equals("yen")) {
                        Statement statement = connection.createStatement();
                        double prevtradeamount = 0;
                        String query5 = "SELECT `yentrade`, `walletID` FROM `person`";
                        ResultSet resultSet5 = statement.executeQuery(query5);
                        while (resultSet5.next()) {
                            if (resultSet5.getInt("walletID") == adminWalletID) {
                                prevtradeamount = resultSet5.getDouble("yentrade");
                                break;
                            }
                        }
                        prevtradeamount += amount;
                        String query4 = " UPDATE `person` SET `yentrade` = ? WHERE `walletID` = ?";


                        PreparedStatement preparedStatement4 = connection.prepareStatement(query4);
                        preparedStatement4.setDouble(1, prevtradeamount);
                        preparedStatement4.setInt(2, adminWalletID);
                        preparedStatement4.executeUpdate();


                        String query1 = "SELECT `yen`, `walletID` FROM `person`";
                        ResultSet resultSet = statement.executeQuery(query1);
                        while (resultSet.next()) {
                            if (resultSet.getInt("walletID") == walletid) {
                                prevamount = resultSet.getDouble("yen");
                                break;
                            }
                        }

                        ResultSet resultSet1 = statement.executeQuery(query1);
                        while (resultSet1.next()) {
                            if (resultSet1.getInt("walletID") == adminWalletID) {
                                prevamountadmin = resultSet1.getDouble("yen");
                                break;
                            }
                        }
                        prevamountadmin += amount / 100;
                        fee.setText( amount / 100 + " yen");
                        amount = amount * 99 / 100;
                        amount += prevamount;
                        String query = " UPDATE `person` SET `yen` = ? WHERE `walletID` = ?";


                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setDouble(1, amount);
                        preparedStatement.setInt(2, walletid);
                        preparedStatement.executeUpdate();

                        preparedStatement.setDouble(1, prevamountadmin);
                        preparedStatement.setInt(2, adminWalletID);
                        preparedStatement.executeUpdate();
                        checklabel.setVisible(false);
                    } else if (currencytype.equals("gbp")) {
                        Statement statement = connection.createStatement();
                        double prevtradeamount = 0;
                        String query5 = "SELECT `gbptrade`, `walletID` FROM `person`";
                        ResultSet resultSet5 = statement.executeQuery(query5);
                        while (resultSet5.next()) {
                            if (resultSet5.getInt("walletID") == adminWalletID) {
                                prevtradeamount = resultSet5.getDouble("gbptrade");
                                break;
                            }
                        }
                        prevtradeamount += amount;
                        String query4 = " UPDATE `person` SET `gbptrade` = ? WHERE `walletID` = ?";


                        PreparedStatement preparedStatement4 = connection.prepareStatement(query4);
                        preparedStatement4.setDouble(1, prevtradeamount);
                        preparedStatement4.setInt(2, adminWalletID);
                        preparedStatement4.executeUpdate();


                        String query1 = "SELECT `gbp`, `walletID` FROM `person`";
                        ResultSet resultSet = statement.executeQuery(query1);
                        while (resultSet.next()) {
                            if (resultSet.getInt("walletID") == walletid) {
                                prevamount = resultSet.getDouble("gbp");
                                break;
                            }
                        }

                        ResultSet resultSet1 = statement.executeQuery(query1);
                        while (resultSet1.next()) {
                            if (resultSet1.getInt("walletID") == adminWalletID) {
                                prevamountadmin = resultSet1.getDouble("gbp");
                                break;
                            }
                        }
                        prevamountadmin += amount / 100;
                        fee.setText( amount / 100 + " gbp");
                        amount = amount * 99 / 100;
                        amount += prevamount;
                        String query = " UPDATE `person` SET `gbp` = ? WHERE `walletID` = ?";


                        PreparedStatement preparedStatement = connection.prepareStatement(query);
                        preparedStatement.setDouble(1, amount);
                        preparedStatement.setInt(2, walletid);
                        preparedStatement.executeUpdate();

                        preparedStatement.setDouble(1, prevamountadmin);
                        preparedStatement.setInt(2, adminWalletID);
                        preparedStatement.executeUpdate();
                        checklabel.setVisible(false);
                    } else
                        checklabel.setVisible(true);
                } catch (SQLException w) {
                    System.out.println("error in get connection");
                    w.printStackTrace();
                }
            }
            else
                walletnotfound.setVisible(true);
        }



    }

    public void onusdcheckClicked(ActionEvent e) throws IOException {
        if (usdcheck.isSelected()) {
            gbpcheck.setSelected(false);
            eurcheck.setSelected(false);
            tomancheck.setSelected(false);
            yencheck.setSelected(false);
            currencytype = "usd";
        }
    }

    public void oneurcheckClicked(ActionEvent e) throws IOException {
        if (eurcheck.isSelected()) {
            usdcheck.setSelected(false);
            gbpcheck.setSelected(false);
            tomancheck.setSelected(false);
            yencheck.setSelected(false);
            currencytype = "eur";
        }
    }

    public void ontomancheckClicked(ActionEvent e) throws IOException {
        if (tomancheck.isSelected()) {
            usdcheck.setSelected(false);
            eurcheck.setSelected(false);
            gbpcheck.setSelected(false);
            yencheck.setSelected(false);
            currencytype = "toman";
        }
    }

    public void onyencheckClicked(ActionEvent e) throws IOException {
        if (yencheck.isSelected()) {
            usdcheck.setSelected(false);
            eurcheck.setSelected(false);
            tomancheck.setSelected(false);
            gbpcheck.setSelected(false);
            currencytype = "yen";
        }
    }

    public void ongbpcheckClicked(ActionEvent e) throws IOException {
        if (gbpcheck.isSelected()) {
            usdcheck.setSelected(false);
            eurcheck.setSelected(false);
            tomancheck.setSelected(false);
            yencheck.setSelected(false);
            currencytype = "gbp";
        }
    }
    public boolean checkwalletid(int id) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dataAddress, dataUser, dataPassword);
            Statement statement = connection.createStatement();
            //noinspection SqlResolve
            String query = "SELECT  `walletid` FROM `person` ";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                if (resultSet.getInt("walletid") == id) {
                    return  true;
                }
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}