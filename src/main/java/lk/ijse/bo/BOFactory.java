package lk.ijse.bo;

import lk.ijse.bo.custom.impl.*;
import lk.ijse.dao.custom.impl.AdminSignupDAOImpl;
import lk.ijse.dao.custom.impl.BookDAOImpl;
import lk.ijse.dao.custom.impl.ReservationDAOImpl;

public class BOFactory {

    private static  BOFactory boFactory;

    private BOFactory(){

    }

    public static BOFactory getBoFactory(){
        return (boFactory ==null) ? boFactory=new BOFactory(): boFactory;
    }
    public enum BOTypes{
        ADMIN,BOOK,RESERVATION,LIBRARY_BRANCH,USER,QUERY
    }

    public SuperBO getBo(BOTypes boTypes){
        switch (boTypes){
            case ADMIN:
                return  new AdminSignupBOImpl();
            case BOOK:
                return  new BookBOImpl();
            case RESERVATION:
                return  new PlaceReservationBoImpl();
            case LIBRARY_BRANCH:
                return new LibraryBranchBOImpl();
            case USER:
                return new UserSignupBOImpl();
            case QUERY:
                return new QueryBOImpl();
        }
        return null;
    }

}
