package lk.ijse.dao.custom;

import lk.ijse.dao.crudDAO;
import lk.ijse.entity.Book;

import java.sql.SQLException;

public interface BookDAO extends crudDAO<Book> {
    boolean setAvailability(String bookID,String availabilityStatus) throws SQLException;
}
