package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.BookReservationDetailDAO;
import lk.ijse.dto.BookReservationDetailsDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class BookReservationDetailDAOImpl{
   /* @Override
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
    public BookReservationDetailsDTO search(String reservationID) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM BookReservationDetails WHERE reservationID = ?", reservationID);
        BookReservationDetailsDTO entity = null;

        if (resultSet.next()) {
            // Use getDate() to retrieve LocalDate
            LocalDate borrowDate = resultSet.getDate("borrowDate").toLocalDate();

            entity = new BookReservationDetailsDTO(
                    resultSet.getString("reservationID"),
                    resultSet.getString("bookID"),
                    borrowDate, // Use LocalDate instead of String
                    resultSet.getString("dueDate"),
                    resultSet.getString("bookReturnDate")
            );
        }
        return entity;
    }


    @Override
    public ArrayList<BookReservationDetailsDTO> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean save(String reservationID, BookReservationDetailsDTO bookReservationsDetails) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO bookReservationDetails (reservationID, bookID, borrowDate, dueDate, bookReturnDate) VALUES (?,?,?,?,?)",
                reservationID,
                bookReservationsDetails.getBookID(),
                bookReservationsDetails.getBorrowedDate(),
                bookReservationsDetails.getDueDate(),
                bookReservationsDetails.getBookReturnDate()
        );
    }

    public boolean update(String reservationID, BookReservationDetailsDTO bookReservationsDetails) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE bookReservationDetails SET bookID=?,borrowDate=?,dueDate=?, bookReturnDate=? WHERE reservationID=?",
                bookReservationsDetails.getBookID(),
                bookReservationsDetails.getBorrowedDate(),
                bookReservationsDetails.getDueDate(),
                bookReservationsDetails.getBookReturnDate(),
                reservationID
        );

    }

*/
}
