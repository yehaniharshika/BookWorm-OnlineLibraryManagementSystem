package lk.ijse.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionTM {
    private String firstName;
    private String bookID;
    private String bookName;
    private LocalDate borrowedDate;
    private String bookReturnDate;

}
