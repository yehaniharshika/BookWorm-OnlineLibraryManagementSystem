package lk.ijse.dao;

import lk.ijse.dao.custom.impl.*;

public class DAOFactory {

    private  static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getDaoFactory(){
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DTOTypes{
        ADMIN,BOOK,RESERVATION,LIBRARY_BRANCH,QUERY,USER
    }

    public SuperDAO getDAO(DTOTypes dtoTypes){
        switch (dtoTypes){

            case ADMIN:
                return new AdminSignupDAOImpl();
            case BOOK:
                return new BookDAOImpl();
            case RESERVATION:
                return new ReservationDAOImpl();
            case LIBRARY_BRANCH:
                return new LibraryBranchDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            case USER:
                return new UserSignupDAOImpl();

        }
        return null;
    }
}
