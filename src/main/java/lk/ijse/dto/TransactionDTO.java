package lk.ijse.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TransactionDTO {
    private String firstName;
    private String bookID;
    private String bookName;
    private LocalDate borrowedDate;
    private String bookReturnDate;
}
