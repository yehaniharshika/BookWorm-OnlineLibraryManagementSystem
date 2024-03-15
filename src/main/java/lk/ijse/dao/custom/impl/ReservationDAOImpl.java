package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.ReservationDAO;
import lk.ijse.entity.Book;
import lk.ijse.entity.Reservation;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservationDAOImpl implements  ReservationDAO{
    @Override
    public String getCount() throws SQLException {
        return "";
    }

    @Override
    public String generateNextId() throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("select reservationID from Reservation order by reservationID desc limit 1");
        String id = (String) query.uniqueResult();

        transaction.commit();
        session.close();

        return splitId(id);
    }

    private String splitId(String currentReservationId) {
        if(currentReservationId != null) {
            String[] strings = currentReservationId.split("R0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "R00"+id;
            }else {
                if (length < 3){
                    return "R0"+id;
                }else {
                    return "R"+id;
                }
            }
        }
        return "R001";
    }

    @Override
    public boolean update(Reservation entity) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("update  Reservation set BorrowDate=?1,dueDate=?2,bookReturnDate=?3,user=?4,book=?5 where reservationID=?6");
        query.setParameter(1,entity.getBorrowDate());
        query.setParameter(2,entity.getDueDate());
        query.setParameter(3,entity.getBookReturnDate());
        query.setParameter(4,entity.getUser());
        query.setParameter(5,entity.getBook());
        query.setParameter(6,entity.getReservationID());
        int i = query.executeUpdate();

        transaction.commit();
        session.close();
        return (i==1? true:false);
    }

    @Override
    public boolean delete(String reservationID) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("DELETE FROM Reservation WHERE reservationID = ?1");
        query.setParameter(1, reservationID);
        int i = query.executeUpdate();

        transaction.commit();
        session.close();

        return (i==1 ? true : false);
    }

    @Override
    public Reservation search(String reservationID) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query<Reservation> query = session.createQuery("FROM Reservation WHERE reservationID =?1", Reservation.class);
            query.setParameter(1, reservationID);
            Reservation reservation = query.uniqueResult();

            transaction.commit();
            return  reservation;
        } catch (HibernateException e) {
            // Rollback transaction on error
            transaction.rollback();
            e.printStackTrace();
            return null;
        } finally {
            // Close session
            session.close();
        }
    }

    @Override
    public ArrayList<Reservation> getAll() throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Reservation ");

        ArrayList<Reservation> list = (ArrayList<Reservation>) query.list();

        transaction.commit();
        session.close();

        return list;
    }

    @Override
    public boolean exist(String reservationID ) throws SQLException{
       /* ResultSet rst = SQLUtil.execute("SELECT reservationID FROM reservation WHERE reservationID=?",reservationID);
        return rst.next();*/
        return false;
    }

    @Override
    public boolean save(Reservation entity) throws SQLException{
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("insert into Reservation (reservationID,BorrowDate,dueDate,bookReturnDate,user,book) select :reservationID, :BorrowDate,:dueDate,:bookReturnDate,:user,:book");
        query.setParameter("reservationID", entity.getReservationID());
        query.setParameter("BorrowDate", entity.getBorrowDate());
        query.setParameter("dueDate",entity.getDueDate());
        query.setParameter("bookReturnDate",entity.getBookReturnDate());
        query.setParameter("user", entity.getUser());
        query.setParameter("book", entity.getBook());


        int i = query.executeUpdate();

        transaction.commit();
        session.close();

        return (i==1? true:false);
    }
}
