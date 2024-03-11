package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {
    private String bookID;
    private String bookName;
    private String authorName;
    private String bookGenre;
    private int qty;
    private String branchID;
}
