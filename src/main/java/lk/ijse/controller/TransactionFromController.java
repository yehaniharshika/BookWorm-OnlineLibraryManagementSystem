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
import lk.ijse.bo.custom.impl.PlaceTransactionBoImpl;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.PlaceTransactionDTO;
import lk.ijse.dto.UserSignupDTO;
import lk.ijse.dto.tm.TransactionCartTM;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class TransactionFromController {

    @FXML
    private JFXButton btnAddReservation;

    @FXML
    private JFXComboBox<String> cmbBookId;

    @FXML
    private JFXComboBox<String> cmbUserId;

    @FXML
    private JFXButton btnBookReturn;

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
    private TableColumn<?, ?> colTransactionId;

    @FXML
    private Label lblBookName;

    @FXML
    private Label lblMemberName;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private AnchorPane miniRoot;

    @FXML
    private TableView<TransactionCartTM> tblTransaction;

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

    public void initialize() throws SQLException {
        loadAllUserIDs();
        loadAllBookIDs();
        setDate();
        generateNextTransactionID();
        loadAllTransactions();
        setCellValueFactory();

    }

    private void setCellValueFactory() {
        colTransactionId.setCellValueFactory(new PropertyValueFactory<>("transactionID"));
        colBorrowedDate.setCellValueFactory(new PropertyValueFactory<>("borrowedDate"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        colBookReturnDate.setCellValueFactory(new PropertyValueFactory<>("bookReturnDate"));
        colMemberId.setCellValueFactory(new PropertyValueFactory<>("userID"));
        colBookID.setCellValueFactory(new PropertyValueFactory<>("bookID"));
    }

    private void loadAllTransactions() {
        ObservableList<TransactionCartTM> obList = FXCollections.observableArrayList();

        try {
            List<PlaceTransactionDTO> allTransactions = placeTransactionBo.getAllTransactionDetails();

            for (PlaceTransactionDTO dto : allTransactions){
                obList.add(new TransactionCartTM(
                        dto.getTransactionID(),
                        dto.getBorrowedDate(),
                        dto.getDueDate(),
                        dto.getBookReturnDate(),
                        dto.getQty(),
                        dto.getUserID(),
                        dto.getBookID()
                ));
            }
            tblTransaction.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void generateNextTransactionID() {
        try {
            String transactionID = placeTransactionBo.generateNextTransactionId();
            txtTransactionId.setText(transactionID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
                loadAllTransactions();
                setCellValueFactory();
                clearFields();
                setDate();
                generateNextTransactionID();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void clearFields() {
        txtTransactionId.setText("");
        txtBorrowedDate.setText("");
        txtReturnDate.setText("");
        txtQty.setText("");
        cmbUserId.setValue("");
        cmbBookId.setValue("");
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

    private void setDate() {
        txtBorrowedDate.setText(String.valueOf(LocalDate.now()));
    }

    @FXML
    void cmbUserOnAction(ActionEvent event) {
        String userID = cmbUserId.getValue();

        try {
            UserSignupDTO dto = placeTransactionBo.searchUser(userID);

            if (dto != null){
                lblMemberName.setText(dto.getFirstName()+" "+dto.getLastName());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void txtQty_OnAction(ActionEvent event) {

    }

    @FXML
    void txtTransactionIdOnAction(ActionEvent event) {
        String transactionID = txtTransactionId.getText();

        try {
            PlaceTransactionDTO dto = placeTransactionBo.searchTransactions(transactionID);

            if (dto != null){
                txtTransactionId.setText(dto.getTransactionID());
                txtBorrowedDate.setText(dto.getBorrowedDate());
                txtDueDate.setValue(LocalDate.parse(dto.getDueDate()));
                txtReturnDate.setText(dto.getBookReturnDate());
                txtQty.setText(String.valueOf(dto.getQty()));
                cmbUserId.setValue(dto.getUserID());
                cmbBookId.setValue(dto.getBookID());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void btnBookReturnOnAction(ActionEvent event) {
        String transactionID = txtTransactionId.getText();
        String borrowedDate = txtBorrowedDate.getText();
        String dueDate = String.valueOf(txtDueDate.getValue());
        String bookReturnDate = txtReturnDate.getText();
        int qty = Integer.parseInt(txtQty.getText());
        String userID = cmbUserId.getValue();
        String bookID = cmbBookId.getValue();

        var dto = new PlaceTransactionDTO(transactionID,borrowedDate,dueDate,bookReturnDate,qty,userID,bookID);

        try{
            boolean isUpdated = placeTransactionBo.updateTransactionDetail(dto);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"Book return Successfully").show();
                placeTransactionBo.updateQtyBooks(bookID,qty);
                loadAllTransactions();
                setCellValueFactory();
                clearFields();
                setDate();
                generateNextTransactionID();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

