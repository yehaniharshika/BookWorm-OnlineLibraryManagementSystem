package lk.ijse.bo.custom.impl;

import lk.ijse.Db.DBConnection;
import lk.ijse.bo.custom.PlaceTransactionBo;
import lk.ijse.dao.custom.impl.BookDAOImpl;
import lk.ijse.dao.custom.impl.TransactionDAOImpl;
import lk.ijse.dao.custom.impl.UserSignupDAOImpl;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.PlaceTransactionDTO;
import lk.ijse.dto.UserSignupDTO;
import lk.ijse.entity.Book;
import lk.ijse.entity.User;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaceTransactionBoImpl implements PlaceTransactionBo {

    public BookDAOImpl bookDAO = new BookDAOImpl();
    public UserSignupDAOImpl userSignupDAO = new UserSignupDAOImpl();
    public TransactionDAOImpl transactionDAO = new TransactionDAOImpl();


    @Override
    public boolean placeTransaction(List<PlaceTransactionDTO> placeTransactions) throws SQLException {
        Connection connection = null;
        connection = DBConnection.getInstance().getConnection();


        for (PlaceTransactionDTO detail : placeTransactions) {

            boolean isOk = transactionDAO.saveTransactionDetail(detail);

            if (!isOk) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }


            BookDTO book= null;
            try {
                book = findBook(detail.getBookID());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            book.setQtyOnHand(book.getQtyOnHand() - detail.getQty());

                boolean isUpdateQty = bookDAO.update(new Book(book.getBookID(), book.getBookName(), book.getAuthorName(), book.getBookGenre(),book.getQtyOnHand(),book.getBranchID()));

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
    public BookDTO findBook(String bookID) throws SQLException, ClassNotFoundException {
        Book book = bookDAO.search(bookID);
        return new BookDTO(book.getBookID(), book.getBookName(), book.getAuthorName(),book.getBookGenre(),book.getQtyOnHand(),book.getBranchID());
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
                    book.getBranchID())
            );
        }
        return bookDtos;
    }

    @Override
    public String generateNextTransactionId() throws SQLException {
        return "";
    }
}
