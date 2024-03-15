package lk.ijse.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Reservation {
    @Id
    private String reservationID;
    private String BorrowDate;


    @ManyToOne
    @JoinColumn(name = "userID", insertable = false, updatable = false)
    private User user;

    @OneToMany(mappedBy = "reservation")
    private List<BookReservationDetail> bookReservationDetails;

    public Reservation(List<BookReservationDetail> bookReservationDetails, String borrowDate, String reservationID, User user) {
        this.bookReservationDetails = bookReservationDetails;
        BorrowDate = borrowDate;
        this.reservationID = reservationID;
        this.user = user;
    }

    public Reservation() {

    }

    public List<BookReservationDetail> getBookReservationDetails() {
        return bookReservationDetails;
    }

    public void setBookReservationDetails(List<BookReservationDetail> bookReservationDetails) {
        this.bookReservationDetails = bookReservationDetails;
    }

    public String getBorrowDate() {
        return BorrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        BorrowDate = borrowDate;
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
