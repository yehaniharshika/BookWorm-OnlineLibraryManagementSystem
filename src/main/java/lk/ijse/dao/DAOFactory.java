package lk.ijse.dao;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory(){

    }

    public static DAOFactory getDaoFactory(){
        return  daoFactory == null? daoFactory = new DAOFactory():daoFactory;
    }

    public enum DAOTypes{
        USER
    }

    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case USER:
                return new UserSignupDAOImpl();

            default:
                return null;
        }
    }
}