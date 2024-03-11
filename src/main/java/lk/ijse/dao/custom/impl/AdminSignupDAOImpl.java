package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.AdminSignupDAO;
import lk.ijse.entity.Admin;

import java.sql.ResultSet;
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
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM admin");

        ArrayList<Admin> adminList = new ArrayList<>();

        while (resultSet.next()){
            adminList.add(new Admin(
                  resultSet.getString(1),
                  resultSet.getString(2),
                  resultSet.getString(3),
                  resultSet.getString(4),
                  resultSet.getString(5),
                  resultSet.getString(6),
                  resultSet.getString(7)
            ));
        }
        return adminList;
    }
}
