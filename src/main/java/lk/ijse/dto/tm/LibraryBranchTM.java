package lk.ijse.dto.tm;

import lk.ijse.entity.Admin;
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
    private String adminID;


}
