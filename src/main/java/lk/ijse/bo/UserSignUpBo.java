package lk.ijse.bo;

import lk.ijse.dto.UserSignupDTO;
import lk.ijse.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserSignUpBo {
    boolean saveUser(UserSignupDTO dto) throws SQLException;
    boolean updateUser(UserSignupDTO dto) throws SQLException;
    boolean deleteUser(String userID) throws SQLException;
    UserSignupDTO searchUser(String userID) throws  SQLException;
    List<UserSignupDTO> getAllUsers() throws SQLException;
}
