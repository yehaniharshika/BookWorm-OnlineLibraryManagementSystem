package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.BookReservationDetailDAO;
import lk.ijse.dto.BookReservationDetailsDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class BookReservationDetailDAOImpl implements BookReservationDetailDAO {
   @Override
    public String getCount() throws SQLException {
        return "";
    }

    @Override
    public String generateNextId() throws SQLException {

        return "";
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
       /* ResultSet resultSet = SQLUtil.execute("SELECT * FROM BookReservationDetails WHERE reservationID = ?", reservationID);
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
        return entity;*/
        return null;
    }


    @Override
    public ArrayList<BookReservationDetailsDTO> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean save(String reservationID, BookReservationDetailsDTO bookReservationsDetails) throws SQLException, ClassNotFoundException {
       /* return SQLUtil.execute("INSERT INTO bookReservationDetails (reservationID, bookID, borrowDate, dueDate, bookReturnDate) VALUES (?,?,?,?,?)",
                reservationID,
                bookReservationsDetails.getBookID(),
                bookReservationsDetails.getBorrowedDate(),
                bookReservationsDetails.getDueDate(),
                bookReservationsDetails.getBookReturnDate()
        );*/

        return false;
    }

    public boolean update(String reservationID, BookReservationDetailsDTO bookReservationsDetails) throws SQLException, ClassNotFoundException {
        /*return SQLUtil.execute("UPDATE bookReservationDetails SET bookID=?,borrowDate=?,dueDate=?, bookReturnDate=? WHERE reservationID=?",
                bookReservationsDetails.getBookID(),
                bookReservationsDetails.getBorrowedDate(),
                bookReservationsDetails.getDueDate(),
                bookReservationsDetails.getBookReturnDate(),
                reservationID
        );*/

        return false;
    }


}
