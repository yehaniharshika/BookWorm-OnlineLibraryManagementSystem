package lk.ijse.dto;

import lk.ijse.entity.Book;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BookDTO {
    private String bookID;
    private String bookName;
    private String authorName;
    private String bookGenre;
    private int qty;

    public BookDTO(Book book) {
        this.bookID = book.getBookID();
        this.bookName = book.getBookName();
        this.authorName = book.getAuthorName();
        this.bookGenre = book.getBookGenre();
        this.qty = book.getQty();
    }
}
