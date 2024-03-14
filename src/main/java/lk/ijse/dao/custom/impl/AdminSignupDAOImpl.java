package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.AdminSignupDAO;
import lk.ijse.entity.Admin;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        Session session =  FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Object save = session.save(entity);
        transaction.commit();
        session.close();
        return save != null;
    }

    @Override
    public boolean update(Admin entity) throws SQLException {
        Session session =  FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Object update = session;
        transaction.commit();
        session.close();
        return update != null;
    }

    @Override
    public boolean delete(String adminID) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.get(Admin.class,adminID));
        transaction.commit();
        session.close();
        return adminID != null;
    }

    @Override
    public Admin search(String adminID) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Admin> getAll() throws SQLException {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Query<Admin> query = session.createQuery("from Admin", Admin.class);
            List<Admin> adminList = query.list();
            return new ArrayList<>(adminList);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }
}
