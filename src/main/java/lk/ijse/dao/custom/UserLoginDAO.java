package lk.ijse.dao.custom;

import java.sql.SQLException;

public interface UserLoginDAO {
    boolean checkUserCredentials(String username,String password) throws SQLException;
}
