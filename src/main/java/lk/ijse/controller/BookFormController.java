/*
package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.custom.impl.BookBOImpl;
import lk.ijse.bo.custom.impl.LibraryBranchBOImpl;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.LibraryBranchDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class BookFormController {

    @FXML
    private JFXComboBox<String> cmbBranchId;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private Label lblBranchName;

    @FXML
    private AnchorPane miniRoot;

    @FXML
    private TextField txtAuthorName;

    @FXML
    private TextField txtAvailability;

    @FXML
    private TextField txtBookId;

    @FXML
    private TextField txtBookName;

    @FXML
    private TextField txtGenre;

    @FXML
    private TextField txtQty;

    public BookBOImpl bookBO =  new BookBOImpl();
    public LibraryBranchBOImpl libraryBranchBO = new LibraryBranchBOImpl();


    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    public void  initialize(){
        loadAllBranchIDs();
        setAvailabilityStatus();
        generateNextBookID();

        txtQty.textProperty().addListener((observable, oldValue, newValue) -> {
            setAvailabilityStatus();
        });


    }

    private void generateNextBookID() {
        try {
            String branchID = bookBO.generateNextBookId();
            txtBookId.setText(branchID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setAvailabilityStatus() {
        String qtyText = txtQty.getText();

        if (!qtyText.isEmpty()) {
            int qtyOnHand = Integer.parseInt(qtyText);

            if (qtyOnHand == 0) {
                txtAvailability.setText("Not Available");
                txtAvailability.setStyle("-fx-text-fill: red; -fx-font-weight: bold");
            } else {
                txtAvailability.setText("Available");
                txtAvailability.setStyle("-fx-text-fill: blue; -fx-font-weight: bold");
            }
        }
    }


    private void loadAllBranchIDs() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            ArrayList<LibraryBranchDTO> idList = libraryBranchBO.getAllLibraryBranches();

            for (LibraryBranchDTO dto : idList){
                obList.add(dto.getBranchID());
            }
            cmbBranchId.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void clearFields() {
        txtBookId.setText("");
        txtBookName.setText("");
        txtAuthorName.setText("");
        txtGenre.setText("");
        txtQty.setText("");
        txtAvailability.setText("");
        cmbBranchId.setValue("");
        lblBranchName.setText("");

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String bookID = txtBookId.getText();

        try {
            boolean isDeleted = bookBO.deleteBook(bookID);

            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION,"deleted !!!").show();
                clearFields();
                generateNextBookID();
            }else {
                new Alert(Alert.AlertType.ERROR,"not deleted !!!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String bookID = txtBookId.getText();
        String bookName = txtBookName.getText();
        String authorName = txtAuthorName.getText();
        String bookGenre = txtGenre.getText();
        int qtyOnHand = Integer.parseInt(txtQty.getText());
        String  availabilityStatus = txtAvailability.getText();
        String branchID = cmbBranchId.getValue();

        var dto = new BookDTO(bookID,bookName,authorName,bookGenre,qtyOnHand,availabilityStatus,branchID);

        try {
            boolean isSaved = bookBO.saveBook(dto);
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Success!!!").show();
                clearFields();
                generateNextBookID();
            }else {
                new Alert(Alert.AlertType.ERROR,"Error!!!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String bookID = txtBookId.getText();
        String bookName = txtBookName.getText();
        String authorName = txtAuthorName.getText();
        String bookGenre = txtGenre.getText();
        int qtyOnHand= Integer.parseInt(txtQty.getText());
        String  availabilityStatus = txtAvailability.getText();
        String branchID = cmbBranchId.getValue();

        var dto = new BookDTO(bookID,bookName,authorName,bookGenre,qtyOnHand,availabilityStatus,branchID);

        try {
            boolean isUpdated= bookBO.updateBook(dto);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"updated!!!").show();
                clearFields();
                generateNextBookID();
            }else {
                new Alert(Alert.AlertType.ERROR,"Error!!!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void cmbBranchOnAction(ActionEvent event) {
        String branchID = cmbBranchId.getValue();

        try {
            LibraryBranchDTO libraryBranchDTO = libraryBranchBO.searchLibraryBranch(branchID);
            if (libraryBranchDTO!= null){
                lblBranchName.setText(String.valueOf(libraryBranchDTO.getBranchName()));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String bookID  = txtBookId.getText();

        try {
            BookDTO dto = bookBO.searchBook(bookID);

            if (dto!= null){
                txtBookId.setText(dto.getBookID());
                txtBookName.setText(dto.getBookName());
                txtAuthorName.setText(dto.getAuthorName());
                txtGenre.setText(dto.getBookGenre());
                txtQty.setText(String.valueOf(dto.getQtyOnHand()));
                txtAvailability.setText(dto.getAvailability());
                cmbBranchId.setValue(dto.getBranchID());
            }else{
                new Alert(Alert.AlertType.ERROR,"Book not found!!!").show();

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
*/

