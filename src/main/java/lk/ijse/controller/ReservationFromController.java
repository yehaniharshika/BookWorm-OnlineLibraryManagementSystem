
package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.custom.impl.PlaceReservationBoImpl;
import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.BookReservationDetailsDTO;
import lk.ijse.dto.UserSignupDTO;
import lk.ijse.dto.tm.TransactionCartTM;
import lk.ijse.entity.Reservation;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colBookBorrowDate;

    @FXML
    private TableColumn<?, ?> colBookName;

    @FXML
    private TableColumn<?, ?> colBookReturnDate;

    @FXML
    private TableColumn<?, ?> colDueDate;

    @FXML
    private TableColumn<?, ?> colbookID;

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
    private TableView<TransactionCartTM> tblTransaction;

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

    ObservableList<TransactionCartTM> obList = FXCollections.observableArrayList();

    public PlaceReservationBoImpl placeReservationBo = new PlaceReservationBoImpl();

    public void initialize(){
        loadAllBookID();
        loadAllUserID();
        setDate();
        generateNextReservationID();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colbookID.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        colBookName.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        colBookBorrowDate.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        colBookReturnDate.setCellValueFactory(new PropertyValueFactory<>("bookReturnDate"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    private void loadAllUserID() {
        ObservableList<String> observableListList = FXCollections.observableArrayList();

        try {
            List<UserSignupDTO> userList = placeReservationBo.getAllUsers();

            for (UserSignupDTO dto : userList){
                observableListList.add(dto.getUserID());
            }
            cmbUserId.setItems(observableListList);
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

    public void generateNextReservationID() {
        try {
            String reservationID = placeReservationBo.generateNextReservationId();
            txtReservationID.setText(reservationID);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void btnAddReservationOnAction(ActionEvent event) {
        String bookID = cmbBookId.getValue();
        String bookName = lblBookName.getText();
        LocalDate borrowedDate = LocalDate.parse(txtBorrowedDate.getText());
        String dueDate = String.valueOf(txtDueDate.getValue());
        String bookReturnDate = txtReturnDate.getText();
        Button btn = new Button("Remove");

        setRemoveBtnAction(btn);
        btn.setCursor(Cursor.HAND);
        btn.setStyle("-fx-background-color:#cf6a87; -fx-border-color: black; -fx-background-radius: 15; -fx-border-radius: 15; -fx-font-weight: bold");
        btn.setMinWidth(112);
        btn.setMinHeight(30);
        if (!obList.isEmpty()){
            for (int i=0 ; i<tblTransaction.getItems().size() ; i++){
                if (colbookID.getCellData(i).equals(bookID)){


               /* int colsupplierQuantity = (int) colQty.getCellData(i);
                    qty += colsupplierQuantity;

                   *//* obList.get(i).;*//*

                    calculateTotal();
*/
                    tblTransaction.refresh();
                    return;
                }
            }

        }
        var TransactionCartTM = new TransactionCartTM(bookID,bookName,borrowedDate,dueDate,bookReturnDate,btn);
        obList.add(TransactionCartTM);
        /*tblTransaction.setItems(obList);*/

            /* calculateTotal();
                txtSupplyQuantity.clear();*/


    }

    private void setRemoveBtnAction(Button btn) {
        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                int focusedIndex = tblTransaction.getSelectionModel().getSelectedIndex();

                obList.remove(focusedIndex);
                tblTransaction.refresh();

                /*calculateTotal();*/


            }
        });
    }

    @FXML
    void btnBookReturnOnAction(ActionEvent event) {

    }

    @FXML
    void btnPlaceReservationOnAction(ActionEvent event) {

        Session session  = null;
        Transaction transaction = null;

        try {
            String userID = cmbUserId.getValue();
            String bookID = cmbBookId.getValue();
            String reservationID = txtReservationID.getText();
            String borrowedDate = txtBorrowedDate.getText();
            String dueDate = String.valueOf(txtDueDate.getValue());
            String bookReturnDate = txtReturnDate.getText();

            session = FactoryConfiguration.getInstance().getSession();
            transaction = session.beginTransaction();

            Reservation reservation = new Reservation();
            /*reservation.setUser(userID);*/

            reservation.setReservationID(txtReservationID.getText());
            reservation.setBorrowDate(borrowedDate);

            session.save(reservation);

            /*int i=0;
            for (TransactionCartTM transactionCartTM : obList){
                session.save(new )
            }*/

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        /*String reservationID = txtReservationID.getText();
        boolean b = saveOrder(reservationID, LocalDate.now(), cmbUserId.getValue(),
                tblTransaction.getItems().stream().map(tm -> new BookReservationDetailsDTO(tm.getBookID(),
                        tm.getBookName(), tm.getBorrowDate(), tm.getDueDate(), tm.getBookReturnDate())).collect(Collectors.toList()));

        if (b) {
            new Alert(Alert.AlertType.INFORMATION, "Order has been placed successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Order has not been placed successfully").show();
        }

        generateNextReservationID();
        lblId.setText("Order Id: " + txtReservationID.getText());
        cmbUserId.getSelectionModel().clearSelection();
        cmbBookId.getSelectionModel().clearSelection();
        tblTransaction.getItems().clear();
        txtQty.clear();

        *//*calculateTotal();*/

    }

    public boolean saveOrder(String reservationID, LocalDate borrowDate, String userID, List<BookReservationDetailsDTO> bookReservationDetails) {
       /* try {
            return placeReservationBo.placeReservation(reservationID, borrowDate, userID, bookReservationDetails);
        } catch (SQLException e) {
           e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return false;*/
        return false;
    }

    private void clearAllFields() {
        txtReservationID.setText("");
        txtBorrowedDate.setText("");
        txtDueDate.setValue(LocalDate.parse(""));
        txtReturnDate.setText("");
    }

    @FXML
    void cmbBookOnAction(ActionEvent event) {
        String bookID = cmbBookId.getValue();

        try {
            BookDTO dto = placeReservationBo.searchBook(bookID);

            if (dto != null){
                lblBookName.setText(dto.getBookName());
                lblQtyOnHand.setText(String.valueOf(dto.getQtyOnHand()));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void setDate() {
        txtBorrowedDate.setText(String.valueOf(LocalDate.now()));
    }

    @FXML
    void cmbUserOnAction(ActionEvent event) {
        String userID = cmbUserId.getValue();

       try {
            UserSignupDTO dto = placeReservationBo.searchUser(userID);

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

}

