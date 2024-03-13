package lk.ijse.dao.custom;

import lk.ijse.dao.crudDAO;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.PlaceTransactionDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface TransactionDAO{
    boolean saveTransaction(PlaceTransactionDTO dto) throws SQLException;

    boolean updateTransaction(PlaceTransactionDTO dto) throws SQLException;

    PlaceTransactionDTO searchTransaction(String id) throws SQLException;

    ArrayList<PlaceTransactionDTO> getAllTransactions() throws SQLException;

    String generateNextId() throws SQLException;

}
