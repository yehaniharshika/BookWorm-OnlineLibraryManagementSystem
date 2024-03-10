package lk.ijse.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class AdminLoginFormController {

    @FXML
    private AnchorPane adminRoot;

    @FXML
    private TextField txtAdminID;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    void btnLoginOnAction(ActionEvent event) {

    }

    @FXML
    void hyperSignUpOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/AdminSignup_Form.fxml"));

        Scene scene = new Scene(rootNode);

        adminRoot.getScene().getWindow();
        Stage primaryStage = (Stage) adminRoot.getScene().getWindow();

        primaryStage.setScene(scene);
        primaryStage.setTitle("Signup Form");
        primaryStage.show();
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/MainWindow_From.fxml"));
        Stage stage = (Stage) adminRoot.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard");
        stage.centerOnScreen();
    }


}
