package lk.ijse.dao.custom.impl;

import lk.ijse.dao.SQLUtil;
import lk.ijse.dao.custom.BookDAO;
import lk.ijse.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookDAOImpl implements BookDAO {
    @Override
    public String getCount() throws SQLException {
        return "";
    }

    @Override
    public String generateNextId() throws SQLException {
        return "";
    }

    @Override
    public boolean save(Book entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO book VALUES(?,?,?,?,?,?)",
                entity.getBookID(),
                entity.getBookName(),
                entity.getAuthorName(),
                entity.getBookGenre(),
                entity.getQtyOnHand(),
                entity.getBranchID()
        );
    }

    @Override
    public boolean update(Book entity) throws SQLException {
        return SQLUtil.execute("UPDATE book SET bookName=?,authorName=?,bookGenre=?,qtyOnHand=?,branchID=? WHERE bookID=?",
                entity.getBookName(),
                entity.getAuthorName(),
                entity.getBookGenre(),
                entity.getQtyOnHand(),
                entity.getBranchID(),
                entity.getBookID()
        );
    }

    @Override
    public boolean delete(String bookID) throws SQLException {
        return SQLUtil.execute("DELETE FROM book WHERE bookID=?",bookID);
    }

    @Override
    public Book search(String bookID) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM book WHERE bookID=?",bookID);

        Book entity = null;
        if (resultSet.next()){
            entity=new Book(
                    resultSet.getString("bookID"),
                    resultSet.getString("bookName"),
                    resultSet.getString("authorName"),
                    resultSet.getString("bookGenre"),
                    resultSet.getInt("qtyOnHand"),
                    resultSet.getString("branchID")
            );
        }
        return entity;
    }

    @Override
    public ArrayList<Book> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM book");

        ArrayList<Book> bookList = new ArrayList<>();

        while (resultSet.next()){
            bookList.add(new Book(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getString(6)
            ));
        }
        return bookList;
    }
}
