package lk.ijse.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

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
    private TableView<?> tblBookDetails;

}


