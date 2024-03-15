package lk.ijse.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TimeOutDTO {
    private String id;
    private String name;
    private String bookId;
    private LocalDate borrowDate;
    private String returnDate;
}
