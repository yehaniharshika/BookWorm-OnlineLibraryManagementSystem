package lk.ijse.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
public class Admin {
    @Id
    private String adminID;
    private String firstName;
    private String lastName;
    private String nic;
    private String emailAddress;
    private String username;
    private String password;

    @OneToMany(mappedBy = "admin",cascade = CascadeType.ALL)
    private List<LibraryBranch> libraryBranchList;


    public Admin(String adminID, String firstName, String lastName, String nic, String emailAddress, String username, String password) {
        this.adminID = adminID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nic = nic;
        this.emailAddress = emailAddress;
        this.username = username;
        this.password = password;

    }

    public Admin() {

    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<LibraryBranch> getLibraryBranchList() {
        return libraryBranchList;
    }

    public void setLibraryBranchList(List<LibraryBranch> libraryBranchList) {
        this.libraryBranchList = libraryBranchList;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
