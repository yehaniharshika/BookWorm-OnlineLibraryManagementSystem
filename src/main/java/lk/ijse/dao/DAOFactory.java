package lk.ijse.dao;

import lk.ijse.dao.custom.impl.AdminSignupDAOImpl;
import lk.ijse.dao.custom.impl.BookDAOImpl;
import lk.ijse.dao.custom.impl.LibraryBranchDAOImpl;
import lk.ijse.dao.custom.impl.UserSignupDAOImpl;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory(){

    }

    public static DAOFactory getDaoFactory(){
        return  daoFactory == null? daoFactory = new DAOFactory():daoFactory;
    }

    public enum DAOTypes{
        USER,ADMIN,LIBRARY_BRANCH,BOOK,RESERVATION
    }

    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case USER:
                return new UserSignupDAOImpl();

            case ADMIN:
                return new AdminSignupDAOImpl();
            case LIBRARY_BRANCH:
                return new LibraryBranchDAOImpl();
            case BOOK:
                //return new BookDAOImpl();
            default:
                return null;
        }
    }
}
