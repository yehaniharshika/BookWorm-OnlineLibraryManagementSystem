
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
import lk.ijse.bo.custom.BookBO;
import lk.ijse.bo.custom.LibraryBranchBO;
import lk.ijse.bo.custom.impl.BookBOImpl;
import lk.ijse.bo.custom.impl.LibraryBranchBOImpl;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.LibraryBranchDTO;
import lk.ijse.dto.tm.BookTM;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    private TableColumn<?, ?> colAuthorName;

    @FXML
    private TableColumn<?, ?> colAvailabilityStatus;

    @FXML
    private TableColumn<?, ?> colBookGenre;

    @FXML
    private TableColumn<?, ?> colBookId;

    @FXML
    private TableColumn<?, ?> colBookName;

    @FXML
    private TableColumn<?, ?> colBranchId;
    @FXML
    private TableView<BookTM> tblBookDetails;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;


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

    BookBO bookBO = (BookBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.BOOK);
    LibraryBranchBO libraryBranchBO = (LibraryBranchBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.LIBRARY_BRANCH);


    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
        generateNextBookID();
    }

    public void  initialize(){
        loadAllBranchIDs();
        setAvailabilityStatus();
        generateNextBookID();
        loadAllBooks();
        setCellValueFactory();

        txtQty.textProperty().addListener((observable, oldValue, newValue) -> {
            setAvailabilityStatus();
        });


    }

    private void setCellValueFactory() {
        colBookId.setCellValueFactory(new PropertyValueFactory<>("bookID"));
        colBookName.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        colAuthorName.setCellValueFactory(new PropertyValueFactory<>("authorName"));
        colBookGenre.setCellValueFactory(new PropertyValueFactory<>("bookGenre"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colAvailabilityStatus.setCellValueFactory(new PropertyValueFactory<>("availabilityStatus"));
        colBranchId.setCellValueFactory(new PropertyValueFactory<>("branchID"));
    }

    private void loadAllBooks() {
        ObservableList<BookTM> obList = FXCollections.observableArrayList();

        try {
            List<BookDTO> bookList = bookBO.getAllBooks();

            for (BookDTO dto : bookList){
                obList.add(new BookTM(
                        dto.getBookID(),
                        dto.getBookName(),
                        dto.getAuthorName(),
                        dto.getBookGenre(),
                        dto.getQtyOnHand(),
                        dto.getAvailability(),
                        dto.getBranchID()

                ));
            }
            tblBookDetails.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
                loadAllBooks();
                setCellValueFactory();
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
                loadAllBooks();
                setCellValueFactory();
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


