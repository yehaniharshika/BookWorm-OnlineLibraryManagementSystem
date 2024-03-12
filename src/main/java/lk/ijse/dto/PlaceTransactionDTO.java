package lk.ijse.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PlaceTransactionDTO {
    private String transactionID;
    private String userID;
    private String bookID;
    private String  bookName;
    private String borrowedDate;
    private  String  dueDate;
    private String bookReturnDate;
    private int qty;
}
