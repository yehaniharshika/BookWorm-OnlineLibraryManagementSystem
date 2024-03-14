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

        Query query = session.createQuery("select branchID from LibraryBranch order by branchID desc limit 1");
        String id = (String) query.uniqueResult();

        transaction.commit();
        session.close();

        return splitId(id);
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
    public boolean save(LibraryBranch dto) throws SQLException {
        return false;
    }

    @Override
    public boolean update(LibraryBranch dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public LibraryBranch search(String id) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<LibraryBranch> getAll() throws SQLException {
        return null;
    }
}
