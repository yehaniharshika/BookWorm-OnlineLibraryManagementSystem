package lk.ijse.dao.custom;

import lk.ijse.dao.crudDAO;
import lk.ijse.dto.ReservationDTO;
import lk.ijse.entity.Reservation;

import java.sql.SQLException;
import java.time.LocalDate;

public interface ReservationDAO extends crudDAO<Reservation> {
    boolean save(String ReservationID, LocalDate borrowDate, String userID) throws SQLException, ClassNotFoundException;
    boolean exist(String reservationID ) throws SQLException;

}
