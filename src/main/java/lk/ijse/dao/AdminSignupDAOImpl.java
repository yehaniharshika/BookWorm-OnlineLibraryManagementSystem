package lk.ijse.dao;

import lk.ijse.dto.AdminSignupDTO;
import lk.ijse.entity.Admin;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminSignupDAOImpl implements AdminSignupDAO {
    @Override
    public String getCount() throws SQLException {
        return "";
    }

    @Override
    public String generateNextId() throws SQLException {
        return "";
    }

    @Override
    public boolean save(Admin entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO admin VALUES(?,?,?,?,?,?,?)",
                entity.getAdminID(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getNic(),
                entity.getEmailAddress(),
                entity.getUsername(),
                entity.getPassword()
        );
    }

    @Override
    public boolean update(Admin entity) throws SQLException {
        return SQLUtil.execute("UPDATE admin set firstName=?, lastName=?, nic=?, emailAddress=?, username=?, password=? WHERE adminID=?",
                entity.getFirstName(),
                entity.getLastName(),
                entity.getNic(),
                entity.getEmailAddress(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getAdminID()
        );
    }

    @Override
    public boolean delete(String adminID) throws SQLException {
        return SQLUtil.execute("DELETE FROM user WHERE adminID=?",adminID);
    }

    @Override
    public Admin search(String adminID) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Admin> getAll() throws SQLException {
        return null;
    }
}
