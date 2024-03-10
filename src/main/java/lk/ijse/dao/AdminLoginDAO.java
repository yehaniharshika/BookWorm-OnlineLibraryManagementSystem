package lk.ijse.dao;

import java.sql.SQLException;

public interface AdminLoginDAO {
    boolean checkAdminCredentials(String adminID,String  username,String password) throws SQLException;
}
