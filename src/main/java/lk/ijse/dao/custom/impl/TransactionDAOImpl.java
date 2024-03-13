package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.TransactionDAO;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.PlaceTransactionDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TransactionDAOImpl implements TransactionDAO {

    @Override
    public boolean saveTransaction(PlaceTransactionDTO dto) throws SQLException {
        return SQLUtil.execute("INSERT INTO transaction VALUES (?,?,?,?,?,?,?)",
                dto.getTransactionID(),
                dto.getBorrowedDate(),
                dto.getDueDate(),
                dto.getBookReturnDate(),
                dto.getQty(),
                dto.getUserID(),
                dto.getBookID()
        );

    }

    @Override
    public boolean updateTransaction(PlaceTransactionDTO dto) throws SQLException {
        return SQLUtil.execute("UPDATE transaction SET borrowedDate=?,dueDate=?,bookReturnDate=?,qty=?,userID=?,bookID=? WHERE transactionID=?",
                dto.getBorrowedDate(),
                dto.getDueDate(),
                dto.getBookReturnDate(),
                dto.getQty(),
                dto.getUserID(),
                dto.getBookID(),
                dto.getTransactionID()
        );

    }

    @Override
    public PlaceTransactionDTO searchTransaction(String transactionID) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM transaction WHERE transactionID=?",transactionID);
        PlaceTransactionDTO dto = null;

        if (resultSet.next()){
            dto = new PlaceTransactionDTO(
                    resultSet.getString("transactionID"),
                    resultSet.getString("borrowedDate"),
                    resultSet.getString("dueDate"),
                    resultSet.getString("bookReturnDate"),
                    resultSet.getInt("qty"),
                    resultSet.getString("userID"),
                    resultSet.getString("bookID"));
        }
        return dto;
    }

    @Override
    public ArrayList<PlaceTransactionDTO> getAllTransactions() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM transaction");

        ArrayList<PlaceTransactionDTO> transactionList = new ArrayList<>();

        while (resultSet.next()){
            transactionList.add(new PlaceTransactionDTO(
                    resultSet.getString("transactionID"),
                    resultSet.getString("borrowedDate"),
                    resultSet.getString("dueDate"),
                    resultSet.getString("bookReturnDate"),
                    resultSet.getInt("qty"),
                    resultSet.getString("userID"),
                    resultSet.getString("bookID")
            ));
        }
        return transactionList;
    }

    @Override
    public String generateNextId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT transactionID FROM transaction ORDER BY transactionID DESC LIMIT 1");

        if (resultSet.next()){
            return splitFeeId(resultSet.getString(1));
        }
        return splitFeeId(null);
    }

    private String splitFeeId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("T0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "T00"+id;
            }else {
                if (length < 3){
                    return "T0"+id;
                }else {
                    return "T"+id;
                }
            }
        }
        return "T001";
    }
}
