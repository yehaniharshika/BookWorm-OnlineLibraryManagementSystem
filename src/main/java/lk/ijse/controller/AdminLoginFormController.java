package lk.ijse.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.AdminLoginBOImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class AdminLoginFormController {

    @FXML
    private AnchorPane adminRoot;

    @FXML
    private TextField txtAdminID;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;

    public AdminLoginBOImpl adminLoginBO = new AdminLoginBOImpl();

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        String adminID =txtAdminID.getText();
        String username = txtUserName.getText();
        String password = txtPassword.getText();


        try {
            if (adminID.isEmpty() || username.isEmpty() || password.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please enter your username and password !!!!").show();
            } else {
                boolean dto = adminLoginBO.checkAdminCredentials(adminID,username,password);

                if (dto) {
                    Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/AdminDashboard_Form.fxml"));

                    Scene scene = new Scene(rootNode);

                    Stage primaryStage = (Stage) this.adminRoot.getScene().getWindow();
                    primaryStage.setScene(scene);

                    primaryStage.setTitle(" BookWorm Dashboard");
                    primaryStage.centerOnScreen();
                    primaryStage.show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Credentials are wrong!!!!!").show();
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
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
