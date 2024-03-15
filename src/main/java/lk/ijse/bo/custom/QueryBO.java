package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.TransactionDTO;

import java.util.List;

public interface QueryBO  extends SuperBO {
    List<TransactionDTO> getTransactions(String user) throws Exception;

    //List<TimeOutDto> setAllTimeOut()throws Exception;
}
