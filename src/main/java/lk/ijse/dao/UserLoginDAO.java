package lk.ijse.dao;

import java.sql.SQLException;

public interface UserLoginDAO {
    boolean checkUserCredentials(String username,String password) throws SQLException;
}
