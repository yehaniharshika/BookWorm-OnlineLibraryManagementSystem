package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.UserLoginBO;
import lk.ijse.dao.custom.impl.UserLoginDAOImpl;

import java.sql.SQLException;

public class UserLoginBoImpl implements UserLoginBO {

    public UserLoginDAOImpl userLoginDAO = new UserLoginDAOImpl();
    @Override
    public boolean checkUserCredentials(String username, String password) throws SQLException {
        return userLoginDAO.checkUserCredentials(username,password);
    }
}
