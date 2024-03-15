package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.LibraryBranchBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.AdminLoginDAO;
import lk.ijse.dao.custom.AdminSignupDAO;
import lk.ijse.dao.custom.LibraryBranchDAO;
import lk.ijse.dao.custom.impl.AdminSignupDAOImpl;
import lk.ijse.dao.custom.impl.LibraryBranchDAOImpl;
import lk.ijse.dto.AdminSignupDTO;
import lk.ijse.dto.LibraryBranchDTO;
import lk.ijse.entity.Admin;
import lk.ijse.entity.LibraryBranch;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibraryBranchBOImpl implements LibraryBranchBO{

  LibraryBranchDAO libraryBranchDAO = (LibraryBranchDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DTOTypes.LIBRARY_BRANCH);
  AdminSignupDAO adminSignupDAO = (AdminSignupDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DTOTypes.ADMIN);

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
                adminSignupDAO.getAdmin(dto.getAdminID())
        ));
    }

    @Override
    public boolean updateLibraryBranch(String adminID ,LibraryBranchDTO dto) throws SQLException {
        return libraryBranchDAO.update(new LibraryBranch(
                dto.getBranchID(),
                dto.getBranchName(),
                dto.getLocation(),
                adminSignupDAO.getAdmin(adminID)
        ));
    }

    @Override
    public boolean deleteLibraryBranch(String branchID) throws SQLException {
        return libraryBranchDAO.delete(branchID);
    }

    @Override
    public LibraryBranchDTO searchLibraryBranch(String branchID) throws SQLException {
        LibraryBranch libraryBranch = libraryBranchDAO.search(branchID);

        if (libraryBranch != null) {
            return new LibraryBranchDTO(
                    libraryBranch.getBranchID(),
                    libraryBranch.getBranchName(),
                    libraryBranch.getLocation(),
                    libraryBranch.getAdmin()
            );
        } else {
            return null; // or throw an exception or handle the null case as appropriate for your application
        }
    }


    @Override
    public ArrayList<LibraryBranchDTO> getAllLibraryBranches() throws SQLException {

        ArrayList<LibraryBranch> branches = libraryBranchDAO.getAll();
        ArrayList<LibraryBranchDTO> libraryBranchDTOS = new ArrayList<>();


        for (LibraryBranch libraryBranch : branches) {
            libraryBranchDTOS.add(new LibraryBranchDTO(
                    libraryBranch.getBranchID(),
                    libraryBranch.getBranchName(),
                    libraryBranch.getLocation(),
                    libraryBranch.getAdmin()

            ));
        }
        return libraryBranchDTOS;
    }

    @Override
    public ArrayList<AdminSignupDTO> getAllAdmins() throws SQLException {
        List<Admin> getAlladmins = adminSignupDAO.getAll();
        ArrayList<AdminSignupDTO> adminSignupDTOS = new ArrayList<>();

        for (Admin admin : getAlladmins){
            adminSignupDTOS.add(new AdminSignupDTO(
                    admin.getAdminID(),
                    admin.getFirstName(),
                    admin.getLastName(),
                    admin.getNic(),
                    admin.getEmailAddress(),
                    admin.getUsername(),
                    admin.getPassword()

            ));
        }
        return adminSignupDTOS;

    }



}
