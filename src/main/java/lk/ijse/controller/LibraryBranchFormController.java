package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.AdminSignupBO;
import lk.ijse.bo.custom.LibraryBranchBO;
import lk.ijse.bo.custom.impl.AdminSignupBOImpl;
import lk.ijse.bo.custom.impl.LibraryBranchBOImpl;
import lk.ijse.dto.AdminSignupDTO;
import lk.ijse.dto.LibraryBranchDTO;
import lk.ijse.dto.tm.LibraryBranchTM;

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
    private TableColumn<?, ?> colAdminID;


    @FXML
    private TableColumn<?, ?> colLocation;

    @FXML
    private TableView<LibraryBranchTM> tblLibraryBranch;

    @FXML
    private TextField txtBranchId;

    @FXML
    private TextField txtBranchName;

    @FXML
    private TextField txtLocation;

    @FXML
    private AnchorPane childpane;

    LibraryBranchBO libraryBranchBO = (LibraryBranchBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.LIBRARY_BRANCH);
    AdminSignupBO adminSignupBO = (AdminSignupBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.ADMIN);

    public void initialize(){
        loadAllAdminIds();
        getAllLibraryBranches();
        setCellValueFactory();
        generateNextLibraryBranchID();
        tableListener();
    }

    private void tableListener() {
        tblLibraryBranch.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> {
            setData(newValue);

        });
    }

    private void setData(LibraryBranchTM newValue) {
        if (newValue != null){
            txtBranchId.setText(newValue.getBranchID());
            txtBranchName.setText(newValue.getBranchName());
            txtLocation.setText(String.valueOf(newValue.getLocation()));
            cmbAdminId.setValue(String.valueOf(newValue.getAdminID()));
        }
    }

    private void setCellValueFactory() {
        colBranchId.setCellValueFactory(new PropertyValueFactory<>("branchID"));
        colBranchName.setCellValueFactory(new PropertyValueFactory<>("branchName"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colAdminID.setCellValueFactory(new PropertyValueFactory<>("adminID"));

    }

    private void generateNextLibraryBranchID() {
        try {
            String branchID = libraryBranchBO.generateNextBranchId();
            txtBranchId.setText(branchID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   private void getAllLibraryBranches() {
        ObservableList<LibraryBranchTM> obList = FXCollections.observableArrayList();

        try {
            ArrayList<LibraryBranchDTO> libraryBranchList = libraryBranchBO.getAllLibraryBranches();

            for (LibraryBranchDTO dto : libraryBranchList){
                obList.add(new LibraryBranchTM(
                        dto.getBranchID(),
                        dto.getBranchName(),
                        dto.getLocation(),
                        dto.getAdminID()
                ));
            }
            tblLibraryBranch.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
        clearFields();
    }

    private void clearFields() {
        txtBranchId.setText("");
        txtBranchName.setText("");
        txtLocation.setText("");
        cmbAdminId.setValue("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String branchID = txtBranchId.getText();

        try {
            boolean isDeleted = libraryBranchBO.deleteLibraryBranch(branchID);

            if (isDeleted){
                new Alert(Alert.AlertType.INFORMATION,"deleted !!").show();
                clearFields();
                getAllLibraryBranches();
                setCellValueFactory();
                generateNextLibraryBranchID();
            }else {
                new Alert(Alert.AlertType.ERROR,"not deleted !!").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String branchID = txtBranchId.getText();
        String branchName = txtBranchName.getText();
        String location = txtLocation.getText();
        String adminID = cmbAdminId.getValue();

        var dto = new LibraryBranchDTO(branchID,branchName,location,adminID);

        try {
            boolean isSaved = libraryBranchBO.saveLibraryBranch(dto);

            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Success!!!").show();
                clearFields();
                generateNextLibraryBranchID();
                getAllLibraryBranches();
                setCellValueFactory();

            }else {
                new Alert(Alert.AlertType.ERROR,"adding not successfully!!!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String branchID = txtBranchId.getText();
        String branchName = txtBranchName.getText();
        String location = txtLocation.getText();
        String adminID = cmbAdminId.getValue();

        var dto = new LibraryBranchDTO(branchID,branchName,location,adminID);

        try {
            boolean isUpdated = libraryBranchBO.updateLibraryBranch(adminID,dto);

            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"Updated!!!").show();
                clearFields();
                getAllLibraryBranches();
                setCellValueFactory();
                generateNextLibraryBranchID();
            }else {
                new Alert(Alert.AlertType.ERROR,"not Updated!!!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cmbAdminOnAction(ActionEvent event) {
        String adminID = cmbAdminId.getValue();
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String branchID = txtBranchId.getText();

        try {
            LibraryBranchDTO dto = libraryBranchBO.searchLibraryBranch(branchID);

            if (dto != null){
                txtBranchId.setText(dto.getBranchID());
                txtBranchName.setText(dto.getBranchName());
                txtLocation.setText(dto.getLocation());
                cmbAdminId.setValue(String.valueOf(dto.getAdminID()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
