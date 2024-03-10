package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Admin {
    private String adminID;
    private String firstName;
    private String lastName;
    private String nic;
    private String emailAddress;
    private String username;
    private String password;
}
