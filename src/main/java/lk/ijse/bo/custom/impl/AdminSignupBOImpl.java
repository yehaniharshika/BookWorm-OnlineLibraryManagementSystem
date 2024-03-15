package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.AdminSignupBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.AdminSignupDAO;
import lk.ijse.dao.custom.impl.AdminSignupDAOImpl;
import lk.ijse.dto.AdminSignupDTO;
import lk.ijse.entity.Admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminSignupBOImpl implements AdminSignupBO {

    AdminSignupDAO adminSignupDAO = (AdminSignupDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DTOTypes.ADMIN);

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
        return adminSignupDAO.delete(adminID);
    }

    @Override
    public AdminSignupDTO searchAdmin(String adminID) throws SQLException {
        Admin admin =adminSignupDAO.search(adminID);


        return new AdminSignupDTO(
                admin.getAdminID(),
                admin.getFirstName(),
                admin.getLastName(),
                admin.getNic(),
                admin.getEmailAddress(),
                admin.getUsername(),
                admin.getPassword()

        );
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

    @Override
    public String generateNextAdminId() throws SQLException {
        return adminSignupDAO.generateNextId();
    }
}
