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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class Swap implements Initializable {

    @FXML
    private CheckBox eurcheckbuy;

    @FXML
    private CheckBox eurchecksell;

    @FXML
    private CheckBox gbpcheckbuy;

    @FXML
    private CheckBox gbpchecksell;

    @FXML
    private TextField sellamountfield;

    @FXML
    private Label time, sellchecklabel, buychecklabel, amountlabel, notenoughmoney, fee, transferfee, equal, sellequal, buyequal;
    @FXML
    private CheckBox tomancheckbuy;

    @FXML
    private CheckBox tomanchecksell;

    @FXML
    private CheckBox usdcheckbuy;

    @FXML
    private CheckBox usdchecksell;

    @FXML
    private CheckBox yencheckbuy;

    @FXML
    private CheckBox yenchecksell;
    @FXML
    private Button swapswap, back;

    public String selltype = "";
    public String buytype = "";
    public String selltypetrade = "";
    public String buytypetrade = "";

    public String selltypelasttrade = "";
    public String buytypelasttrade = "";

    String dataAddress = "jdbc:Mysql://localhost:3306/infos";
    String dataUser = "root";
    String dataPassword = "";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initClock();

    }

    private void initClock() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            time.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public void onusdchecksellClicked(ActionEvent e) throws IOException {
        if (usdchecksell.isSelected()) {
            usdcheckbuy.setSelected(false);
            gbpchecksell.setSelected(false);
            eurchecksell.setSelected(false);
            tomanchecksell.setSelected(false);
            yenchecksell.setSelected(false);
            selltype = "`usd`";
            selltypetrade = "`usdtrade`";
            selltypelasttrade = "`usdlasttrade`";
        }
    }

    public void oneurchecksellClicked(ActionEvent e) throws IOException {
        if (eurchecksell.isSelected()) {
            eurcheckbuy.setSelected(false);
            usdchecksell.setSelected(false);
            gbpchecksell.setSelected(false);
            tomanchecksell.setSelected(false);
            yenchecksell.setSelected(false);
            selltype = "`eur`";
            selltypetrade = "`eurtrade`";
            selltypelasttrade = "`eurlasttrade`";
        }
    }

    public void ontomanchecksellClicked(ActionEvent e) throws IOException {
        if (tomanchecksell.isSelected()) {
            tomancheckbuy.setSelected(false);
            usdchecksell.setSelected(false);
            eurchecksell.setSelected(false);
            gbpchecksell.setSelected(false);
            yenchecksell.setSelected(false);
            selltype = "`toman`";
            selltypetrade = "`tomantrade`";
            selltypelasttrade = "`tomanlasttrade`";
        }
    }

    public void onyenchecksellClicked(ActionEvent e) throws IOException {
        if (yenchecksell.isSelected()) {
            yencheckbuy.setSelected(false);
            usdchecksell.setSelected(false);
            eurchecksell.setSelected(false);
            tomanchecksell.setSelected(false);
            gbpchecksell.setSelected(false);
            selltype = "`yen`";
            selltypetrade = "`yentrade`";
            selltypelasttrade = "`yenlasttrade`";
        }
    }

    public void ongbpchecksellClicked(ActionEvent e) throws IOException {
        if (gbpchecksell.isSelected()) {
            gbpcheckbuy.setSelected(false);
            usdchecksell.setSelected(false);
            eurchecksell.setSelected(false);
            tomanchecksell.setSelected(false);
            yenchecksell.setSelected(false);
            selltype = "`gbp`";
            selltypetrade = "`gbptrade`";
            selltypelasttrade = "`gbplasttrade`";
        }
    }

    public void onusdcheckbuyClicked(ActionEvent e) throws IOException {
        if (usdcheckbuy.isSelected()) {
            usdchecksell.setSelected(false);
            gbpcheckbuy.setSelected(false);
            eurcheckbuy.setSelected(false);
            tomancheckbuy.setSelected(false);
            yencheckbuy.setSelected(false);
            buytype = "`usd`";
            buytypetrade = "`usdtrade`";
            buytypelasttrade = "`usdlasttrade`";
        }
    }

    public void oneurcheckbuyClicked(ActionEvent e) throws IOException {
        if (eurcheckbuy.isSelected()) {
            eurchecksell.setSelected(false);
            usdcheckbuy.setSelected(false);
            gbpcheckbuy.setSelected(false);
            tomancheckbuy.setSelected(false);
            yencheckbuy.setSelected(false);
            buytype = "`eur`";
            buytypetrade = "`eurtrade`";
            buytypelasttrade = "`eurlasttrade`";
        }
    }

    public void ontomancheckbuyClicked(ActionEvent e) throws IOException {
        if (tomancheckbuy.isSelected()) {
            tomanchecksell.setSelected(false);
            usdcheckbuy.setSelected(false);
            eurcheckbuy.setSelected(false);
            gbpcheckbuy.setSelected(false);
            yencheckbuy.setSelected(false);
            buytype = "`toman`";
            buytypetrade = "`tomantrade`";
            buytypelasttrade = "`tomanlasttrade`";
        }
    }

    public void onyencheckbuyClicked(ActionEvent e) throws IOException {
        if (yencheckbuy.isSelected()) {
            yenchecksell.setSelected(false);
            usdcheckbuy.setSelected(false);
            eurcheckbuy.setSelected(false);
            tomancheckbuy.setSelected(false);
            gbpcheckbuy.setSelected(false);
            buytype = "`yen`";
            buytypetrade = "`yentrade`";
            buytypelasttrade = "`yenlasttrade`";
        }
    }

    public void ongbpcheckbuyClicked(ActionEvent e) throws IOException {
        if (gbpcheckbuy.isSelected()) {
            gbpchecksell.setSelected(false);
            eurcheckbuy.setSelected(false);
            tomancheckbuy.setSelected(false);
            yencheckbuy.setSelected(false);
            buytype = "`gbp`";
            buytypetrade = "`gbptrade`";
            buytypelasttrade = "`gbplasttrade`";
        }
    }

    public void onswapswapClicked(ActionEvent e) {
        double sellprevamount = 0;
        double buyprevamount = 0;
        double sellamount = 0;
        double buyamount = 0;
        String sellamount2 = sellamountfield.getText();

         if(!selltype.equals("") && !buytype.equals("")){
            sellchecklabel.setVisible(false);
            amountlabel.setVisible(false);
            sellamount = Double.parseDouble(sellamount2);
            if (checkmoney(sellamount)) {
                notenoughmoney.setVisible(false);
                try {
                    Connection connection = DriverManager.getConnection(dataAddress, dataUser, dataPassword);

                    Statement statement = connection.createStatement();
                    String query1 = "SELECT `index`," + selltype + ", " + buytype + "FROM `person`";
                    ResultSet resultSet = statement.executeQuery(query1);
                    while (resultSet.next()) {
                        if (resultSet.getInt("index") == LoginPage.number) {
                            sellprevamount = resultSet.getDouble(selltype.substring(1, selltype.length() - 1));
                            buyprevamount = resultSet.getDouble(buytype.substring(1, buytype.length() - 1));
                            break;
                        }
                    }

                    fee.setText(sellamount / 100 + "  " + selltype.substring(1, selltype.length() - 1));
                    fee.setVisible(true);
                    transferfee.setVisible(true);

                    if (selltype.equals("`usd`") && buytype.equals("`eur`"))
                        buyamount = sellamount * Double.parseDouble(HomePage.eur) / Double.parseDouble(HomePage.usd);

                    else if (selltype.equals("`usd`") && buytype.equals("`toman`"))
                        buyamount = sellamount * Double.parseDouble(HomePage.toman) / Double.parseDouble(HomePage.usd);

                    else if (selltype.equals("`usd`") && buytype.equals("`yen`"))
                        buyamount = sellamount * Double.parseDouble(HomePage.yen) / Double.parseDouble(HomePage.usd);

                    else if (selltype.equals("`usd`") && buytype.equals("`gbp`"))
                        buyamount = sellamount * Double.parseDouble(HomePage.gbp) / Double.parseDouble(HomePage.usd);

                    else if (selltype.equals("`eur`") && buytype.equals("`usd`"))
                        buyamount = sellamount * Double.parseDouble(HomePage.usd) / Double.parseDouble(HomePage.eur);

                    else if (selltype.equals("`eur`") && buytype.equals("`toman`"))
                        buyamount = sellamount * Double.parseDouble(HomePage.toman) / Double.parseDouble(HomePage.eur);

                    else if (selltype.equals("`eur`") && buytype.equals("`yen`"))
                        buyamount = sellamount * Double.parseDouble(HomePage.yen) / Double.parseDouble(HomePage.eur);

                    else if (selltype.equals("`eur`") && buytype.equals("`gbp`"))
                        buyamount = sellamount * Double.parseDouble(HomePage.gbp) / Double.parseDouble(HomePage.eur);

                    else if (selltype.equals("`toman`") && buytype.equals("`usd`"))
                        buyamount = sellamount * Double.parseDouble(HomePage.usd) / Double.parseDouble(HomePage.toman);

                    else if (selltype.equals("`toman`") && buytype.equals("`eur`"))
                        buyamount = sellamount * Double.parseDouble(HomePage.eur) / Double.parseDouble(HomePage.toman);

                    else if (selltype.equals("`toman`") && buytype.equals("`yen`"))
                        buyamount = sellamount * Double.parseDouble(HomePage.yen) / Double.parseDouble(HomePage.toman);

                    else if (selltype.equals("`toman`") && buytype.equals("`gbp`"))
                        buyamount = sellamount * Double.parseDouble(HomePage.gbp) / Double.parseDouble(HomePage.toman);

                    else if (selltype.equals("`yen`") && buytype.equals("`usd`"))
                        buyamount = sellamount * Double.parseDouble(HomePage.usd) / Double.parseDouble(HomePage.yen);

                    else if (selltype.equals("`yen`") && buytype.equals("`toman`"))
                        buyamount = sellamount * Double.parseDouble(HomePage.toman) / Double.parseDouble(HomePage.yen);

                    else if (selltype.equals("`yen`") && buytype.equals("`eur`"))
                        buyamount = sellamount * Double.parseDouble(HomePage.eur) / Double.parseDouble(HomePage.yen);

                    else if (selltype.equals("`yen`") && buytype.equals("`gbp`"))
                        buyamount = sellamount * Double.parseDouble(HomePage.gbp) / Double.parseDouble(HomePage.yen);

                    else if (selltype.equals("`gbp`") && buytype.equals("`usd`"))
                        buyamount = sellamount * Double.parseDouble(HomePage.usd) / Double.parseDouble(HomePage.gbp);

                    else if (selltype.equals("`gbp`") && buytype.equals("`toman`"))
                        buyamount = sellamount * Double.parseDouble(HomePage.toman) / Double.parseDouble(HomePage.gbp);

                    else if (selltype.equals("`gbp`") && buytype.equals("`yen`"))
                        buyamount = sellamount * Double.parseDouble(HomePage.yen) / Double.parseDouble(HomePage.gbp);

                    else if (selltype.equals("`gbp`") && buytype.equals("`eur`"))
                        buyamount = sellamount * Double.parseDouble(HomePage.eur) / Double.parseDouble(HomePage.gbp);
                    else {
                        return;
                    }
                    sellequal.setText(Double.parseDouble(String.format("%.02f", sellamount)) + "  " + selltype.substring(1, selltype.length() - 1));
                    sellequal.setVisible(true);

                    buyequal.setText(Double.parseDouble(String.format("%.02f", buyamount)) + "  " + buytype.substring(1, buytype.length() - 1));
                    buyequal.setVisible(true);

                    equal.setVisible(true);


                    double prevtradeamountbuy = 0;
                    double prevtradeamountsell = 0;
                    String query5 = "SELECT " + buytypetrade + ", "+ selltypetrade +", `walletID` FROM `person`";
                    ResultSet resultSet5 = statement.executeQuery(query5);
                    while (resultSet5.next()) {
                        if (resultSet5.getInt("walletID") == HomePage.adminWalletID) {
                            prevtradeamountsell = resultSet5.getDouble(selltypetrade.substring(1, selltypetrade.length() - 1));
                            prevtradeamountbuy = resultSet5.getDouble(buytypetrade.substring(1, buytypetrade.length() - 1));

                            break;
                        }
                    }
                    prevtradeamountsell += sellamount;
                    prevtradeamountbuy += buyamount;
                    String query4 = " UPDATE `person` SET" + selltypetrade + " = ?," + buytypetrade + "  = ?," + selltypelasttrade + "  = ?," + buytypelasttrade + "  = ?" +"WHERE `walletID` = ?";


                    PreparedStatement preparedStatement4 = connection.prepareStatement(query4);
                    preparedStatement4.setDouble(1, Double.parseDouble(String.format("%.02f", prevtradeamountsell)));
                    preparedStatement4.setDouble(2, Double.parseDouble(String.format("%.02f", prevtradeamountbuy)));
                    preparedStatement4.setString(3, Double.parseDouble(String.format("%.02f", sellamount)) + "  " + selltype.substring(1, selltype.length() - 1) + "  ->  " + Double.parseDouble(String.format("%.02f", buyamount)) + "  " + buytype.substring(1, buytype.length() - 1));
                    preparedStatement4.setString(4, Double.parseDouble(String.format("%.02f", sellamount)) + "  " + selltype.substring(1, selltype.length() - 1) + "  ->  " + Double.parseDouble(String.format("%.02f", buyamount)) + "  " + buytype.substring(1, buytype.length() - 1));
                    preparedStatement4.setInt(5, HomePage.adminWalletID);
                    preparedStatement4.executeUpdate();


                    double prevamountadmin = 0;
                    String query2 = "SELECT " + selltype + ", `walletID` FROM `person`";
                    ResultSet resultSet1 = statement.executeQuery(query2);
                    while (resultSet1.next()) {
                        if (resultSet1.getInt("walletID") == HomePage.adminWalletID) {
                            prevamountadmin = resultSet1.getDouble(selltype.substring(1, selltype.length() - 1));
                            break;
                        }
                    }
                    prevamountadmin += sellamount / 100;

                    sellchecklabel.setVisible(false);
                    buychecklabel.setVisible(false);
                    sellamount = sellamount * 101 / 100;
                    sellprevamount -= sellamount;
                    buyamount = buyamount * 99 / 100;
                    buyprevamount += buyamount;

                    String query = " UPDATE `person` SET" + selltype + " = ?," + buytype + "  = ?" + "WHERE `index` = ?";

                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setDouble(1, Double.parseDouble(String.format("%.02f", sellprevamount)));
                    preparedStatement.setDouble(2, Double.parseDouble(String.format("%.02f", buyprevamount)));
                    preparedStatement.setInt(3, LoginPage.number);
                    preparedStatement.executeUpdate();

                    String query3 = " UPDATE `person` SET" + selltype + " = ? WHERE `walletID` = ?";

                    PreparedStatement preparedStatement2 = connection.prepareStatement(query3);
                    preparedStatement2.setDouble(1, Double.parseDouble(String.format("%.02f", prevamountadmin)));
                    preparedStatement2.setInt(2, HomePage.adminWalletID);
                    preparedStatement2.executeUpdate();

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
            else
                notenoughmoney.setVisible(true);

        }
        else  if(buytype.equals("") && selltype.equals("")){
            sellchecklabel.setVisible(true);
            buychecklabel.setVisible(true);
        }
        else if(selltype.equals("")) {
            sellchecklabel.setVisible(true);
            buychecklabel.setVisible(false);
        }
        else if(buytype.equals("")) {
            buychecklabel.setVisible(true);
            sellchecklabel.setVisible(false);
        }
         if (sellamount2.equals(""))
            amountlabel.setVisible(true);
        if (!sellamount2.equals(""))
            amountlabel.setVisible(false);
    }

    public boolean checkmoney(double sellamount) {
        try {
            double asset = 0;

            Connection connection = DriverManager.getConnection(dataAddress, dataUser, dataPassword);

            Statement statement = connection.createStatement();
            String query1 = "SELECT `index`," + selltype + "FROM `person`";
            ResultSet resultSet = statement.executeQuery(query1);
            while (resultSet.next()) {
                if (resultSet.getInt("index") == LoginPage.number) {
                     asset = resultSet.getDouble(selltype.substring(1, selltype.length() - 1));
                    break;
                }
            }
            return asset >= sellamount;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public  void onBackClicked(ActionEvent e) throws IOException {
        Parent backParent = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        Scene backScene = new Scene(backParent);
        Stage window = (Stage) back.getScene().getWindow();
        window.setScene(backScene);
        window.show();
    }
}
