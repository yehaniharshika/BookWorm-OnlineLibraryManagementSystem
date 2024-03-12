package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.TransactionDAO;
import lk.ijse.dto.PlaceTransactionDTO;

import java.sql.SQLException;

public class TransactionDAOImpl implements TransactionDAO {

    @Override
    public boolean saveTransaction(PlaceTransactionDTO dto) throws SQLException {
        return SQLUtil.execute("INSERT INTO transaction  (transactionID,borrowedDate,dueDate,bookReturnDate,qty,userID,bookID) VALUES (?,?,?,?,?,?,?)",
                dto.getTransactionID(),
                dto.getBorrowedDate(),
                dto.getDueDate(),
                dto.getBookReturnDate(),
                dto.getQty(),
                dto.getUserID(),
                dto.getBookID()
        );

    }
}
