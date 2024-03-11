package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class TransactionFromController {

    @FXML
    private JFXButton btnAddReservation;

    @FXML
    private JFXComboBox<?> cmbBookId;

    @FXML
    private JFXComboBox<?> cmbUserId;

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
    private Label lblBookName1;

    @FXML
    private Label lblMemberName;

    @FXML
    private AnchorPane miniRoot;

    @FXML
    private TableView<?> tblTransaction;

    @FXML
    private TextField txtBorrowedDate;

    @FXML
    private DatePicker txtDueDate;

    @FXML
    private TextField txtReturnDate;

    @FXML
    private TextField txtTransactionId;

    @FXML
    void btnAddReservationOnAction(ActionEvent event) {

    }

    @FXML
    void cmbBookOnAction(ActionEvent event) {

    }

    @FXML
    void cmbUserOnAction(ActionEvent event) {

    }

    @FXML
    void txtTransactionIdOnAction(ActionEvent event) {

    }

}
