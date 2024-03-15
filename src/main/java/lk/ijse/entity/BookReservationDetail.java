package lk.ijse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class BookReservationDetail {
    @Id
    private  String reservationDetailID;
    private String borrowDate;
    private String dueDate;
    private String bookReturnDate;

    @ManyToOne
    @JoinColumn(name = "bookID")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "reservationID")
    private Reservation reservation;

    public BookReservationDetail(String reservationDetailID,String borrowDate,String dueDate,String bookReturnDate,Book book,Reservation reservation) {
        this.reservationDetailID = reservationDetailID;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.bookReturnDate = bookReturnDate;
        this.book = book;
        this.reservation = reservation;
    }



    public BookReservationDetail() {

    }

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
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public String getReservationDetailID() {
        return reservationDetailID;
    }

    public void setReservationDetailID(String reservationDetailID) {
        this.reservationDetailID = reservationDetailID;
    }
}
