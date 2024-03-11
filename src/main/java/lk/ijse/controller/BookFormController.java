package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BookBOImpl;
import lk.ijse.dto.BookDTO;

import java.sql.SQLException;

public class BookFormController {

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

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

    public void initialize(){
        setAvailability();
    }

    private void setAvailability() {

        if(txtQty == null){
            txtAvailability.setText("Non Availability");
        }else{
            txtAvailability.setText("Availability");
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtBookId.setText("");
        txtBookName.setText("");
        txtAuthorName.setText("");
        txtGenre.setText("");
        txtQty.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String bookID = txtBookId.getText();

        try {
            boolean isDeleted = bookBO.deleteBook(bookID);

            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION,"deleted !!!").show();
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
        int qty = Integer.parseInt(txtQty.getText());

        var dto = new BookDTO(bookID,bookName,authorName,bookGenre,qty);

        try {
            boolean isSaved = bookBO.saveBook(dto);
            if (isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Success!!!").show();
                clearFields();
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
        int qty = Integer.parseInt(txtQty.getText());

        var dto = new BookDTO(bookID,bookName,authorName,bookGenre,qty);

        try {
            boolean isUpdated= bookBO.updateBook(dto);
            if (isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"updated!!!").show();
                clearFields();
            }else {
                new Alert(Alert.AlertType.ERROR,"Error!!!").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void txtBookIDOnAction(ActionEvent event) {
        String bookID  = txtBookId.getText();

        try {
            BookDTO dto = bookBO.searchBook(bookID);

            if (dto!= null){
                txtBookId.setText(dto.getBookID());
                txtBookName.setText(dto.getBookName());
                txtAuthorName.setText(dto.getAuthorName());
                txtGenre.setText(dto.getBookGenre());
                txtQty.setText(String.valueOf(dto.getQty()));
            }else{
                new Alert(Alert.AlertType.ERROR,"Book not found!!!").show();

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}

