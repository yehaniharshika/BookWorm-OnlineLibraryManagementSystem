package lk.ijse.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Book {
    @Id
    private String bookID;
    private String bookName;
    private String authorName;
    private String bookGenre;
    private int qtyOnHand;
    private String availability;

    @ManyToOne
    @JoinColumn(name = "branchID")
    private LibraryBranch libraryBranch;

    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL)
    private List<BookReservationDetail> bookReservationDetails;


    /*public Book(String authorName, String availability, String bookGenre, String bookID, String bookName, String branchID, LibraryBranch libraryBranch, int qtyOnHand) {
        this.authorName = authorName;
        this.availability = availability;
        this.bookGenre = bookGenre;
        this.bookID = bookID;
        this.bookName = bookName;
        this.branchID = branchID;
        this.libraryBranch = libraryBranch;
        this.qtyOnHand = qtyOnHand;
    }*/




    public Book(String bookID, String bookName, String authorName, String bookGenre, int qtyOnHand, String availability, LibraryBranch libraryBranch) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.authorName = authorName;
        this.bookGenre = bookGenre;
        this.qtyOnHand = qtyOnHand;
        this.availability = availability;
        this.libraryBranch = libraryBranch;
    }


    public Book() {

    }

   /* public Book(String bookID, String bookName, String authorName, String bookGenre, String availability, int qtyOnHand, LibraryBranch branchID) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.authorName = authorName;
        this.bookGenre = bookGenre;
        this.availability = availability;
        this.qtyOnHand = qtyOnHand;
        this.libraryBranch = branchID;
    }*/

    public List<BookReservationDetail> getBookReservationDetails() {
        return bookReservationDetails;
    }

    public void setBookReservationDetails(List<BookReservationDetail> bookReservationDetails) {
        this.bookReservationDetails = bookReservationDetails;
    }

    public LibraryBranch getLibraryBranch() {
        return libraryBranch;
    }

    public void setLibraryBranch(LibraryBranch libraryBranch) {
        this.libraryBranch = libraryBranch;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getBookGenre() {
        return bookGenre;
    }

    public void setBookGenre(String bookGenre) {
        this.bookGenre = bookGenre;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }
}
