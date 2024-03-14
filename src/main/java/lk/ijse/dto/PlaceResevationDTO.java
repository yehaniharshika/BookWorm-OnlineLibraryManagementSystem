package lk.ijse.dto;

import lk.ijse.dto.tm.TransactionCartTM;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PlaceResevationDTO {
    private String reservationID;
    private String borrowedDate;
    private String userID;

    private List<TransactionCartTM> transactionCartTMList =  new ArrayList<>();

}
