package lk.ijse.dao.custom;

import lk.ijse.dao.crudDAO;
import lk.ijse.dto.BookReservationDetailsDTO;

import java.sql.SQLException;

public interface BookReservationDetailDAO extends crudDAO<BookReservationDetailsDTO> {
    boolean save(String orderId, BookReservationDetailsDTO bookReservationsDetails ) throws SQLException, ClassNotFoundException;

    /*boolean saveTransaction(bookReservationDetailsDTO dto) throws SQLException;

    boolean updateTransaction(bookReservationDetailsDTO dto) throws SQLException;

    bookReservationDetailsDTO searchTransaction(String id) throws SQLException;

    ArrayList<bookReservationDetailsDTO> getAllTransactions() throws SQLException;

    String generateNextId() throws SQLException;*/

}
