package lk.ijse.bo.custom;

import lk.ijse.dto.BookDTO;

import java.sql.SQLException;
import java.util.List;

public interface BookBO {
    boolean saveBook(BookDTO dto) throws SQLException;
    boolean updateBook(BookDTO dto) throws SQLException;
    boolean deleteBook(String bookID) throws SQLException;
    BookDTO searchBook(String bookID) throws  SQLException;
    List<BookDTO> getAllBooks() throws SQLException;
}