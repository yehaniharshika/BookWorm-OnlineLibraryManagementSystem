package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.UserSignUpDAO;
import lk.ijse.dto.AdminSignupDTO;
import lk.ijse.entity.Book;
import lk.ijse.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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

        Query query = session.createQuery("select userID from User order by userID desc limit 1");
        String id = (String) query.uniqueResult();

        transaction.commit();
        session.close();

        return splitId(id);
    }

    private String splitId(String currentID) {
        if(currentID != null) {
            String[] strings = currentID.split("U0");
            if(strings.length > 1) {
                int id = Integer.parseInt(strings[1]);
                id++;
                String ID = String.valueOf(id);
                int length = ID.length();
                if (length < 2){
                    return "U00"+id;
                } else if (length < 3) {
                    return "U0"+id;
                } else {
                    return "U"+id;
                }
            } else {
                return "U001";
            }
        }
        return "U001";
    }



    @Override
    public boolean save(User entity) throws SQLException {
       Session session = FactoryConfiguration.getInstance().getSession();
       Transaction transaction = session.beginTransaction();

       Query query = session.createQuery("insert into User(userID,firstName,lastName,nic,emailAddress) values(:userID, :firstName,:lastName,:nic,:emailAddress)");
       query.setParameter("userID", entity.getUserID());
       query.setParameter("firstName", entity.getFirstName());
       query.setParameter("lastName", entity.getLastName());
       query.setParameter("nic", entity.getNic());
       query.setParameter("emailAddress", entity.getEmailAddress());

       int i = query.executeUpdate();

       transaction.commit();
       session.close();

        return (i==1? true:false);
    }

    @Override
    public boolean update(User entity) throws SQLException {
       Session session = FactoryConfiguration.getInstance().getSession();
       Transaction transaction = session.beginTransaction();

       Query query = session.createQuery("update User set firstName=?1,lastName=?2,nic=?3,emailAddress=?4 where userID=?5");
       query.setParameter(1,entity.getFirstName());
       query.setParameter(2,entity.getLastName());
       query.setParameter(3,entity.getNic());
       query.setParameter(4,entity.getEmailAddress());
       query.setParameter(5,entity.getUserID());

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
           user = new User(
                   u.getUserID(),
                   u.getFirstName(),
                   u.getLastName(),
                   u.getNic(),
                   u.getEmailAddress());
       }
       transaction.commit();
       session.close();
       return user;

    }

    @Override
    public ArrayList<User> getAll() throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM User ");
        ArrayList<User> list = (ArrayList<User>) query.list();

        transaction.commit();
        session.close();

        return list;

    }


    @Override
    public User getUser(String userID) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM User WHERE userID=?1");
        query.setParameter(1, userID);
        List<User> userList = query.list();
        User user = null;

        for (User user1 : userList) {
            user = new User(
                    user1.getUserID(),
                    user1.getFirstName(),
                    user1.getLastName(),
                    user1.getNic(),
                    user1.getEmailAddress()
            );
        }
        transaction.commit();
        session.close();
        return user;
    }
}
