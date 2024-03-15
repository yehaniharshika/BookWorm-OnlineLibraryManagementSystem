package lk.ijse.dao.custom.impl;

import lk.ijse.config.FactoryConfiguration;
import lk.ijse.dao.custom.LibraryBranchDAO;
import lk.ijse.dto.AdminSignupDTO;
import lk.ijse.entity.LibraryBranch;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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

        Query query = session.createQuery("select branchID from LibraryBranch order by branchID desc limit 1");
        String id = (String) query.uniqueResult();

        transaction.commit();
        session.close();

        return splitId(id);
    }
    @Override
    public boolean save(LibraryBranch entity) throws SQLException {
        // Generate the next branchID
        String nextBranchId = generateNextId();

        // Set the generated branchID to the entity
        entity.setBranchID(nextBranchId);

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Query query = session.createQuery("INSERT INTO LibraryBranch (branchID,branchName,location,admin) " +
                    "SELECT :branchID, :branchName, :location, :admin");
            query.setParameter("branchID", entity.getBranchID());
            query.setParameter("branchName", entity.getBranchName());
            query.setParameter("location", entity.getLocation());
            query.setParameter("admin", entity.getAdmin());


            int i = query.executeUpdate();
            /*session.save(entity);*/
            transaction.commit();
            return (i == 1);

        } catch (HibernateException e) {
            // Rollback transaction on error
            transaction.rollback();
            e.printStackTrace();
            return false;
        } finally {
            // Close session
            session.close();
        }
    }


    private String splitId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("L0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "L00"+id;
            }else {
                if (length < 3){
                    return "L0"+id;
                }else {
                    return "L"+id;
                }
            }
        }
        return "L001";
    }

    @Override
    public boolean update(LibraryBranch entity) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("update  LibraryBranch set branchName=?1,location=?2,admin=?3 where branchID=?4");
        query.setParameter(1,entity.getBranchName());
        query.setParameter(2,entity.getLocation());
        query.setParameter(3,entity.getAdmin());
        query.setParameter(4,entity.getBranchID());
        int i = query.executeUpdate();

        transaction.commit();
        session.close();
        return (i==1? true:false);
    }


    @Override
    public boolean delete(String branchID) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("DELETE FROM LibraryBranch WHERE branchID = ?1");
        query.setParameter(1, branchID);
        int i = query.executeUpdate();

        transaction.commit();
        session.close();

        return (i==1 ? true : false);
    }

    @Override
    public LibraryBranch search(String branchID) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM LibraryBranch WHERE branchID = ?1");
        query.setParameter(1, branchID);

        List<LibraryBranch> list = query.list();

        LibraryBranch branch = null;

        for (LibraryBranch branch1 : list) {
            branch = new LibraryBranch(branch1.getBranchID(),
                    branch1.getBranchName() ,
                    branch1.getLocation(),
                    branch1.getAdmin());
        }

        transaction.commit();
        session.close();

        return branch;
    }

    @Override
    public ArrayList<LibraryBranch> getAll() throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM LibraryBranch ");

        ArrayList<LibraryBranch> list = (ArrayList<LibraryBranch>) query.list();

        transaction.commit();
        session.close();

        return list;
    }


    @Override
    public LibraryBranch getLibraryBranch(String branchID) throws SQLException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("FROM LibraryBranch WHERE branchID=?1");
        query.setParameter(1, branchID);
        List<LibraryBranch> branchList = query.list();
        LibraryBranch libraryBranch = null;

        for (LibraryBranch libraryBranch1 : branchList) {
            libraryBranch = new LibraryBranch(
                    libraryBranch1.getBranchID(),
                    libraryBranch1.getBranchName(),
                    libraryBranch1.getLocation(),
                    libraryBranch1.getAdmin()

            );
        }
        transaction.commit();
        session.close();
        return libraryBranch;
    }
}
