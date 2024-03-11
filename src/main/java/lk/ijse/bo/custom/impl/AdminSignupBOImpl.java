package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.AdminSignupBO;
import lk.ijse.dao.custom.AdminSignupDAO;
import lk.ijse.dao.custom.impl.AdminSignupDAOImpl;
import lk.ijse.dto.AdminSignupDTO;
import lk.ijse.dto.UserSignupDTO;
import lk.ijse.entity.Admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminSignupBOImpl implements AdminSignupBO {

    private AdminSignupDAO adminSignupDAO = new AdminSignupDAOImpl();

    @Override
    public boolean saveAdmin(AdminSignupDTO dto) throws SQLException {
        return adminSignupDAO.save(new Admin(
                dto.getAdminID(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getNic(),
                dto.getEmailAddress(),
                dto.getUsername(),
                dto.getPassword()
        ));
    }

    @Override
    public boolean updateAdmin(AdminSignupDTO dto) throws SQLException {
        return adminSignupDAO.update(new Admin(
                dto.getAdminID(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getNic(),
                dto.getEmailAddress(),
                dto.getUsername(),
                dto.getPassword()
        ));
    }

    @Override
    public boolean deleteAdmin(String adminID) throws SQLException {
        return false;
    }

    @Override
    public UserSignupDTO searchAdmin(String adminID) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<AdminSignupDTO> getAllAdmins() throws SQLException {
        List<Admin> AllAdminList = adminSignupDAO.getAll();
        ArrayList<AdminSignupDTO> adminSignupDTOS = new ArrayList<>();

        for (Admin admin : AllAdminList){
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
