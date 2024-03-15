package lk.ijse.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserSignupTM {
    private String userID;
    private String firstName;
    private String lastName;
    private String nic;
    private String emailAddress;
}
