package lk.ijse.bo.custom;

import java.sql.SQLException;

public interface AdminLoginBO {
    boolean checkAdminCredentials(String adminID,String  username,String password) throws SQLException;
}
