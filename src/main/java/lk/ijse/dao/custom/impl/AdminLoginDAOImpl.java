package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.AdminLoginDAO;
import lk.ijse.entity.Admin;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminLoginDAOImpl implements AdminLoginDAO {
    @Override
    public  boolean checkAdminCredentials(String adminID,String  username,String password) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "SELECT admin FROM Admin admin WHERE admin.adminID = :adminID AND admin.username = :username AND admin.password = :password";

        Query query = session.createQuery(hql);
        query.setParameter("adminID", adminID);
        query.setParameter("username", username);
        query.setParameter("password", password);

        Admin admin = (Admin) query.uniqueResult();
        transaction.commit();
        session.close();
        return admin != null;


        /*ResultSet resultSet = SQLUtil.execute("SELECT * FROM admin WHERE adminID =?  and username=?  and password=?",adminID,username,password);

        String AdminID = null;
        String UserName = null;
        String Password = null;

        while (resultSet.next()){
            AdminID = resultSet.getString(1);
            UserName = resultSet.getString(6);
            Password = resultSet.getString(7);
        }
        if (adminID.equals(AdminID) && username.equals(UserName) && password.equals(Password)){
            return  true;
        }

        return false;*/
    }
}
