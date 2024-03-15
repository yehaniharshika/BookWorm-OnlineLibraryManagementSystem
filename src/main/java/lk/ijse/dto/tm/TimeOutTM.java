package lk.ijse.dto.tm;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TimeOutTM {
    private String id;
    private String name;
    private String bookId;
    private LocalDate borrowDate;
    private String returnDate;


}
