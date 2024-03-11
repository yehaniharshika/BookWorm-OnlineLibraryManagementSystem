package lk.ijse.dao.custom;

import java.sql.SQLException;

public interface AdminLoginDAO {
    boolean checkAdminCredentials(String adminID,String  username,String password) throws SQLException;
}
