package lk.ijse.controller;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowFromController  implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private ImageView MainWindowImg;

    @FXML
    private ImageView imgAdmin;

    @FXML
    private ImageView imgUser;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblMenu;

    @FXML
    private void navigate(MouseEvent event) throws IOException {
        if (event.getSource() instanceof ImageView) {
            ImageView imageView = (ImageView) event.getSource();

            Parent root = null;

            switch (imageView.getId()) {
                case "imgAdmin":
                    root = FXMLLoader.load(this.getClass().getResource("/view/Dashboard_Form.fxml"));
                    break;
                case "imgUser":
                    root = FXMLLoader.load(this.getClass().getResource("/view/UserLogin_Form.fxml"));
                    break;

            }

            if (root != null) {
                Scene subScene = new Scene(root);
                Stage primaryStage = (Stage) this.root.getScene().getWindow();
                primaryStage.setScene(subScene);
                primaryStage.centerOnScreen();

                TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
                tt.setFromX(-subScene.getWidth());
                tt.setToX(0);
                tt.play();

            }
        }
    }

    @FXML
    void playMouseEnterAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView){
            ImageView imageView = (ImageView) event.getSource();

            switch (imageView.getId()){
                case "imgAdmin":
                    lblMenu.setText("Admin");
                    lblDescription.setText("Click To Enter the Online Library");
                    break;

                case "imgUser":{
                    lblMenu.setText("User");
                    lblDescription.setText("Click to register the Online Library ");
                    break;
                }
            }
        }
    }

    @FXML
    void playMouseExitAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView){
            ImageView imageView = (ImageView) event.getSource();
            ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200),imageView);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);
            scaleTransition.play();

            imageView.setEffect(null);
            lblMenu.setText("Welcome To Online Library !!!");
            lblDescription.setText("Please select one of above main operations to proceed");

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(2000), root);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();
    }
}

