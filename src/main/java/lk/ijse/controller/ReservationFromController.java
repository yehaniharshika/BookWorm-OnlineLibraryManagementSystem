package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.custom.impl.PlaceReservationBoImpl;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.UserSignupDTO;

import java.sql.SQLException;
import java.util.List;

public class ReservationFromController {

    @FXML
    private JFXButton btnAddReservation;

    @FXML
    private JFXButton btnBookReturn;

    @FXML
    private JFXButton btnPlaceReservation;

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
    private Label lblBookName;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblId;

    @FXML
    private Label lblMemberName;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private Label lblTotalBooksCount;

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
    private TextField txtReservationID;

    @FXML
    private TextField txtReturnDate;
    public PlaceReservationBoImpl placeReservationBo = new PlaceReservationBoImpl();

    public void initialize(){
        loadAllBookID();
        loadAllUserID();
        generateNextReservationID();
    }

    private void loadAllUserID() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<UserSignupDTO> userList = placeReservationBo.getAllUsers();

            for (UserSignupDTO dto : userList){
                obList.add(dto.getUserID());
            }
            cmbUserId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllBookID() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<BookDTO> bookList = placeReservationBo.getAllBooks();

            for (BookDTO dto : bookList){
                obList.add(dto.getBookID());
            }
            cmbBookId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateNextReservationID() {
        try {
            String reservationID = placeReservationBo.generateNextReservationId();
            txtReservationID.setText(reservationID);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new order id").show();
        }
        return "OID-001";
    }

    @FXML
    void btnAddReservationOnAction(ActionEvent event) {

    }

    @FXML
    void btnBookReturnOnAction(ActionEvent event) {

    }

    @FXML
    void btnPlaceReservationOnAction(ActionEvent event) {

    }

    @FXML
    void cmbBookOnAction(ActionEvent event) {

    }

    @FXML
    void cmbUserOnAction(ActionEvent event) {

    }

    @FXML
    void txtQty_OnAction(ActionEvent event) {

    }

}
