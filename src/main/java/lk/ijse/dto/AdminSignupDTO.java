package lk.ijse.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class AdminSignupDTO {
    private String adminID;
    private String firstName;
    private String lastName;
    private String nic;
    private String emailAddress;
    private String username;
    private String password;
}
