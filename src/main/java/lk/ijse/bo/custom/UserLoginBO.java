package lk.ijse.bo.custom;

import java.sql.SQLException;

public interface UserLoginBO {
    boolean checkUserCredentials(String username,String password) throws SQLException;
}
