package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class AdminDashboardFormController {

    @FXML
    private AnchorPane MainRoot;

    @FXML
    private AnchorPane Root;

    @FXML
    private Label lblAuthorCount;

    @FXML
    private Label lblBookCount;

    @FXML
    private Label lblBorrowCount;

    @FXML
    private Label lblMemberCount;

    @FXML
    void btnDashboardOnAction(ActionEvent event) {

    }

    @FXML
    void btnUserInformationOnAction(ActionEvent event) throws IOException {
        Parent node = FXMLLoader.load(this.getClass().getResource("/view/User_Form.fxml"));

        this.Root.getChildren().clear();
        this.Root.getChildren().add(node);

    }

}
