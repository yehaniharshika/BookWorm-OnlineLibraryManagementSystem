package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.bo.AdminSignupBOImpl;
import lk.ijse.dto.AdminSignupDTO;
import lk.ijse.dto.UserSignupDTO;

import java.io.IOException;
import java.sql.SQLException;

public class AdminSignupFormController {

    @FXML
    private JFXButton btnCreateAccount;

    @FXML
    private JFXButton btnDeleteAccount;

    @FXML
    private JFXButton btnUpdateAccount;

    @FXML
    private TextField txtAdminID;

    @FXML
    private TextField txtEmailAddress;

    @FXML
    private TextField txtFirstname;

    @FXML
    private TextField txtLastname;

    @FXML
    private TextField txtNIC;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;
    @FXML
    private AnchorPane root;

    public AdminSignupBOImpl adminSignupBO = new AdminSignupBOImpl();

    @FXML
    void btnCreateAccountOnAction(ActionEvent event) {
        String adminID = txtAdminID.getText();
        String firstName = txtFirstname.getText();
        String lastName = txtLastname.getText();
        String nic = txtNIC.getText();
        String emailAddress = txtEmailAddress.getText();
        String username = txtUserName.getText();
        String password = txtPassword.getText();

        var dto = new AdminSignupDTO(adminID,firstName,lastName,nic,emailAddress,username,password);

        try {
            boolean isSaved = adminSignupBO.saveAdmin(dto);
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Success!!!").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"Error!!!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnDeleteAccountOnAction(ActionEvent event) {
        String adminID = txtAdminID.getText();

        try {
            boolean isDeleted = adminSignupBO.deleteAdmin(adminID);
            if (isDeleted){
                new Alert(Alert.AlertType.INFORMATION,"Deleted !!!").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"not Deleted !!!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnUpdateAccountOnAction(ActionEvent event) {
        String adminId = txtAdminID.getText();
        String firstName = txtFirstname.getText();
        String lastName = txtLastname.getText();
        String nic = txtNIC.getText();
        String emailAddress = txtEmailAddress.getText();
        String username = txtUserName.getText();
        String password = txtPassword.getText();

        var dto = new AdminSignupDTO(adminId,firstName,lastName,nic,emailAddress,username,password);

        try {
            boolean isUpdated = adminSignupBO.updateAdmin(dto);
            if (isUpdated){
                new Alert(Alert.AlertType.INFORMATION,"updated!!!").show();
            }else {
                new Alert(Alert.AlertType.ERROR,"not updated!!!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void hyperLoginHereOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/AdminLogin_Form.fxml"));

        Scene scene = new Scene(rootNode);

        root.getScene().getWindow();
        Stage primaryStage = (Stage) root.getScene().getWindow();

        primaryStage.setScene(scene);
        primaryStage.setTitle("Login Form");
    }

    @FXML
    void txtAuthorIDOnAction(ActionEvent event) {

    }

}
