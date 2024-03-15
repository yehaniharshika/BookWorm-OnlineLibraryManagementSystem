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
    private String ReservationID;
    private LocalDate borrowDate;
    private String dueDate;
    private String bookReturnDate;
    private String userID;
    private String bookID;
}
