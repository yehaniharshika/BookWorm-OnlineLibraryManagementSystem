package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class AdminDashboardFormController {

    @FXML
    private AnchorPane MainRoot;

    @FXML
    private AnchorPane Root;

    @FXML
    private JFXButton btnUserTransaction;

    @FXML
    private JFXButton btnBook;

    @FXML
    private JFXButton btnLibraryBranch;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private JFXButton btnTransactionHistory;

    @FXML
    private Label lblAuthorCount;

    @FXML
    private Label lblBookCount;

    @FXML
    private Label lblBorrowCount;

    @FXML
    private Label lblMemberCount;

    @FXML
    private JFXButton btnBookList;
    @FXML
    private JFXButton btnUser;


    public  void  initialize(){
        setDateAndTime();
    }

    private void setDateAndTime() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> updateTime()));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        DateFormat date = new SimpleDateFormat("yyy:MM:dd");
        Calendar cal = Calendar.getInstance();

        int year;
        int month;
        int datee;
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        datee = cal.get(Calendar.DATE);
        lblDate.setText(String.valueOf(LocalDate.now()));
    }

    private void updateTime() {
        LocalTime now = LocalTime.now();
        String formattedTime = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        lblTime.setText(formattedTime);
    }

    @FXML
    void btnDashboardOnAction(ActionEvent event) {

    }

    @FXML
    void btnBookOnAction(ActionEvent event) throws IOException {
        Parent node = FXMLLoader.load(this.getClass().getResource("/view/Book_Form.fxml"));

        this.Root.getChildren().clear();
        this.Root.getChildren().add(node);
    }


    @FXML
    void btnLibraryBranchOnAction(ActionEvent event) throws IOException {
        Parent node = FXMLLoader.load(this.getClass().getResource("/view/LibraryBranch_Form.fxml"));

        this.Root.getChildren().clear();
        this.Root.getChildren().add(node);
    }


    @FXML
    void btnTransactionHistoryOnAction(ActionEvent event) throws IOException {
        Parent node = FXMLLoader.load(this.getClass().getResource("/view/reservationHistory.fxml"));

        this.Root.getChildren().clear();
        this.Root.getChildren().add(node);
    }


    @FXML
    void btnUserTransactionOnAction(ActionEvent event) throws IOException {
        Parent node = FXMLLoader.load(this.getClass().getResource("/view/Reservation_From.fxml"));

        this.Root.getChildren().clear();
        this.Root.getChildren().add(node);
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/AdminLogin_Form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) Root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("BookWorm");
        stage.centerOnScreen();
    }



    @FXML
    void btnUserOnAction(ActionEvent event) throws IOException {
        Parent node = FXMLLoader.load(this.getClass().getResource("/view/UserRegister_Form.fxml"));

        this.Root.getChildren().clear();
        this.Root.getChildren().add(node);
    }

    @FXML
    void btnTimeOutOnAction(ActionEvent event) throws IOException {
        Parent node = FXMLLoader.load(this.getClass().getResource("/view/timeOutList_Form.fxml"));

        this.Root.getChildren().clear();
        this.Root.getChildren().add(node);
    }


}
