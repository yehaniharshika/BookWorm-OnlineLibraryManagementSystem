package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.QueryBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.QueryDAO;
import lk.ijse.dao.custom.impl.QueryDAOImpl;
import lk.ijse.dto.BookReservationDetailsDTO;
import lk.ijse.dto.TimeOutDTO;
import lk.ijse.dto.TransactionDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QueryBOImpl implements QueryBO {
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DTOTypes.QUERY);
    @Override
    public List<TransactionDTO> getTransactions(String user) throws Exception {

        List<Object[]> objects= queryDAO.getTransaction(user);

        ArrayList<TransactionDTO> list = new ArrayList<>();


        for(Object[] ob : objects){
            list.add(new TransactionDTO(
                    (String) ob[0],
                    (String) ob[1],
                    (String)ob[2],
                    (LocalDate) ob[3],
                    (String) ob[4]
            ));
        }
        return list;
    }

    @Override
    public List<TimeOutDTO> setAllTimeOut() throws Exception {
        List<Object[]> objects = queryDAO.getAllTimeOut();

        ArrayList<TimeOutDTO> trans = new ArrayList<>();


        for(Object[] ob : objects){
            trans.add(new TimeOutDTO(
                    (String) ob[0],
                    (String) ob[1],
                    (String) ob[2],
                    (LocalDate) ob[3],
                    (String) ob[4]
            ));
        }
        return trans;
    }
}
