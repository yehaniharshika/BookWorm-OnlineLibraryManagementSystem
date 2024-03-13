package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.UserLoginDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLoginDAOImpl implements UserLoginDAO {

    @Override
    public boolean checkUserCredentials(String username,String password) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("select * from user where username=? and password=?",
                username,
                password);

        String Username = null;
        String Password = null;

        while (resultSet.next()){
            Username = resultSet.getString(6);
            Password = resultSet.getString(7);
        }

        if (username.equals(Username) && password.equals(Password)){
            return true;
        }
        return false;
    }

}
