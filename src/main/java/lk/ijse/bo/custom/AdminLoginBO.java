package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;

import java.sql.SQLException;

public interface AdminLoginBO  extends SuperBO {
    boolean checkAdminCredentials(String adminID,String  username,String password) throws SQLException;
}
