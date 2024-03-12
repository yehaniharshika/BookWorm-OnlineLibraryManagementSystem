package lk.ijse.dto;

import lk.ijse.entity.Book;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class BookDTO {
    private String bookID;
    private String bookName;
    private String authorName;
    private String bookGenre;
    private int qtyOnHand;
    private String branchID;

    public BookDTO(Book book) {
        this.bookID = book.getBookID();
        this.bookName = book.getBookName();
        this.authorName = book.getAuthorName();
        this.bookGenre = book.getBookGenre();
        this.qtyOnHand = book.getQtyOnHand();
        this.branchID = book.getBranchID();
    }
}
