package lk.ijse.dao.custom;

import lk.ijse.dao.crudDAO;
import lk.ijse.entity.Book;

import java.sql.SQLException;

public interface BookDAO extends crudDAO<Book> {
    boolean updateQtyBooks(String bookID,int qtyOnHand) throws SQLException;
}
