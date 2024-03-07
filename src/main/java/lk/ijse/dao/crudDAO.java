package lk.ijse.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface crudDAO<T> extends SuperDAO {
    String getCount() throws SQLException;

    String generateNextId() throws SQLException;

    boolean save(T dto) throws SQLException;

    boolean update(T dto) throws SQLException;

    boolean delete(String id) throws SQLException;

    T search(String id) throws SQLException;

    ArrayList<T> getAll() throws SQLException;
}
