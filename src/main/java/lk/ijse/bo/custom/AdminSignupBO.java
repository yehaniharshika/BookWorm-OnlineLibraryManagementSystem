package lk.ijse.bo.custom;

import lk.ijse.dto.AdminSignupDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AdminSignupBO {
    boolean saveAdmin(AdminSignupDTO dto) throws SQLException;
    boolean updateAdmin(AdminSignupDTO dto) throws SQLException;
    boolean deleteAdmin(String adminID) throws SQLException;
    AdminSignupDTO searchAdmin(String adminID) throws  SQLException;
    ArrayList<AdminSignupDTO> getAllAdmins() throws SQLException;
    String generateNextAdminId() throws SQLException;
}
