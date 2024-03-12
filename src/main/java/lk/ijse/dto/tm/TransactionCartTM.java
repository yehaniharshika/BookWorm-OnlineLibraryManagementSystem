package lk.ijse.dto.tm;

import javafx.scene.control.Button;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TransactionCartTM {
    private String transactionID;
    private String borrowedDate;
    private String dueDate;
    private String bookReturnDate;
    private int qty;
    private String userID;
    private String bookID;

}
