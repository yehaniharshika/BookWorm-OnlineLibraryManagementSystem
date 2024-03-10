package lk.ijse.bo;

import lk.ijse.dto.AdminSignupDTO;
import lk.ijse.dto.UserSignupDTO;

import java.sql.SQLException;

public interface AdminSignupBO {
    boolean saveAdmin(AdminSignupDTO dto) throws SQLException;
    boolean updateAdmin(AdminSignupDTO dto) throws SQLException;
    boolean deleteAdmin(String adminID) throws SQLException;
    UserSignupDTO searchAdmin(String adminID) throws  SQLException;
}
