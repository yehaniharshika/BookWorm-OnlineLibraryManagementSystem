package lk.ijse.dao.custom.impl;

import jakarta.transaction.HeuristicMixedException;
import jakarta.transaction.HeuristicRollbackException;
import jakarta.transaction.RollbackException;
import jakarta.transaction.SystemException;
import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.UserSignUpDAO;
import lk.ijse.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserSignupDAOImpl implements UserSignUpDAO{


   @Override
    public String getCount() throws SQLException {
        return "";
    }

    @Override
    public String generateNextId() throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "select u.userID from User u order by u.userID desc limit 1";
        Query query = session.createQuery(hql);
        query.setMaxResults(1);
        List<String> resultList = query.getResultList();
        if (!resultList.isEmpty()) {
            String id = resultList.get(0);
            int newItemId = Integer.parseInt(id.replace("U00-", "")) + 1;
            return String.format("U00-%03d", newItemId);
        } else {
            transaction.commit();
            session.close();
            return "U00-001";
        }
    }

    @Override
    public boolean save(User entity) throws SQLException {
       Session session = FactoryConfiguration.getInstance().getSession();
       Transaction transaction = session.beginTransaction();

       Query query = session.createQuery("insert into User(userID,firstName,lastName,nic,emailAddress,username,password) values(:userID, :firstName,:lastName,:nic,:emailAddress, :username, :password)");
       query.setParameter("userID", entity.getUserID());
       query.setParameter("firstName", entity.getFirstName());
       query.setParameter("lastName", entity.getLastName());
       query.setParameter("nic", entity.getNic());
       query.setParameter("emailAddress", entity.getEmailAddress());
       query.setParameter("username", entity.getUsername());
       query.setParameter("password", entity.getPassword());

       int i = query.executeUpdate();

       transaction.commit();
       session.close();


       /* return SQLUtil.execute("INSERT INTO user VALUES(?,?,?,?,?,?,?)",
                entity.getUserID(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getNic(),
                entity.getEmailAddress(),
                entity.getUsername(),
                entity.getPassword()
        );*/
        return (i==1? true:false);
    }

    @Override
    public boolean update(User entity) throws SQLException {
       Session session = FactoryConfiguration.getInstance().getSession();
       Transaction transaction = session.beginTransaction();

       Query query = session.createQuery("update User set firstName=?1,lastName=?2,nic=?3,emailAddress=?4,username=?5,password=?6 where userID=?7");
       query.setParameter(1,entity.getFirstName());
       query.setParameter(2,entity.getLastName());
       query.setParameter(3,entity.getNic());
       query.setParameter(4,entity.getEmailAddress());
       query.setParameter(5,entity.getUsername());
       query.setParameter(6,entity.getPassword());
       query.setParameter(7,entity.getUserID());

       int i = query.executeUpdate();

       transaction.commit();
       session.close();

       return (i==1? true:false);
        /*return SQLUtil.execute("UPDATE user set firstName=?, lastName=?, nic=?, emailAddress=?, username=?, password=? WHERE userID=?",
                entity.getFirstName(),
                entity.getLastName(),
                entity.getNic(),
                entity.getEmailAddress(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getUserID()
        );*/
    }

    @Override
    public boolean delete(String userID) throws SQLException {
       Session session =FactoryConfiguration.getInstance().getSession();
       Transaction transaction = session.beginTransaction();

       Query query = session.createQuery("delete from User  where userID=?1");
       query.setParameter(1,userID);
       int i = query.executeUpdate();

       transaction.commit();
       session.close();
       return (i==1? true:false);
        /*return SQLUtil.execute("DELETE FROM user WHERE userID=?",userID);*/
    }

    @Override
    public User search(String userID) throws SQLException {
       Session session = FactoryConfiguration.getInstance().getSession();
       Transaction transaction = session.beginTransaction();

       Query query = session.createQuery("from User where userID=?1");
       query.setParameter(1,userID);

       List<User> userList = query.list();

       User user = null;

       for (User u : userList) {
           user = new User(u.getUserID(),
                   u.getFirstName(),
                   u.getLastName(),
                   u.getNic(),
                   u.getEmailAddress(),
                   u.getUsername(),
                   u.getPassword()
           );
       }
       transaction.commit();
       session.close();
       return user;
        /*ResultSet resultSet = SQLUtil.execute("SELECT * FROM user WHERE userID=?",userID);
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
        return entity;*/
    }

    @Override
    public ArrayList<User> getAll() throws SQLException {
       Session session = FactoryConfiguration.getInstance().getSession();
       Transaction transaction = session.beginTransaction();

       Query query = session.createQuery("from User ");

       ArrayList<User> list = (ArrayList<User>) query.list();

       transaction.commit();
       session.close();
       return list;
       /* ResultSet resultSet = SQLUtil.execute("SELECT * FROM user");

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
        return userList;*/
    }


}
