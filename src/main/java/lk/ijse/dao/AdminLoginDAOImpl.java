package lk.ijse.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminLoginDAOImpl implements AdminLoginDAO{
    @Override
    public  boolean checkAdminCredentials(String adminID,String  username,String password) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM librarian WHERE adminID =?  and username=?  and password=?",adminID,username,password);

        String AdminID = null;
        String UserName = null;
        String Password = null;

        while (resultSet.next()){
            AdminID = resultSet.getString(1);
            UserName = resultSet.getString(6);
            Password = resultSet.getString(7);
        }
        if (adminID.equals(AdminID) && username.equals(UserName) && password.equals(Password)){
            return  true;
        }

        return false;
    }
}
