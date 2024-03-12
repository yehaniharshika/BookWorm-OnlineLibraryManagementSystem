package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.TransactionDAO;
import lk.ijse.dto.PlaceTransactionDTO;

import java.sql.SQLException;

public class TransactionDAOImpl implements TransactionDAO {

    @Override
    public boolean saveTransactionDetail(PlaceTransactionDTO placeTransactions) throws SQLException {
        return SQLUtil.execute("INSERT INTO transaction  (transactionID,userID,bookID,bookName,borrowedDate,dueDate,bookReturnDate,qty) VALUES (?,?,?,?,?,?,?,?)",
                placeTransactions.getTransactionID(),
                placeTransactions.getUserID(),
                placeTransactions.getBookID(),
                placeTransactions.getBookName(),
                placeTransactions.getBorrowedDate(),
                placeTransactions.getDueDate(),
                placeTransactions.getBookReturnDate());

    }
}
