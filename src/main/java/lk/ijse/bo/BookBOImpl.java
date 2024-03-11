package lk.ijse.bo;

import lk.ijse.dao.BookDAOImpl;
import lk.ijse.dto.BookDTO;
import lk.ijse.entity.Book;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookBOImpl implements BookBO{

    public BookDAOImpl bookDAO = new BookDAOImpl();
    @Override
    public boolean saveBook(BookDTO dto) throws SQLException {
        return bookDAO.save(new Book(
                dto.getBookID(),
                dto.getBookName(),
                dto.getAuthorName(),
                dto.getBookGenre(),
                dto.getQty()
        ));
    }

    @Override
    public boolean updateBook(BookDTO dto) throws SQLException {
        return bookDAO.update(new Book(
                dto.getBookID(),
                dto.getBookName(),
                dto.getAuthorName(),
                dto.getBookGenre(),
                dto.getQty()
        ));
    }

    @Override
    public boolean deleteBook(String bookID) throws SQLException {
        return bookDAO.delete(bookID);
    }

    @Override
    public BookDTO searchBook(String bookID) throws SQLException {
        Book book = bookDAO.search(bookID);

        if (book != null){
            return new BookDTO(book);
        }

        return null;
    }

    @Override
    public List<BookDTO> getAllBooks() throws SQLException {
        List<Book> bookList = bookDAO.getAll();
        ArrayList<BookDTO> bookDTOS = new ArrayList<>();

        for (Book book : bookList){
            bookDTOS.add(new BookDTO(
                    book.getBookID(),
                    book.getBookName(),
                    book.getAuthorName(),
                    book.getBookGenre(),
                    book.getQty()
            ));
        }
        return bookDTOS;
    }
}
