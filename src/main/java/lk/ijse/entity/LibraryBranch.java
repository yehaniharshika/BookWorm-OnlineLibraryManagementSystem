package lk.ijse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class LibraryBranch {
    @Id
    private String branchID;
    private String branchName;
    private String location;

    @ManyToOne
    @JoinColumn(name = "adminID")
    private Admin admin;

    public LibraryBranch(Admin admin, String branchID, String branchName, String description, String location) {
        this.admin = admin;
        this.branchID = branchID;
        this.branchName = branchName;
        this.location = location;
    }

    public LibraryBranch() {

    }
    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {

        this.admin = admin;
    }

    public String getBranchID() {
        return branchID;
    }

    public void setBranchID(String branchID) {
        this.branchID = branchID;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
