package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.UserSignUpDAO;
import lk.ijse.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserSignupDAOImpl  implements UserSignUpDAO {


    @Override
    public String getCount() throws SQLException {
        return "";
    }

    @Override
    public String generateNextId() throws SQLException {
        return "";
    }

    @Override
    public boolean save(User entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO user VALUES(?,?,?,?,?,?,?)",
                entity.getUserID(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getNic(),
                entity.getEmailAddress(),
                entity.getUsername(),
                entity.getPassword()
        );
    }

    @Override
    public boolean update(User entity) throws SQLException {
        return SQLUtil.execute("UPDATE user set firstName=?, lastName=?, nic=?, emailAddress=?, username=?, password=? WHERE userID=?",
                entity.getFirstName(),
                entity.getLastName(),
                entity.getNic(),
                entity.getEmailAddress(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getUserID()
        );
    }

    @Override
    public boolean delete(String userID) throws SQLException {
        return SQLUtil.execute("DELETE FROM user WHERE userID=?",userID);
    }

    @Override
    public User search(String userID) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM user WHERE userID=?",userID);
        User entity = null;

        if (resultSet.next()){
            entity = new User(
                    resultSet.getString("userID"),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getString("nic"),
                    resultSet.getString("emailAddress"),
                    resultSet.getString("username"),
                    resultSet.getString("password")
            );
        }
        return entity;
    }

    @Override
    public ArrayList<User> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM user");

        ArrayList<User> userList = new ArrayList<>();

        while (resultSet.next()){
            userList.add(new User(
                    resultSet.getString("userID"),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"),
                    resultSet.getString("nic"),
                    resultSet.getString("emailAddress"),
                    resultSet.getString("userName"),
                    resultSet.getString("password")
            ));
        }
        return userList;
    }


}
