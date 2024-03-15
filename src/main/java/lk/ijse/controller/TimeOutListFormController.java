package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.bo.BOFactory;
import lk.ijse.bo.custom.QueryBO;
import lk.ijse.dto.TimeOutDTO;
import lk.ijse.dto.tm.TimeOutTM;

import java.util.List;

public class TimeOutListFormController {

    @FXML
    private TableColumn<?, ?> colBookId;

    @FXML
    private TableColumn<?, ?> colBorrowDate;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colReturnDate;

    @FXML
    private TableColumn<?, ?> colUser;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<TimeOutTM> tblTimeOut;

    QueryBO queryBO = (QueryBO) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.QUERY);

    public void initialize() {
        setCellValueFactory();
        setTimeOutDetails();
    }

    private void setTimeOutDetails() {
        List<TimeOutDTO> dtoList = null;
        try {
            dtoList = queryBO.setAllTimeOut();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ObservableList<TimeOutTM> tm = FXCollections.observableArrayList();

        for(TimeOutDTO dto :dtoList){
            tm.add(new TimeOutTM(
                    dto.getId(),
                    dto.getName(),
                    dto.getBookId(),
                    dto.getBorrowDate(),
                    dto.getReturnDate()
            ));
        }
        tblTimeOut.setItems(tm);
    }

    private void setCellValueFactory() {
        colUser.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colBookId.setCellValueFactory(new PropertyValueFactory<>("bookId"));
        colBorrowDate.setCellValueFactory(new PropertyValueFactory<>("borrowDate"));
        colReturnDate.setCellValueFactory(new PropertyValueFactory<>("returnDate"));

    }
}
