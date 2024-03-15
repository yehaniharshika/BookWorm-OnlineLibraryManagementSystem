package lk.ijse.dao.custom;

import lk.ijse.dao.crudDAO;
import lk.ijse.entity.LibraryBranch;

import java.sql.SQLException;

public interface LibraryBranchDAO extends crudDAO<LibraryBranch> {
    LibraryBranch getLibraryBranch(String branchID) throws SQLException;
}
