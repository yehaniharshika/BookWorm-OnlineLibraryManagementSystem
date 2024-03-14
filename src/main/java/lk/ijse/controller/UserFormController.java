package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.custom.impl.UserSignupBOImpl;
import lk.ijse.dto.UserSignupDTO;
import lk.ijse.dto.tm.UserSignupTM;

import java.sql.SQLException;
import java.util.List;

public class UserFormController {

    @FXML
    private TableColumn<?, ?> colEmailAddress;

    @FXML
    private TableColumn<?, ?> colFristName;

    @FXML
    private TableColumn<?, ?> colLastName;

    @FXML
    private TableColumn<?, ?> colNICNumber;

    @FXML
    private TableColumn<?, ?> colPassword;

    @FXML
    private TableColumn<?, ?> colUserID;

    @FXML
    private TableColumn<?, ?> colUserName;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<UserSignupTM> tblUserDetails;

    public UserSignupBOImpl userSignupBO = new UserSignupBOImpl();

    public void initialize(){
        loadAllUsers();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colUserID.setCellValueFactory(new PropertyValueFactory<>("userID"));
        colFristName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colNICNumber.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colEmailAddress.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("username"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
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
                        dto.getEmailAddress(),
                        dto.getUsername(),
                        dto.getPassword()));
            }
            tblUserDetails.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
