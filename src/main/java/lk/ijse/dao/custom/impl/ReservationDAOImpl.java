package lk.ijse.dao.custom.impl;

import lk.ijse.dao.custom.ReservationDAO;
import lk.ijse.entity.Reservation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservationDAOImpl{
   /* @Override
    public String getCount() throws SQLException {
        return "";
    }

    @Override
    public String generateNextId() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT reservationID FROM reservation ORDER BY reservationID DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("reservationID");
            int newCustomerId = Integer.parseInt(id.replace("R00-", "")) + 1;
            return String.format("R00-%03d", newCustomerId);
        } else {
            return "R00-001";
        }
    }

    @Override
    public boolean save(Reservation dto) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Reservation dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public Reservation search(String id) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Reservation> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean exist(String reservationID ) throws SQLException{
        ResultSet rst = SQLUtil.execute("SELECT reservationID FROM reservation WHERE reservationID=?",reservationID);
        return rst.next();
    }

    @Override
    public boolean save(String reservationID, LocalDate borrowDate, String userID) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO reservation VALUES (?,?,?)",
               reservationID ,borrowDate,userID);
    }*/
}
