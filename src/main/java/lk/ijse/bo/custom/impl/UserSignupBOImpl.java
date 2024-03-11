package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.UserSignUpBo;
import lk.ijse.dao.custom.UserSignUpDAO;
import lk.ijse.dao.custom.impl.UserSignupDAOImpl;
import lk.ijse.dto.UserSignupDTO;
import lk.ijse.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserSignupBOImpl implements UserSignUpBo {

    public UserSignUpDAO userSignUpDAO = new UserSignupDAOImpl();
    @Override
    public boolean saveUser(UserSignupDTO dto) throws SQLException {
        return userSignUpDAO.save(new User(
                dto.getUserID(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getNic(),
                dto.getEmailAddress(),
                dto.getUsername(),
                dto.getPassword())
        );
    }

    @Override
    public boolean updateUser(UserSignupDTO dto) throws SQLException {
        return userSignUpDAO.update(new User(
                dto.getUserID(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getNic(),
                dto.getEmailAddress(),
                dto.getUsername(),
                dto.getPassword())
        );
    }

    @Override
    public boolean deleteUser(String userID) throws SQLException {
        return userSignUpDAO.delete(userID);
    }

    @Override
    public UserSignupDTO searchUser(String userID) throws SQLException {
        User user = userSignUpDAO.search(userID);

        if (user!= null){
            return new UserSignupDTO(user);
        }
        return null;
    }

    @Override
    public List<UserSignupDTO> getAllUsers() throws SQLException {
        List<User> allUsers = userSignUpDAO.getAll();
        ArrayList<UserSignupDTO> userSignupDTOS = new ArrayList<>();

        for (User user :allUsers){
            userSignupDTOS.add(new UserSignupDTO(
                    user.getUserID(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getNic(),
                    user.getEmailAddress(),
                    user.getUsername(),
                    user.getPassword())
            );
        }
        return userSignupDTOS;
    }

}
