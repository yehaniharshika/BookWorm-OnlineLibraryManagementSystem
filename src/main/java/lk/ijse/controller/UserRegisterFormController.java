package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.UserSignUpBo;
import lk.ijse.bo.custom.impl.UserSignupBOImpl;
import lk.ijse.dto.UserSignupDTO;
import lk.ijse.dto.tm.UserSignupTM;

import java.sql.SQLException;
import java.util.List;

public class UserRegisterFormController {

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
    private TableColumn<?, ?> colEmailAddress;

    @FXML
    private TableColumn<?, ?> colFristName;

    @FXML
    private TableColumn<?, ?> colLastName;

    @FXML
    private TableColumn<?, ?> colNICNumber;

    @FXML
    private TableColumn<?, ?> colUserID;

    @FXML
    private TableView<UserSignupTM> tblUserDetails;


    @FXML
    private TextField txtUserID;

    @FXML
    private AnchorPane pane;

    UserSignUpBo userSignupBO = (UserSignUpBo) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.USER);

    public void initialize(){
        generateNextUserID();
        loadAllUsers();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));
        colFristName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colNICNumber.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colEmailAddress.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
    }

    private void loadAllUsers() {
        ObservableList<UserSignupTM> obList = FXCollections.observableArrayList();

        try {

            List<UserSignupDTO> userList = userSignupBO.getAllUsers();

            for (UserSignupDTO dto : userList){
                obList.add(new UserSignupTM(
                        dto.getUserID(),
                        dto.getFirstName(),
                        dto.getLastName(),
                        dto.getNic(),
                        dto.getEmailAddress()));
            }
            tblUserDetails.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

        var dto = new UserSignupDTO(userId,firstName,lastName,nic,emailAddress);

        try {
            boolean isSaved = userSignupBO.saveUser(dto);
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Success!!!").show();
                clearFields();
                generateNextUserID();
                loadAllUsers();
                setCellValueFactory();
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
                loadAllUsers();
                setCellValueFactory();
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

        var dto = new UserSignupDTO(userId,firstName,lastName,nic,emailAddress);

        try {
            boolean isUpdated = userSignupBO.updateUser(dto);
            if (isUpdated){
                new Alert(Alert.AlertType.INFORMATION,"updated!!!").show();
                clearFields();
                loadAllUsers();
                setCellValueFactory();
                generateNextUserID();
            }else {
                new Alert(Alert.AlertType.ERROR,"not updated!!!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            }else {
                new Alert(Alert.AlertType.ERROR,"User not found!!!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
