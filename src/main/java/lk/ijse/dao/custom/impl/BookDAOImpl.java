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
        ResultSet resultSet = SQLUtil.execute("SELECT bookID FROM book ORDER BY bookID DESC LIMIT 1;");

        if (resultSet.next()){
            String id = resultSet.getString("bookID");
            int newItemId = Integer.parseInt(id.replace("B00-", "")) + 1;
            return String.format("B00-%03d", newItemId);
        }else {
            return "B00-001";
        }
    }


    @Override
    public boolean save(Book entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO book VALUES(?,?,?,?,?,?,?)",
                entity.getBookID(),
                entity.getBookName(),
                entity.getAuthorName(),
                entity.getBookGenre(),
                entity.getQtyOnHand(),
                entity.getAvailability(),
                entity.getBranchID()
        );
    }

    @Override
    public boolean update(Book entity) throws SQLException {
        return SQLUtil.execute("UPDATE book SET bookName=?,authorName=?,bookGenre=?,qtyOnHand=?, availabilityStatus=?,branchID=? WHERE bookID=?",
                entity.getBookName(),
                entity.getAuthorName(),
                entity.getBookGenre(),
                entity.getQtyOnHand(),
                entity.getAvailability(),
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
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM book WHERE bookID=?", bookID);

        Book entity = null;
        if (resultSet.next()){
            entity=new Book(
                    resultSet.getString("bookID"),
                    resultSet.getString("bookName"),
                    resultSet.getString("authorName"),
                    resultSet.getString("bookGenre"),
                    resultSet.getInt("qtyOnHand"),
                    resultSet.getString("availabilityStatus"),
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
                    resultSet.getString(6),
                    resultSet.getString(7)
            ));
        }
        return bookList;
    }

    @Override
    public boolean updateQtyBooks(String bookID,int qtyOnHand) throws SQLException {
        return SQLUtil.execute("UPDATE book SET qtyOnHand = qtyOnHand -  CAST(? AS SIGNED) WHERE bookID = ?",
                qtyOnHand, bookID
        );


    }
}
