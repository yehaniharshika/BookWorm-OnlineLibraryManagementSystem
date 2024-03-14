package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.PlaceReservationBo;
import lk.ijse.dao.custom.impl.BookDAOImpl;
import lk.ijse.dao.custom.impl.BookReservationDetailDAOImpl;
import lk.ijse.dao.custom.impl.ReservationDAOImpl;
import lk.ijse.dao.custom.impl.UserSignupDAOImpl;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.BookReservationDetailsDTO;
import lk.ijse.dto.ReservationDTO;
import lk.ijse.dto.UserSignupDTO;
import lk.ijse.entity.Book;
import lk.ijse.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PlaceReservationBoImpl  {

    public BookDAOImpl bookDAO = new BookDAOImpl();
    public UserSignupDAOImpl userSignupDAO = new UserSignupDAOImpl();
    public ReservationDAOImpl reservationDAO = new ReservationDAOImpl();
    public BookReservationDetailDAOImpl bookReservationDetailDAO = new BookReservationDetailDAOImpl();


    /*@Override
    public boolean placeReservation(String reservationID, LocalDate borrowDate, String userID, List<BookReservationDetailsDTO> bookReservationsDetails) throws SQLException,ClassNotFoundException {
        Connection connection = null;
        connection = DBConnection.getInstance().getConnection();
        boolean isExist = reservationDAO.exist(reservationID);

        if (isExist) {
            return false;
        }

        connection.setAutoCommit(false);

        boolean isSaved = reservationDAO.save(reservationID, borrowDate, userID);

        if (!isSaved) {
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }

        for (BookReservationDetailsDTO detail : bookReservationsDetails) {

            boolean isReservationDetailsSaved = bookReservationDetailDAO.save(reservationID, detail);

            if (!isReservationDetailsSaved) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }


            BookDTO book = searchBook(detail.getBookID());
            book.setAvailability("not available");

            boolean isUpdateQty = bookDAO.update(new Book(book.getBookID(), book.getBookName(), book.getAuthorName(), book.getBookGenre(),book.getQtyOnHand(),book.getAvailability(), book.getBranchID()));

            if (!(isUpdateQty)) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
        }

        connection.commit();
        connection.setAutoCommit(true);
        return true;
    }


    @Override
    public String generateNextReservationId() throws SQLException {
        return reservationDAO.generateNextId();
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
                    user.getEmailAddress(),
                    user.getUsername(),
                    user.getPassword()
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
                    book.getBranchID())
            );
        }
        return bookDtos;
    }

    *//*@Override
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
    }
*//*
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

    @Override
    public boolean updateBookReservationDetails(String reservationID, BookReservationDetailsDTO bookReservationsDetails) throws SQLException, ClassNotFoundException {
        return bookReservationDetailDAO.update(reservationID, bookReservationsDetails);
    }

    @Override
    public ReservationDTO searchReservation(String reservationID) throws SQLException {
        return null;
    }

*/
}
