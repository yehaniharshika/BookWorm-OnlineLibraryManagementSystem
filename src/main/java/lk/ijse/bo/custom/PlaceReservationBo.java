package lk.ijse.bo.custom;

import lk.ijse.dto.BookDTO;
import lk.ijse.dto.BookReservationDetailsDTO;
import lk.ijse.dto.UserSignupDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface PlaceReservationBo {
    boolean placeReservation(String reservationID, LocalDate borrowDate, String userID, List<BookReservationDetailsDTO> bookReservationsDetails) throws SQLException,ClassNotFoundException;

    String generateNextReservationId() throws SQLException;

    List<UserSignupDTO> getAllUsers() throws SQLException;

    List<BookDTO> getAllBooks() throws SQLException;

    UserSignupDTO searchUser(String userID) throws SQLException;

    BookDTO searchBook(String bookID) throws SQLException;

    //BookReservationDetailsDTO searchTransactions(String transactionID) throws SQLException;
}
