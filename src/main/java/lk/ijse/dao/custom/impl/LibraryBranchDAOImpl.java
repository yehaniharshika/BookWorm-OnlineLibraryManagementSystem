package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.LibraryBranchDAO;
import lk.ijse.entity.LibraryBranch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LibraryBranchDAOImpl implements LibraryBranchDAO {


    @Override
    public String getCount() throws SQLException {
        return "";
    }

    @Override
    public String generateNextId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT branchID FROM libraryBranch ORDER BY branchID DESC LIMIT 1;");

        if (resultSet.next()){

            return splitLibraryBranchId(resultSet.getString("authorId"));
        }
        return splitLibraryBranchId(null);
    }

    private String splitLibraryBranchId(String currentBranchId) {
        if(currentBranchId != null) {
            String[] strings =currentBranchId.split("B0");
            int branchID = Integer.parseInt(strings[1]);
            branchID++;
            String ID = String.valueOf(branchID);
            int length = ID.length();
            if (length < 2){
                return "B00"+branchID;
            }else {
                if (length < 3){
                    return "B0"+branchID;
                }else {
                    return "B"+branchID;
                }
            }
        }
        return "B001";
    }

    @Override
    public boolean save(LibraryBranch entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO libraryBranch VALUES(?,?,?,?,?)",
                entity.getBranchID(),
                entity.getBranchName(),
                entity.getLocation(),
                entity.getDescription(),
                entity.getAdminID()
        );
    }

    @Override
    public boolean update(LibraryBranch entity) throws SQLException {
        return SQLUtil.execute("UPDATE libraryBranch SET branchName=?,location=?,description=?,adminID=? WHERE branchID=?",
                entity.getBranchName(),
                entity.getLocation(),
                entity.getDescription(),
                entity.getAdminID(),
                entity.getBranchID()
        );
    }

    @Override
    public boolean delete(String branchID) throws SQLException {
        return SQLUtil.execute("DELETE  FROM libraryBranch WHERE branchID=?",branchID);
    }

    @Override
    public LibraryBranch search(String branchID) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM libraryBranch WHERE branchID=?",branchID);

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
        return entity;
    }

    @Override
    public ArrayList<LibraryBranch> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM libraryBranch");

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
        return libraryBranchesList;
    }
}
