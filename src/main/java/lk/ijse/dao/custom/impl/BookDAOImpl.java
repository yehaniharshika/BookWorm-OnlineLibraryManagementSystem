package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.BookDAO;
import lk.ijse.dto.AdminSignupDTO;
import lk.ijse.entity.Admin;
import lk.ijse.entity.Book;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO{

    @Override
    public String getCount() throws SQLException {
        return "";
    }

    @Override
    public String generateNextId() throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("select bookID from Book order by bookID desc limit 1");
        String id = (String) query.uniqueResult();

        transaction.commit();
        session.close();

        return splitId(id);
    }

    private String splitId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("B0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "B00"+id;
            }else {
                if (length < 3){
                    return "B0"+id;
                }else {
                    return "B"+id;
                }
            }
        }
        return "B001";
    }

    @Override
    public boolean save(Book entity) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("insert into Book (bookID,bookName,authorName,bookGenre,qtyOnHand,availability,libraryBranch) select :bookID, :bookName,:authorName,:bookGenre,:qtyOnHand,:availability,:libraryBranch");
        query.setParameter("bookID", entity.getBookID());
        query.setParameter("bookName", entity.getBookName());
        query.setParameter("authorName", entity.getAuthorName());
        query.setParameter("bookGenre", entity.getBookGenre());
        query.setParameter("qtyOnHand", entity.getQtyOnHand());
        query.setParameter("availability", entity.getAvailability());
        query.setParameter("libraryBranch", entity.getLibraryBranch());

        int i = query.executeUpdate();

        transaction.commit();
        session.close();

        return (i==1? true:false);
    }

    @Override
    public boolean update(Book entity) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("update  Book set bookName=?1,authorName=?2,bookGenre=?3,qtyOnHand=?4,availability=?5,libraryBranch=?6 where bookID=?7");
        query.setParameter(1,entity.getBookName());
        query.setParameter(2,entity.getAuthorName());
        query.setParameter(3,entity.getBookGenre());
        query.setParameter(4,entity.getQtyOnHand());
        query.setParameter(5,entity.getAvailability());
        query.setParameter(6,entity.getLibraryBranch());
        query.setParameter(7,entity.getBookID());
        int i = query.executeUpdate();

        transaction.commit();
        session.close();
        return (i==1? true:false);
    }

    @Override
    public boolean delete(String bookID) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("DELETE FROM Book WHERE bookID = ?1");
        query.setParameter(1, bookID);
        int i = query.executeUpdate();

        transaction.commit();
        session.close();

        return (i==1 ? true : false);
    }

    @Override
    public Book search(String bookID) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query<Book> query = session.createQuery("FROM Book WHERE bookID =?1", Book.class);
            query.setParameter(1, bookID);
            Book book = query.uniqueResult();

            transaction.commit();
            return  book;
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
    public ArrayList<Book> getAll() throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Book ");

        ArrayList<Book> list = (ArrayList<Book>) query.list();

        transaction.commit();
        session.close();

        return list;
    }

    @Override
    public Book getBook(String bookID) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM Book WHERE bookID=?1");
        query.setParameter(1, bookID);
        List<Book> bookList = query.list();
        Book book = null;

        for (Book book1 : bookList) {
            book = new Book(
                    book1.getBookID(),
                    book1.getBookName(),
                    book1.getAuthorName(),
                    book1.getBookGenre(),
                    book1.getQtyOnHand(),
                    book1.getAvailability(),
                    book1.getLibraryBranch()
            );
        }
        transaction.commit();
        session.close();
        return book;
    }
}
