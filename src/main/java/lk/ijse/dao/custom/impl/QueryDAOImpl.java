package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.QueryDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class QueryDAOImpl implements QueryDAO{
    @Override
    public List<Object[]> getTransaction(String user) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        NativeQuery<Object[]> nativeQuery = session.createNativeQuery("SELECT  u.firstName, bd.bookID, bk.bookName, bd.borrowDate, bd.bookReturnDate, r.userID \n" +
                "FROM BookReservationDetail bd \n" +
                "JOIN Reservation r on bd.reservationID = r.reservationID\n" +
                "JOIN Book bk \n" +
                "ON bd.bookID = bk.bookID\n" +
                "JOIN User u \n" +
                "ON  r.userID = u.userID\n" +
                "WHERE u.userID = :user");

        nativeQuery.setParameter("user",user);

        List<Object[]> tran = nativeQuery.getResultList();


        transaction.commit();
        session.close();

        return tran;
    }

    @Override
    public List<Object[]> getAllTimeOut() {
        return List.of();
    }
}
