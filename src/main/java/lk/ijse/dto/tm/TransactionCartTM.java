package lk.ijse.dto.tm;

import javafx.scene.control.Button;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
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

}
