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
import lk.ijse.bo.custom.impl.UserSignupBOImpl;
import lk.ijse.dto.UserSignupDTO;

import java.io.IOException;
import java.sql.SQLException;

public class UserSignupFormController {

    @FXML
    private JFXButton btnCreateAccount;

    @FXML
    private JFXButton btnDeleteAccount;

    @FXML
    private JFXButton btnUpdateAccount;

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
    private TextField txtUserID;

    @FXML
    private TextField txtUserName;

    @FXML
    private AnchorPane pane;

    public UserSignupBOImpl userSignupBO = new UserSignupBOImpl();

    public void initialize(){
        generateNextUserID();
    }

    private void generateNextUserID() {
        try {
            String userID = userSignupBO.generateNextUserId();
            txtUserID.setText(userID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnCreateAccountOnAction(ActionEvent event) {
        String userId = txtUserID.getText();
        String firstName = txtFirstname.getText();
        String lastName = txtLastname.getText();
        String nic = txtNIC.getText();
        String emailAddress = txtEmailAddress.getText();
        String username = txtUserName.getText();
        String password = txtPassword.getText();

        var dto = new UserSignupDTO(userId,firstName,lastName,nic,emailAddress,username,password);

        try {
            boolean isSaved = userSignupBO.saveUser(dto);
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Success!!!").show();
                clearFields();
                generateNextUserID();
            }else {
                new Alert(Alert.AlertType.ERROR,"Error!!!").show();
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }

    }

    private void clearFields() {
        txtUserID.setText("");
        txtFirstname.setText("");
        txtLastname.setText("");
        txtNIC.setText("");
        txtEmailAddress.setText("");
        txtUserName.setText("");
        txtPassword.setText("");
    }

    @FXML
    void btnDeleteAccountOnAction(ActionEvent event) {
        String userID = txtUserID.getText();

        try {
            boolean isDeleted = userSignupBO.deleteUser(userID);
            if (isDeleted){
                new Alert(Alert.AlertType.INFORMATION,"Deleted !!!").show();
                clearFields();
                generateNextUserID();
            }else {
                new Alert(Alert.AlertType.ERROR,"not Deleted !!!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void btnUpdateAccountOnAction(ActionEvent event) {
        String userId = txtUserID.getText();
        String firstName = txtFirstname.getText();
        String lastName = txtLastname.getText();
        String nic = txtNIC.getText();
        String emailAddress = txtEmailAddress.getText();
        String username = txtUserName.getText();
        String password = txtPassword.getText();

        var dto = new UserSignupDTO(userId,firstName,lastName,nic,emailAddress,username,password);

        try {
            boolean isUpdated = userSignupBO.updateUser(dto);
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
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/UserLogin_Form.fxml"));

        Scene scene = new Scene(rootNode);

        pane.getScene().getWindow();
        Stage primaryStage = (Stage) pane.getScene().getWindow();

        primaryStage.setScene(scene);
        primaryStage.setTitle("Login Form");
    }

    @FXML
    void txtAuthorIDOnAction(ActionEvent event) {
        String userID = txtUserID.getText();

        try {
            UserSignupDTO dto = userSignupBO.searchUser(userID);

            if (dto != null){
                txtUserID.setText(dto.getUserID());
                txtFirstname.setText(dto.getFirstName());
                txtLastname.setText(dto.getLastName());
                txtNIC.setText(dto.getNic());
                txtEmailAddress.setText(dto.getEmailAddress());
                txtUserName.setText(dto.getUsername());
                txtPassword.setText(dto.getPassword());
            }else {
                new Alert(Alert.AlertType.ERROR,"User not found!!!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
