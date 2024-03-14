package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.AdminSignupDAO;
import lk.ijse.entity.Admin;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
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
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Admin ");
        ArrayList<Admin> list = (ArrayList<Admin>) query.list();

        transaction.commit();
        session.close();

        return list;
    }

    @Override
    public Admin getAdmin(String adminID) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Admin WHERE adminID=?1");
        query.setParameter(1, adminID);
        List<Admin> adminList = query.list();
        Admin admin = null;

        for (Admin admin1 : adminList) {
            admin = new Admin(admin1.getAdminID(),
                    admin1.getFirstName(),
                    admin1.getLastName(),
                    admin1.getNic(),
                    admin1.getEmailAddress(),
                    admin1.getUsername(),
                    admin1.getPassword()
            );
        }
        transaction.commit();
        session.close();
        return admin;
    }
}
