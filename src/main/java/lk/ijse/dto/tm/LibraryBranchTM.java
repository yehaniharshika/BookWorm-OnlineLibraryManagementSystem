package lk.ijse.dto.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LibraryBranchTM {
    private String branchID;
    private String branchName;
    private String location;
    private String description;
    private String adminID;
}
