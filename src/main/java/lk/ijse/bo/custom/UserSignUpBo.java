package lk.ijse.bo.custom;

import lk.ijse.dto.UserSignupDTO;

import java.sql.SQLException;
import java.util.List;

public interface UserSignUpBo {
    boolean saveUser(UserSignupDTO dto) throws SQLException;
    boolean updateUser(UserSignupDTO dto) throws SQLException;
    boolean deleteUser(String userID) throws SQLException;
    UserSignupDTO searchUser(String userID) throws  SQLException;
    List<UserSignupDTO> getAllUsers() throws SQLException;
}
