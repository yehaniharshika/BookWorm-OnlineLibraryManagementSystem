package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.custom.impl.PlaceTransactionBoImpl;
import lk.ijse.bo.custom.impl.UserLoginBoImpl;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.PlaceTransactionDTO;
import lk.ijse.dto.UserSignupDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionFromController {

    @FXML
    private JFXButton btnAddReservation;

    @FXML
    private JFXComboBox<String> cmbBookId;

    @FXML
    private JFXComboBox<String> cmbUserId;

    @FXML
    private TableColumn<?, ?> colBookID;

    @FXML
    private TableColumn<?, ?> colBookReturnDate;

    @FXML
    private TableColumn<?, ?> colBorrowedDate;

    @FXML
    private TableColumn<?, ?> colDueDate;

    @FXML
    private TableColumn<?, ?> colMemberId;

    @FXML
    private TableColumn<?, ?> colReservationId;

    @FXML
    private Label lblBookName;

    @FXML
    private Label lblMemberName;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private AnchorPane miniRoot;

    @FXML
    private TableView<?> tblTransaction;

    @FXML
    private TextField txtBorrowedDate;

    @FXML
    private DatePicker txtDueDate;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtReturnDate;

    @FXML
    private TextField txtTransactionId;

    public PlaceTransactionBoImpl placeTransactionBo = new PlaceTransactionBoImpl();

    public void initialize(){
        loadAllUserIDs();
        loadAllBookIDs();
    }

    private void loadAllBookIDs() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<BookDTO> idlist = placeTransactionBo.getAllBooks();


            for (BookDTO dto : idlist){
                obList.add(dto.getBookID());
            }
            cmbBookId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllUserIDs() {
        ObservableList<String>  obList = FXCollections.observableArrayList();

        try {
            List<UserSignupDTO> userIDList = placeTransactionBo.getAllUsers();

            for (UserSignupDTO  dto : userIDList){
                obList.add(dto.getUserID());
            }
            cmbUserId.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void btnAddReservationOnAction(ActionEvent event) {
        String transactionID = txtTransactionId.getText();
        String borrowedDate = txtBorrowedDate.getText();
        String dueDate = String.valueOf(txtDueDate.getValue());
        String bookReturnDate = txtReturnDate.getText();
        int qty = Integer.parseInt(txtQty.getText());
        String userID = cmbUserId.getValue();
        String bookID = cmbBookId.getValue();

        var dto = new PlaceTransactionDTO(transactionID,borrowedDate,dueDate,bookReturnDate,qty,userID,bookID);

        try{
            boolean isSaved = placeTransactionBo.saveTransactionDetail(dto);
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Adding transaction successfully!!!").show();
                placeTransactionBo.updateQtyBooks(bookID,qty);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void cmbBookOnAction(ActionEvent event) {
        String bookID = cmbBookId.getValue();

        try {
            BookDTO dto = placeTransactionBo.searchBook(bookID);

            if (dto != null){
                lblBookName.setText(dto.getBookName());
                lblQtyOnHand.setText(String.valueOf(dto.getQtyOnHand()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void cmbUserOnAction(ActionEvent event) {

    }

    @FXML
    void txtQty_OnAction(ActionEvent event) {

    }

    @FXML
    void txtTransactionIdOnAction(ActionEvent event) {

    }

}

