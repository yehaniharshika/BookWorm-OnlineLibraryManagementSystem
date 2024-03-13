package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.custom.impl.BookBOImpl;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.tm.BookTM;

import java.sql.SQLException;
import java.util.List;

public class BookListFormController {

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
    private TableColumn<?, ?> colQtyOnHand;


    @FXML
    private AnchorPane root;

    @FXML
    private TableView<BookTM> tblBookDetails;

    public BookBOImpl bookBO = new BookBOImpl();

    public void  initialize(){
        loadAllBooks();
        setCellValueFactory();
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

}


