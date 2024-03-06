package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class UserLoginFormController {

    @FXML
    private AnchorPane UserRoot;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/MainWindow_From.fxml"));
        Stage stage = (Stage) UserRoot.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard");
        stage.centerOnScreen();

    }

    @FXML
    void btnLoginOnAction(ActionEvent event) {

    }

    @FXML
    void hyperSignUpOnAction(ActionEvent event) {

    }

}
