package lk.ijse.dto;

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
    private String description;
    private String adminID;


    public LibraryBranchDTO(LibraryBranch libraryBranch) {
        this.branchID = libraryBranch.getBranchID();
        this.branchName = libraryBranch.getBranchName();
        this.location = libraryBranch.getLocation();
        this.description = libraryBranch.getDescription();
        this.adminID = libraryBranch.getAdminID();
    }
}
