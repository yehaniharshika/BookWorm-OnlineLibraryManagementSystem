package lk.ijse.bo;

import java.sql.SQLException;

public interface UserLoginBO {
    boolean checkUserCredentials(String username,String password) throws SQLException;
}
