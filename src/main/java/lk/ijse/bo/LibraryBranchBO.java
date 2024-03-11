package lk.ijse.bo;

import lk.ijse.dto.LibraryBranchDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LibraryBranchBO {
    String generateNextBranchId() throws SQLException;

    boolean saveLibraryBranch(LibraryBranchDTO dto) throws SQLException;

    boolean updateLibraryBranch(LibraryBranchDTO dto) throws SQLException;

    boolean deleteLibraryBranch(String branchID) throws SQLException;

    LibraryBranchDTO searchLibraryBranch(String branchID) throws SQLException;

    ArrayList<LibraryBranchDTO> getAllLibraryBranches() throws SQLException;
}
