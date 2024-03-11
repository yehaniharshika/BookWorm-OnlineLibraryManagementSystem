package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.AdminSignupBOImpl;
import lk.ijse.bo.LibraryBranchBOImpl;
import lk.ijse.dto.AdminSignupDTO;
import lk.ijse.dto.LibraryBranchDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class LibraryBranchFormController {

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXComboBox<String> cmbAdminId;

    @FXML
    private TableColumn<?, ?> colBranchId;

    @FXML
    private TableColumn<?, ?> colBranchName;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colLocation;

    @FXML
    private TableView<?> tblLibraryBranch;

    @FXML
    private TextField txtBranchId;

    @FXML
    private TextField txtBranchName;

    @FXML
    private TextArea txtDescription;

    @FXML
    private TextField txtLocation;

    @FXML
    private AnchorPane childpane;

    public LibraryBranchBOImpl libraryBranchBO = new LibraryBranchBOImpl();
    public AdminSignupBOImpl adminSignupBO = new AdminSignupBOImpl();

    public void initialize(){
        loadAllAdminIds();
    }

    private void loadAllAdminIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            ArrayList<AdminSignupDTO> idList = adminSignupBO.getAllAdmins();

            for (AdminSignupDTO dto : idList){
                obList.add(dto.getAdminID());
            }
            cmbAdminId.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String branchID = txtBranchId.getText();
        String branchName = txtBranchName.getText();
        String location = txtLocation.getText();
        String description = txtDescription.getText();
        String adminID = cmbAdminId.getId();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void cmbAdminOnAction(ActionEvent event) {

    }

    @FXML
    void txtBranchIdOnAction(ActionEvent event) {

    }

}
