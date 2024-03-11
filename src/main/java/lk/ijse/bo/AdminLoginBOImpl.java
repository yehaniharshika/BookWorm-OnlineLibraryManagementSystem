package lk.ijse.bo;

import lk.ijse.dao.AdminLoginDAOImpl;

import java.sql.SQLException;

public class AdminLoginBOImpl  implements AdminLoginBO{
    AdminLoginDAOImpl adminLoginDAO = new AdminLoginDAOImpl();
    @Override
    public boolean checkAdminCredentials(String adminID, String username, String password) throws SQLException {
        return adminLoginDAO.checkAdminCredentials(adminID,username,password);
    }
}
