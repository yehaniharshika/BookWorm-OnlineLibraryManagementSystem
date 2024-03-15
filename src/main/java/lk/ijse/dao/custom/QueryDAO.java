package lk.ijse.dao.custom;

import lk.ijse.dao.SuperDAO;

import java.util.List;

public interface QueryDAO  extends SuperDAO {
    public List<Object[]> getTransaction(String user)throws Exception;

    public List<Object[]> getAllTimeOut();
}
