package lk.ijse.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PlaceTransactionDTO {
    private String transactionID;
    private String borrowedDate;
    private  String  dueDate;
    private String bookReturnDate;
    private int qty;
    private String userID;
    private String bookID;

    public PlaceTransactionDTO(PlaceTransactionDTO dto) {
        this.transactionID = dto.transactionID;
        this.borrowedDate = dto.borrowedDate;
        this.dueDate = dto.dueDate;
        this.bookReturnDate = dto.bookReturnDate;
        this.qty = dto.qty;
        this.userID = dto.userID;
        this.bookID = dto.bookID;
    }
}
