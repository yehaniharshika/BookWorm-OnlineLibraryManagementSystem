package lk.ijse.dao.custom;

import lk.ijse.dao.crudDAO;
import lk.ijse.entity.Book;
import lk.ijse.entity.User;

import java.sql.SQLException;

public interface UserSignUpDAO extends crudDAO<User> {
   /* boolean saveUser(UserSignupDTO dto) throws SQLException;
    boolean updateUser(UserSignupDTO dto) throws SQLException;
    boolean deleteUser(String userID) throws SQLException;
    User searchUser(String userID) throws SQLException;
    ArrayList<User> getAllUsers() throws SQLException;*/
    User getUser(String userID) throws SQLException;

}
