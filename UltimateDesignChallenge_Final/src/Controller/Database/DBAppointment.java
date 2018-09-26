package Controller.Database;

import Model.Database.Appointment;
import Model.Database.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class DBAppointment {

    private Connection cnt;

    protected PreparedStatement ps;
    protected ResultSet rs;
    protected Statement st;

    public DBAppointment(Connection cnt) {
        this.cnt = cnt;
    }

    public List<Appointment> getAllAppointments() throws SQLException {
        String sql = "SELECT * FROM `appointmenttbl`";
        ps = cnt.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<Appointment> result = new LinkedList<>();
        while (rs.next()) {
            Appointment newAppointment = new Appointment(
                    rs.getString("client_username"),
                    rs.getString("doctor_username"),
                    rs.getDate("date"),
                    rs.getTime("time_start"),
                    rs.getTime("time_end"),
                    rs.getBoolean("recurring")
            );
            newAppointment.setId(rs.getInt("appointment_id"));
            result.add(newAppointment);
        }
        return result;
    }

    public List<Appointment> getAppointmentsByDate(Date date) throws SQLException {
        String sql = "SELECT * FROM `appointmenttbl` WHERE `date` = ?";
        ps = cnt.prepareStatement(sql);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ps.setString(1, dateFormat.format(date));
        ResultSet rs = ps.executeQuery();
        List<Appointment> result = new LinkedList<>();
        while (rs.next()) {
            Appointment newAppointment = new Appointment(
                    rs.getString("client_username"),
                    rs.getString("doctor_username"),
                    rs.getDate("date"),
                    rs.getTime("time_start"),
                    rs.getTime("time_end"),
                    rs.getBoolean("recurring")
            );
            newAppointment.setId(rs.getInt("appointment_id"));
            result.add(newAppointment);
        }
        return result;
    }
    
    public List<Appointment> getAppointmentsByDoctorUsername(String doctor_username) throws SQLException {
        String sql = "SELECT * FROM `appointmenttbl` WHERE `doctor_username` LIKE ?";
        ps = cnt.prepareStatement(sql);
        ps.setString(1, doctor_username);
        ResultSet rs = ps.executeQuery();
        List<Appointment> result = new LinkedList<>();
        while (rs.next()) {
            Appointment newAppointment = new Appointment(
                    rs.getString("client_username"),
                    rs.getString("doctor_username"),
                    rs.getDate("date"),
                    rs.getTime("time_start"),
                    rs.getTime("time_end"),
                    rs.getBoolean("recurring")
            );
            newAppointment.setId(rs.getInt("appointment_id"));
            result.add(newAppointment);
        }
        return result;
    }
    
    public List<Appointment> getAppointmentsByClientUsername(String client) throws SQLException {
        String sql = "SELECT * FROM `appointmenttbl` WHERE `client_username` LIKE ?";
        ps = cnt.prepareStatement(sql);
        ps.setString(1, client);
        ResultSet rs = ps.executeQuery();
        List<Appointment> result = new LinkedList<>();
        while (rs.next()) {
            Appointment newAppointment = new Appointment(
                    rs.getString("client_username"),
                    rs.getString("doctor_username"),
                    rs.getDate("date"),
                    rs.getTime("time_start"),
                    rs.getTime("time_end"),
                    rs.getBoolean("recurring")
            );
            newAppointment.setId(rs.getInt("appointment_id"));
            result.add(newAppointment);
        }
        return result;
    }
    
    public List<Appointment> getAppointmentByUserAtDate(User user, Date date) throws SQLException {
        String sql;
        if(user.getType().equals("doctor"))
           sql = "SELECT * FROM `appointmenttbl` WHERE `doctor_username` LIKE ? AND `date` LIKE ?"; 
        else sql = "SELECT * FROM `appointmenttbl` WHERE `client_username` LIKE ? AND `date` LIKE ?";
        ps = cnt.prepareStatement(sql);
        ps.setString(1, user.getUsername());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        ps.setString(2, df.format(date));
        ResultSet rs = ps.executeQuery();
        List<Appointment> result = new LinkedList<>();
        while (rs.next()) {
            Appointment appointment = new Appointment(
                    rs.getString("client_username"),
                    rs.getString("doctor_username"),
                    rs.getDate("date"),
                    rs.getTime("time_start"),
                    rs.getTime("time_end"),
                    rs.getBoolean("recurring")
            );
            appointment.setId(rs.getInt("appointment_id"));
            result.add(appointment);
        }
        return result;
    } 
    
    public boolean createAppointment(Appointment appointment) throws SQLException {
        String sql = "INSERT INTO `appointmenttbl` (`client_username`, `doctor_username`,`date`,`time_start`, `time_end`, `recurring`) VALUES (?, ?, ?, ?, ?, ?)";
        ps = cnt.prepareStatement(sql);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        ps.setString(1, appointment.getClientUsername());
        ps.setString(2, appointment.getDoctorUsername());
        ps.setString(3, df.format(appointment.getDate()));
        ps.setTime(4, appointment.getTime_start());
        ps.setTime(5, appointment.getTime_end());
        ps.setBoolean(6, appointment.isRecurring());
        return ps.executeUpdate() > 0;
    }
    
    public boolean deleteAppointment(Appointment appointment) throws SQLException{
        String sql = "DELETE FROM `appointmenttbl` WHERE `appointmenttbl`.`appointment_id` = ?";
        ps = cnt.prepareStatement(sql);
        ps.setInt(1, appointment.getId());
        return ps.executeUpdate() > 0;
    }
    
    public boolean editAppointment(Appointment appointment) throws SQLException{
        String sql = "UPDATE `appointmenttbl` SET `client_username` = ?, `doctor_username` = ?, `date` = ?, `time_start` = ?, `time_end`, `recurring` = ? WHERE `appointmenttbl`.`appointment_id` = ?";
        ps = cnt.prepareStatement(sql);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        
        ps.setString(1, appointment.getClientUsername());
        ps.setString(2, appointment.getDoctorUsername());
        ps.setString(3, df.format(appointment.getDate()));
        ps.setTime(4, appointment.getTime_start());
        ps.setTime(5, appointment.getTime_end());
        ps.setBoolean(6, appointment.isRecurring());
        ps.setInt(7, appointment.getId());
        return ps.executeUpdate() > 0;
    }
}
