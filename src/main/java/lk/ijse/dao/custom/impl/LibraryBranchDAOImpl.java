package lk.ijse.dao.custom.impl;

import jakarta.transaction.*;
import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.LibraryBranchDAO;
import lk.ijse.entity.LibraryBranch;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibraryBranchDAOImpl implements LibraryBranchDAO {


    @Override
    public String getCount() throws SQLException {
        return "";
    }

    @Override
    public String generateNextId() throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("SELECT branchID FROM  LibraryBranch ORDER BY branchID DESC LIMIT 1");
        String branchID = (String) query.getSingleResult();
        int newbranchID = Integer.parseInt(branchID.replace("L00-", "")) + 1;
        transaction.commit();
        session.close();
        return String.format("L00-%03d", newbranchID);


    }

    @Override
    public boolean save(LibraryBranch entity) throws SQLException {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("INSERT INTO LibraryBranch (branchID, branchName,location,admin) SELECT :branchID, :branchName ,:location,:admin");
        query.setParameter("branchID", entity.getBranchID());
        query.setParameter("branchName", entity.getBranchName());
        query.setParameter("location", entity.getLocation());
        query.setParameter("admin", entity.getAdmin());

        int i = query.executeUpdate();

        transaction.commit();
        session.close();

        return (i == 1? true:false);
    }

    /*@Override
    public boolean save(LibraryBranch entity) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = (Transaction) session.beginTransaction();

        Query query = session.createQuery("INSERT INTO libraryBranch (branchID, branchName,location,description,admin) SELECT :branchID, :branchName, :location, :description, :admin");
        query.setParameter("BranchId", entity.getBranchId());
        query.setParameter("Name", entity.getName());
        query.setParameter("admin", entity.getAdmin());

        int i = query.executeUpdate();

        transaction.commit();
        session.close();

        return (i==1 ? true : false);
        return false;
    }
*/
    @Override
    public boolean update(LibraryBranch entity) throws SQLException {
        Session session= FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();


        /*return SQLUtil.execute("UPDATE libraryBranch SET branchName=?,location=?,description=?,adminID=? WHERE branchID=?",
                entity.getBranchName(),
                entity.getLocation(),
                entity.getDescription(),
                entity.getAdminID(),
                entity.getBranchID()
        );*/
        return false;
    }

    @Override
    public boolean delete(String branchID) throws SQLException {
/*
        return SQLUtil.execute("DELETE  FROM libraryBranch WHERE branchID=?",branchID);
*/
        return false;
    }

    @Override
    public LibraryBranch search(String branchID) throws SQLException {
       /* ResultSet resultSet = SQLUtil.execute("SELECT * FROM libraryBranch WHERE branchID=?",branchID);

        LibraryBranch entity = null;

        if (resultSet.next()){
            entity=new LibraryBranch(
                    resultSet.getString("branchID"),
                    resultSet.getString("branchName"),
                    resultSet.getString("location"),
                    resultSet.getString("description"),
                    resultSet.getString("adminID")
            );
        }
        return entity;*/
        return null;
    }

    @Override
    public ArrayList<LibraryBranch> getAll() throws SQLException {

       /* ResultSet resultSet = SQLUtil.execute("SELECT * FROM libraryBranch");

        ArrayList<LibraryBranch> libraryBranchesList = new ArrayList<>();

        while (resultSet.next()){
            libraryBranchesList.add(new LibraryBranch(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        }
        return libraryBranchesList;*/

        return null;
    }
}
