package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.custom.impl.UserLoginBoImpl;

import java.io.IOException;
import java.sql.SQLException;

public class UserLoginFormController {

    @FXML
    private AnchorPane UserRoot;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;

    public UserLoginBoImpl userLoginBo = new UserLoginBoImpl();
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
        String username = txtUserName.getText();
        String password = txtPassword.getText();


        try {
            if (username.isEmpty() || password.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please enter your username and password !!!!").show();
            } else {
                boolean dto = userLoginBo.checkUserCredentials(username,password);

                if (dto) {
                    Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashboard_Form.fxml"));

                    Scene scene = new Scene(rootNode);

                    Stage primaryStage = (Stage) this.UserRoot.getScene().getWindow();
                    primaryStage.setScene(scene);

                    primaryStage.setTitle(" User Dashboard");
                    primaryStage.centerOnScreen();
                    primaryStage.show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Credentials are wrong!!!!!").show();
                }
            }
        } catch (SQLException | IOException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void hyperSignUpOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/UserSignup_Form.fxml"));

        Scene scene = new Scene(rootNode);

        UserRoot.getScene().getWindow();
        Stage primaryStage = (Stage) UserRoot.getScene().getWindow();

        primaryStage.setScene(scene);
        primaryStage.setTitle("Signup Form");
        primaryStage.show();
    }

}
