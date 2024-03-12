package lk.ijse.dao.custom;

import lk.ijse.dto.PlaceTransactionDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface TransactionDAO {
    boolean saveTransactionDetail(PlaceTransactionDTO placeTransactions) throws SQLException;
}
