package lk.ijse.bo.custom;

import lk.ijse.dto.BookDTO;
import lk.ijse.dto.PlaceTransactionDTO;
import lk.ijse.dto.UserSignupDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface PlaceTransactionBo {
    //boolean placeTransaction(List<PlaceTransactionDTO> placeTransactions) throws SQLException;
    boolean saveTransactionDetail(PlaceTransactionDTO dto ) throws SQLException;
    String generateNextTransactionId() throws SQLException;
    //BookDTO findBook(String bookID) throws SQLException, ClassNotFoundException;
    List<UserSignupDTO> getAllUsers() throws SQLException;
    List<BookDTO> getAllBooks() throws SQLException;
    boolean updateQtyBooks(String bookID,int qtyOnHand) throws SQLException;
    UserSignupDTO searchUser(String userID) throws SQLException;
    BookDTO searchBook(String bookID) throws SQLException;
}
