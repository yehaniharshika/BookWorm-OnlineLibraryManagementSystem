package lk.ijse.dto;

import lk.ijse.entity.Book;
import lk.ijse.entity.LibraryBranch;
import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class BookDTO {
    private String bookID;
    private String bookName;
    private String authorName;
    private String bookGenre;
    private int qtyOnHand;
    private String availability;
    private String branchID;


    public BookDTO(String bookID, String bookName, String authorName, String bookGenre, int qtyOnHand, String availability, LibraryBranch libraryBranch) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.authorName = authorName;
        this.bookGenre = bookGenre;
        this.qtyOnHand = qtyOnHand;
        this.availability = availability;
        if (libraryBranch != null) {
            this.branchID = libraryBranch.getBranchID();
        } else {
            this.branchID = null; // or any default value you prefer
        }
    }

    public BookDTO(String bookID, String bookName, String authorName, String bookGenre, int qtyOnHand, String availabilityStatus, String branchID) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.authorName = authorName;
        this.bookGenre = bookGenre;
        this.qtyOnHand = qtyOnHand;
        this.availability = availabilityStatus;
        this.branchID = branchID;
    }

    public BookDTO(Book book) {
        this.bookID = book.getBookID();
    }
}
