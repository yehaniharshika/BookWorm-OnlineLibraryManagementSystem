package lk.ijse.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReservationDTO {
    private String reservationID;
    private String BorrowDate;
    private String dueDate;
    private String bookReturnDate;
    private String userID;
    private String bookID;
}
