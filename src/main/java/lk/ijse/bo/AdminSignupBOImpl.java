package lk.ijse.bo;

import lk.ijse.dao.AdminSignupDAO;
import lk.ijse.dao.AdminSignupDAOImpl;
import lk.ijse.dto.AdminSignupDTO;
import lk.ijse.dto.UserSignupDTO;
import lk.ijse.entity.Admin;

import java.sql.SQLException;

public class AdminSignupBOImpl implements AdminSignupBO{

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
}
