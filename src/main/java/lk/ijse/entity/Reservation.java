package lk.ijse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Reservation {
    @Id
    private String reservationID;
    private String BorrowDate;
    private String userID;

    @ManyToOne
    private User user;

    public Reservation(String borrowDate, String reservationID, User user, String userID) {
        BorrowDate = borrowDate;
        this.reservationID = reservationID;
        this.user = user;
        this.userID = userID;
    }


    public Reservation() {

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

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
