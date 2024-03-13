package lk.ijse.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BookReservationDetailsDTO {

    private String borrowedDate;
    private  String  dueDate;
    private String bookReturnDate;
    //private int qty;
    private String bookID;

    /*public bookReservationDetailsDTO(bookReservationDetailsDTO dto) {
        this.transactionID = dto.transactionID;
        this.borrowedDate = dto.borrowedDate;
        this.dueDate = dto.dueDate;
        this.bookReturnDate = dto.bookReturnDate;
        this.qty = dto.qty;
        this.userID = dto.userID;
        this.bookID = dto.bookID;
    }*/
}
