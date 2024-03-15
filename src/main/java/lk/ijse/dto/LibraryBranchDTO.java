package lk.ijse.dto;

import lk.ijse.entity.Admin;
import lombok.*;


@NoArgsConstructor
@Getter
@Setter
@ToString
public class LibraryBranchDTO {
    private String branchID;
    private String branchName;
    private String location;
    private String adminID;



    public LibraryBranchDTO(String branchID, String branchName, String location, String adminID) {
        this.branchID = branchID;
        this.branchName = branchName;
        this.location = location;
        this.adminID = adminID;
    }

    public LibraryBranchDTO(String branchID, String branchName, String location, Admin admin) {
        this.branchID = branchID;
        this.branchName = branchName;
        this.location = location;
        if (admin != null) {
            this.adminID = admin.getAdminID();
        } else {
            this.adminID = "Unknown"; // Set a default value
        }
    }
}
