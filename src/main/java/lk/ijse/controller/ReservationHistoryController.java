package lk.ijse.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.QueryBO;
import lk.ijse.bo.custom.UserSignUpBo;
import lk.ijse.bo.custom.impl.QueryBOImpl;
import lk.ijse.bo.custom.impl.UserSignupBOImpl;
import lk.ijse.dto.TransactionDTO;
import lk.ijse.dto.UserSignupDTO;
import lk.ijse.dto.tm.TransactionTM;

import java.sql.SQLException;
import java.util.List;

public class ReservationHistoryController {

    @FXML
    private JFXComboBox<String> cmbUserId;

    @FXML
    private TableColumn<?, ?> colBookId;

    @FXML
    private TableColumn<?, ?> colBookName;

    @FXML
    private TableColumn<?, ?> colBorrowDate;

    @FXML
    private TableColumn<?, ?> colReturnDate;

    @FXML
    private TableColumn<?, ?> colUser;

    @FXML
    private TableView<TransactionTM> tblTransction;

    UserSignUpBo userSignupBO = (UserSignUpBo) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.USER);
    QueryBO queryBO = (QueryBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.QUERY);

    public void initialize(){
        getAllUser();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
    }

    private void getAllUser() {
        ObservableList<String>  idList = FXCollections.observableArrayList();

        try {
            List<UserSignupDTO> userList  = userSignupBO.getAllUsers();

            for (UserSignupDTO dto : userList){
                idList.add(dto.getUserID());
            }
            cmbUserId.setItems(idList);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void mouseClickOnAction(MouseEvent event) {

    }

    @FXML
    void userOnAction(ActionEvent event) {
        String user = (String) cmbUserId.getValue();

        if (user==null) {
            new Alert(Alert.AlertType.ERROR, "user is Empty").show();
        }

        try{

            List<TransactionDTO> transactionDto = queryBO.getTransactions(user);

            ObservableList<TransactionTM> tm = FXCollections.observableArrayList();

            for(TransactionDTO dto : transactionDto){
                tm.add(new TransactionTM(
                        dto.getFirstName(),
                        dto.getBookID(),
                        dto.getBookName(),
                        dto.getBorrowedDate(),
                        dto.getBookReturnDate()
                ));

            }
            tblTransction.setItems(tm);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
