package lk.ijse.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Reservation {
    @Id
    private String reservationID;
    private String BorrowDate;
    private String dueDate;
    private String bookReturnDate;

    @ManyToOne
    @JoinColumn(name = "userID", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "bookID", insertable = false, updatable = false)
    private Book book;

    public Reservation(Book book, String bookReturnDatel, String borrowDate, String dueDate, String reservationID, User user) {
        this.book = book;
        this.bookReturnDate = bookReturnDatel;
        BorrowDate = borrowDate;
        this.dueDate = dueDate;
        this.reservationID = reservationID;
        this.user = user;
    }

    public Reservation() {

    }


   /* public Reservation(List<Book> books, String borrowDate, String reservationID, User user, String userID) {
        this.books = books;
        BorrowDate = borrowDate;
        this.reservationID = reservationID;
        this.user = user;
        this.userID = userID;
    }*/

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getBookReturnDate() {
        return bookReturnDate;
    }

    public void setBookReturnDate(String bookReturnDate) {
        this.bookReturnDate = bookReturnDate;
    }

    public String getBorrowDate() {
        return BorrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        BorrowDate = borrowDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getReservationID() {
        return reservationID;
    }

    public void setReservationID(String reservationID) {
        this.reservationID = reservationID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
