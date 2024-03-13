package lk.ijse.bo.custom;

import lk.ijse.dto.BookDTO;
import lk.ijse.dto.PlaceTransactionDTO;
import lk.ijse.dto.UserSignupDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface PlaceTransactionBo {
    boolean saveTransactionDetail(PlaceTransactionDTO dto ) throws SQLException;

    boolean updateTransactionDetail(PlaceTransactionDTO dto) throws SQLException;

    String generateNextTransactionId() throws SQLException;

    List<UserSignupDTO> getAllUsers() throws SQLException;

    List<BookDTO> getAllBooks() throws SQLException;

    List<PlaceTransactionDTO> getAllTransactionDetails() throws SQLException;

    boolean updateQtyBooks(String bookID,int qtyOnHand) throws SQLException;

    UserSignupDTO searchUser(String userID) throws SQLException;

    BookDTO searchBook(String bookID) throws SQLException;

    PlaceTransactionDTO searchTransactions(String transactionID) throws SQLException;
}
