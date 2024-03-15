package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.PlaceReservationBo;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.BookDAO;
import lk.ijse.dao.custom.ReservationDAO;
import lk.ijse.dao.custom.UserSignUpDAO;
import lk.ijse.dao.custom.impl.BookDAOImpl;
import lk.ijse.dao.custom.impl.ReservationDAOImpl;
import lk.ijse.dao.custom.impl.UserSignupDAOImpl;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.UserSignupDTO;
import lk.ijse.entity.Book;
import lk.ijse.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaceReservationBoImpl implements PlaceReservationBo {

    BookDAO bookDAO = (BookDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DTOTypes.BOOK);
    UserSignUpDAO userSignupDAO = (UserSignUpDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DTOTypes.USER);
    ReservationDAO reservationDAO = (ReservationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DTOTypes.RESERVATION);

    @Override
    public String generateNextReservationId() throws SQLException {
        return reservationDAO.generateNextId();
    }

    @Override
    public String generateNextBookReservationDetailId() throws SQLException {
        return  reservationDAO.generateNextBookReservationId();
    }

    @Override
    public List<UserSignupDTO> getAllUsers() throws SQLException {
        ArrayList<User> allUsersList = userSignupDAO.getAll();
        ArrayList<UserSignupDTO> userSignupDTOS = new ArrayList<>();

        for (User user : allUsersList){
            userSignupDTOS.add(new UserSignupDTO(
                    user.getUserID(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getNic(),
                    user.getEmailAddress()

            ));
        }
        return userSignupDTOS;
    }

    @Override
    public List<BookDTO> getAllBooks() throws SQLException {
        ArrayList<Book> allBooks = bookDAO.getAll();
        ArrayList<BookDTO> bookDtos = new ArrayList<>();

        for (Book book : allBooks){
            bookDtos.add(new BookDTO(
                    book.getBookID(),
                    book.getBookName(),
                    book.getAuthorName(),
                    book.getBookGenre(),
                    book.getQtyOnHand(),
                    book.getAvailability(),
                    book.getLibraryBranch())
            );
        }
        return bookDtos;
    }

    /*@Override
    public boolean updateTransactionDetail(BookReservationDetailsDTO dto) throws SQLException {
        return transactionDAO.updateTransaction(new BookReservationDetailsDTO(
                dto.getTransactionID(),
                dto.getBorrowedDate(),
                dto.getDueDate(),
                dto.getBookReturnDate(),
                dto.getQty(),
                dto.getUserID(),
                dto.getBookID()
        )) ;
    }*/

    @Override
    public UserSignupDTO searchUser(String userID) throws SQLException {
        User user = userSignupDAO.search(userID);
        if (user != null){
            return new UserSignupDTO(user);
        }
        return null;
    }

   @Override
    public BookDTO searchBook(String bookID) throws SQLException {
        Book book = bookDAO.search(bookID);

        if (book != null){
            return new BookDTO(book);
        }
        return null;
    }
/*
    @Override
    public boolean saveReservation(ReservationDTO dto) throws SQLException {
        return reservationDAO.save(new Book(
                dto.getReservationID(),
                dto.getBorrowDate(),
                dto.getDueDate(),
                dto.getDueDate(),
                dto.getBookReturnDate(),
                userSignupDAO.getUser(dto.getUserID()),
                bookDAO.getBook(dto.getBookID())

        ));
    }

    @Override
    public boolean updateReservation(ReservationDTO dto) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteReservation(String reservationID) throws SQLException {
        return false;
    }

    @Override
    public boolean updateBookReservationDetails(String reservationID, BookReservationDetailsDTO bookReservationsDetails) throws SQLException, ClassNotFoundException {
        return bookReservationDetailDAO.update(reservationID, bookReservationsDetails);
    }

    @Override
    public ReservationDTO searchReservation(String reservationID) throws SQLException {
        return null;
    }

    @Override
    public List<ReservationDTO> getAllReservations() throws SQLException {
        return List.of();
    }*/


}
