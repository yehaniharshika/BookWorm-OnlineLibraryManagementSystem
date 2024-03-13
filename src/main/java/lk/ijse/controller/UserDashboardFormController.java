package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class UserDashboardFormController {

    @FXML
    private AnchorPane MainRoot;

    @FXML
    private AnchorPane Root;

    @FXML
    private JFXButton btnBookList;

    @FXML
    private JFXButton btnLibraryBranch;

    @FXML
    private JFXButton btnYourTransaction;

    @FXML
    private Label lblAuthorCount;

    @FXML
    private Label lblBookCount;

    @FXML
    private Label lblBorrowCount;

    @FXML
    private Label lblMemberCount;

    @FXML
    void btnLibraryBranchOnAction(ActionEvent event) {

    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/MainWindow_From.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) Root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("BookWorm");
        stage.centerOnScreen();

    }

    @FXML
    void btnViewBookListOnAction(ActionEvent event) throws IOException {
        Parent node = FXMLLoader.load(this.getClass().getResource("/view/BookList_Form.fxml"));

        this.Root.getChildren().clear();
        this.Root.getChildren().add(node);

    }

    @FXML
    void btnYourTransactionOnAction(ActionEvent event) {

    }

}
