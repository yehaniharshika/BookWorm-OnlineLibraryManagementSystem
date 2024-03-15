package lk.ijse.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class BookReservationDetailsDTO {
    private String bookID;
    private String bookName;
    private LocalDate borrowedDate;
    private  String  dueDate;
    private String bookReturnDate;
    //private int qty;


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
