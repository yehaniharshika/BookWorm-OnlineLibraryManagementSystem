package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.UserLoginDAO;
import lk.ijse.entity.Admin;
import lk.ijse.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserLoginDAOImpl  implements UserLoginDAO{

    @Override
    public boolean checkUserCredentials(String username,String password) throws SQLException {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("select User from User user where user.username = :username and user.password=:password");

        query.setParameter("username", username);
        query.setParameter("password", password);

        User user = (User) query.uniqueResult();
        transaction.commit();
        session.close();
        return user != null;
      /*  ResultSet resultSet = SQLUtil.execute("select * from user where username=? and password=?",
                username,
                password);

        String Username = null;
        String Password = null;

        while (resultSet.next()){
            Username = resultSet.getString(6);
            Password = resultSet.getString(7);
        }

        if (username.equals(Username) && password.equals(Password)){
            return true;
        }
        return false;*/
    }

}
