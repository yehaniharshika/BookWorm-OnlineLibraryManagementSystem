package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.BookReservationDetailDAO;
import lk.ijse.dto.BookReservationDetailsDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookReservationDetailDAOImpl implements BookReservationDetailDAO {
    @Override
    public String getCount() throws SQLException {
        return "";
    }

    @Override
    public String generateNextId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT reservationID FROM reservation ORDER BY reservationID DESC LIMIT 1;");

        if (resultSet.next()) {
            String id = resultSet.getString("code");
            int newItemId = Integer.parseInt(id.replace("R00-", "")) + 1;
            return String.format("R00-%03d", newItemId);
        } else {
            return "R00-001";
        }
    }

    @Override
    public boolean save(BookReservationDetailsDTO dto) throws SQLException {
        return false;
    }

    @Override
    public boolean update(BookReservationDetailsDTO dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public BookReservationDetailsDTO search(String id) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<BookReservationDetailsDTO> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean save(String reservationID, BookReservationDetailsDTO bookReservationsDetails) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO bookReservationDetails VALUES (?,?,?,?,?)",
                reservationID,
                bookReservationsDetails.getBookID(),
                bookReservationsDetails.getBorrowedDate(),
                bookReservationsDetails.getDueDate(),
                bookReservationsDetails.getBookReturnDate()
        );
    }

    /*@Override
    public boolean saveTransaction(BookReservationDetailsDTO dto) throws SQLException {
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
    public boolean updateTransaction(BookReservationDetailsDTO dto) throws SQLException {
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
    public BookReservationDetailsDTO searchTransaction(String transactionID) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM transaction WHERE transactionID=?",transactionID);
        BookReservationDetailsDTO dto = null;

        if (resultSet.next()){
            dto = new BookReservationDetailsDTO(
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
    public ArrayList<BookReservationDetailsDTO> getAllTransactions() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM transaction");

        ArrayList<BookReservationDetailsDTO> transactionList = new ArrayList<>();

        while (resultSet.next()){
            transactionList.add(new BookReservationDetailsDTO(
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
    }*/
}
