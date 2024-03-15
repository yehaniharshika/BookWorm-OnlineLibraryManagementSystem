package lk.ijse.dto.tm;

import javafx.scene.control.Button;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class TransactionCartTM {
    private String bookID;
    private String bookName;
    private LocalDate borrowDate;
    private String dueDate;
    private String bookReturnDate;
    private Button btn;


    public TransactionCartTM(String bookID, String bookName, LocalDate borrowedDate, String dueDate, String bookReturnDate, Button btn) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.borrowDate = borrowedDate;
        this.dueDate = dueDate;
        this.bookReturnDate = bookReturnDate;
        this.btn = btn;
    }
}
