package lk.ijse.dto;

import lk.ijse.entity.User;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserSignupDTO {
    private String userID;
    private String firstName;
    private String lastName;
    private String nic;
    private String emailAddress;
    private String username;
    private String password;

    public UserSignupDTO(User user) {
        this.userID = user.getUserID();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.nic = user.getNic();
        this.emailAddress = user.getEmailAddress();
        this.username = user.getUsername();
        this.password = user.getPassword();
    }
}
