package lk.ijse.bo;

import lk.ijse.dao.UserLoginDAOImpl;

import java.sql.SQLException;

public class UserLoginBoImpl implements UserLoginBO{

    public UserLoginDAOImpl userLoginDAO = new UserLoginDAOImpl();
    @Override
    public boolean checkUserCredentials(String username, String password) throws SQLException {
        return userLoginDAO.checkUserCredentials(username,password);
    }
}
