package lk.ijse.dao.custom;

import lk.ijse.dao.crudDAO;
import lk.ijse.entity.Admin;

import java.sql.SQLException;

public interface AdminSignupDAO extends crudDAO<Admin> {
    Admin getAdmin(String adminID) throws SQLException;

}
