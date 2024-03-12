package lk.ijse.dto.tm;

import javafx.scene.control.Button;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TransactionCartTM {
    private String ISBN;
    private String bookName;
    private String borrowedDate;
    private  String  dueDate;
    private String bookReturnDate;
    private int qty;
    //private Button btn;
}
