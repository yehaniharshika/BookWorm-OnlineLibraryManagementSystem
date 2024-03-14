package lk.ijse.config;


import lk.ijse.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryConfiguration {
    private  static  FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration(){
        Configuration configuration = new Configuration().configure()
                .addAnnotatedClass(Admin.class)
                .addAnnotatedClass(LibraryBranch.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(Reservation.class);
        //build the session factory
        sessionFactory = configuration.buildSessionFactory();
    }

    public  static  FactoryConfiguration getInstance(){
        return factoryConfiguration == null ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }

    //get session,for this make SessionFactory variable
    public Session getSession(){
        return sessionFactory.openSession();
    }


}
