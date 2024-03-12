package lk.ijse.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class BookTM {
    private String bookID;
    private String bookName;
    private String authorName;
    private String bookGenre;
    private int qtyOnHand;
    private String branchID;
    private String availability;
}
