package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.AdminLoginBO;
import lk.ijse.dao.custom.impl.AdminLoginDAOImpl;

import java.sql.SQLException;

public class AdminLoginBOImpl  implements AdminLoginBO {
    AdminLoginDAOImpl adminLoginDAO = new AdminLoginDAOImpl();
    @Override
    public boolean checkAdminCredentials(String adminID, String username, String password) throws SQLException {
        return adminLoginDAO.checkAdminCredentials(adminID,username,password);
    }
}
