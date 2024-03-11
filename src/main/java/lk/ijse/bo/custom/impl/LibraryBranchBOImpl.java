package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.LibraryBranchBO;
import lk.ijse.dao.custom.impl.LibraryBranchDAOImpl;
import lk.ijse.dto.LibraryBranchDTO;
import lk.ijse.entity.LibraryBranch;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibraryBranchBOImpl implements LibraryBranchBO {

    public LibraryBranchDAOImpl libraryBranchDAO = new LibraryBranchDAOImpl();

    @Override
    public String generateNextBranchId() throws SQLException {
        return libraryBranchDAO.generateNextId();
    }

    @Override
    public boolean saveLibraryBranch(LibraryBranchDTO dto) throws SQLException {
        return libraryBranchDAO.save(new LibraryBranch(
                dto.getBranchID(),
                dto.getBranchName(),
                dto.getLocation(),
                dto.getDescription(),
                dto.getAdminID()
        ));
    }

    @Override
    public boolean updateLibraryBranch(LibraryBranchDTO dto) throws SQLException {
        return libraryBranchDAO.update(new LibraryBranch(
                dto.getBranchID(),
                dto.getBranchName(),
                dto.getLocation(),
                dto.getDescription(),
                dto.getAdminID()
        ));
    }

    @Override
    public boolean deleteLibraryBranch(String branchID) throws SQLException {
        return libraryBranchDAO.delete(branchID);
    }

    @Override
    public LibraryBranchDTO searchLibraryBranch(String branchID) throws SQLException {

        LibraryBranch libraryBranch = libraryBranchDAO.search(branchID);

        if (libraryBranch != null){
            return new LibraryBranchDTO(libraryBranch);
        }
            return null;
    }

    @Override
    public ArrayList<LibraryBranchDTO> getAllLibraryBranches() throws SQLException {
        List<LibraryBranch> getAllLibraryBranches = libraryBranchDAO.getAll();
        ArrayList<LibraryBranchDTO> libraryBranchDTOS = new ArrayList<>();
        
        for (LibraryBranch libraryBranch : getAllLibraryBranches){
            libraryBranchDTOS.add(new LibraryBranchDTO(
                    libraryBranch.getBranchID(),
                    libraryBranch.getBranchName(),
                    libraryBranch.getLocation(),
                    libraryBranch.getDescription(),
                    libraryBranch.getAdminID()
            ));
        }
        return libraryBranchDTOS;
    }
}
