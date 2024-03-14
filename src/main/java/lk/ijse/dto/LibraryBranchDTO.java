package lk.ijse.dto;

import lk.ijse.entity.Admin;
import lk.ijse.entity.LibraryBranch;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LibraryBranchDTO {
    private String branchID;
    private String branchName;
    private String location;
    private Admin adminID;


    public LibraryBranchDTO(LibraryBranch libraryBranch) {
        this.branchID = libraryBranch.getBranchID();
        this.branchName = libraryBranch.getBranchName();
        this.location = libraryBranch.getLocation();
        this.adminID = libraryBranch.getAdmin();
    }

    public LibraryBranchDTO(String branchID, String branchName, String location, String description) {
    }
}
