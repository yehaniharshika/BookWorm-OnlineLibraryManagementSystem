package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.BookBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.BookDAO;
import lk.ijse.dao.custom.LibraryBranchDAO;
import lk.ijse.dao.custom.impl.BookDAOImpl;
import lk.ijse.dao.custom.impl.LibraryBranchDAOImpl;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.LibraryBranchDTO;
import lk.ijse.entity.Book;
import lk.ijse.entity.LibraryBranch;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookBOImpl implements BookBO{

    BookDAO bookDAO = (BookDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DTOTypes.BOOK);
    LibraryBranchDAO libraryBranchDAO = (LibraryBranchDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DTOTypes.LIBRARY_BRANCH);

    @Override
    public boolean saveBook(BookDTO dto) throws SQLException {
        return bookDAO.save(new Book(
                dto.getBookID(),
                dto.getBookName(),
                dto.getAuthorName(),
                dto.getBookGenre(),
                dto.getQtyOnHand(),
                dto.getAvailability(),
                libraryBranchDAO.getLibraryBranch(dto.getBranchID())
        ));
    }

    @Override
    public boolean updateBook(BookDTO dto) throws SQLException {
        return bookDAO.update(new Book(
                dto.getBookID(),
                dto.getBookName(),
                dto.getAuthorName(),
                dto.getBookGenre(),
                dto.getQtyOnHand(),
                dto.getAvailability(),
                libraryBranchDAO.getLibraryBranch(dto.getBranchID())
        ));
    }

    @Override
    public boolean deleteBook(String bookID) throws SQLException {
        return bookDAO.delete(bookID);
    }

    @Override
    public BookDTO searchBook(String bookID) throws SQLException {
        Book book =bookDAO.search(bookID);


        return new BookDTO(
                book.getBookID(),
                book.getBookName(),
                book.getAuthorName(),
                book.getBookGenre(),
                book.getQtyOnHand(),
                book.getAvailability(),
                book.getLibraryBranch()

        );

    }

    @Override
    public List<BookDTO> getAllBooks() throws SQLException {
        ArrayList<Book> allBooks= bookDAO.getAll();
        ArrayList<BookDTO> bookDTOS = new ArrayList<>();


        for (Book book :allBooks) {
            bookDTOS.add(new BookDTO(
                    book.getBookID(),
                    book.getBookName(),
                    book.getAuthorName(),
                    book.getBookGenre(),
                    book.getQtyOnHand(),
                    book.getAvailability(),
                    book.getLibraryBranch()

            ));
        }
        return bookDTOS;
    }

    @Override
    public String generateNextBookId() throws SQLException {
        return bookDAO.generateNextId();
    }

    @Override
    public List<LibraryBranchDTO> getAllLibraryBranches() throws SQLException {
        ArrayList<LibraryBranch> branches = libraryBranchDAO.getAll();
        ArrayList<LibraryBranchDTO> libraryBranchDTOS = new ArrayList<>();


        for (LibraryBranch libraryBranch : branches) {
            libraryBranchDTOS.add(new LibraryBranchDTO(
                    libraryBranch.getBranchID(),
                    libraryBranch.getBranchName(),
                    libraryBranch.getLocation(),
                    libraryBranch.getAdmin()

            ));
        }
        return libraryBranchDTOS;
    }
}
