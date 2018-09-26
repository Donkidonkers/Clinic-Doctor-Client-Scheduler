package Controller.Database;

import Model.Database.DoctorEvent;
import Model.Database.RecurringDoctorSchedule;
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

public class DBDoctorEvent {

    private Connection cnt;

    protected PreparedStatement ps;
    protected ResultSet rs;
    protected Statement st;

    public DBDoctorEvent(Connection cnt) {
        this.cnt = cnt;
    }

    public List<DoctorEvent> getAllDoctorEvent() throws SQLException {
        String sql = "SELECT * FROM `doctor_event`";
        ps = cnt.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<DoctorEvent> result = new LinkedList<>();
        while (rs.next()) {
            DoctorEvent doctorEvent = new DoctorEvent(
                    rs.getString("doctor_username"),
                    rs.getDate("date"),
                    rs.getTime("time_start"),
                    rs.getTime("time_end"),
                    rs.getBoolean("is_available")
            );
            result.add(doctorEvent);
        }
        return result;
    }

    public List<DoctorEvent> getDoctorEventByUserAtDate(User user, Date date) throws SQLException {
        String sql = "SELECT * FROM `doctor_event` WHERE `doctor_username` LIKE ? AND `date` LIKE ?";
        ps = cnt.prepareStatement(sql);
        ps.setString(1, user.getUsername());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        ps.setString(2, df.format(date));
        ResultSet rs = ps.executeQuery();
        List<DoctorEvent> result = new LinkedList<>();
        while (rs.next()) {
            DoctorEvent doctorEvent = new DoctorEvent(
                    rs.getString("doctor_username"),
                    rs.getDate("date"),
                    rs.getTime("time_start"),
                    rs.getTime("time_end"),
                    rs.getBoolean("is_available")
            );
            result.add(doctorEvent);
        }
        return result;
    }

    public List<DoctorEvent> getDoctorEventByUser(User user) throws SQLException {
        String sql = "SELECT * FROM `doctor_event` WHERE `doctor_username` LIKE ?";
        ps = cnt.prepareStatement(sql);
        ps.setString(1, user.getUsername());
        ResultSet rs = ps.executeQuery();
        List<DoctorEvent> result = new LinkedList<>();
        while (rs.next()) {
            DoctorEvent doctorEvent = new DoctorEvent(
                    rs.getString("doctor_username"),
                    rs.getDate("date"),
                    rs.getTime("time_start"),
                    rs.getTime("time_end"),
                    rs.getBoolean("is_available")
            );
            result.add(doctorEvent);
        }
        return result;
    }

    public boolean createDoctorEvent(DoctorEvent doctorEvent) throws SQLException {
        String sql = "INSERT INTO `doctor_event` (`doctor_username`, `date`, `time_start`, `time_end`, `is_available`) VALUES (?, ?, ?, ?, ?)";
        ps = cnt.prepareStatement(sql);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        ps.setString(1, doctorEvent.getDoctorUsername());
        ps.setString(2, df.format(doctorEvent.getDate()));
        ps.setTime(3, doctorEvent.getTime_start());
        ps.setTime(4, doctorEvent.getTime_end());
        ps.setBoolean(5, doctorEvent.isAvailable());
        return ps.executeUpdate() > 0;
    }

    public boolean deleteRecurringDoctorSchedule(RecurringDoctorSchedule doctorSchedule) throws SQLException {
        String sql = "DELETE FROM `schedule_recurringtbl` WHERE `schedule_recurringtbl`.`schedule_id` = ?";
        ps = cnt.prepareStatement(sql);
        ps.setInt(1, doctorSchedule.getId());
        return ps.executeUpdate() > 0;
    }

    public boolean editDoctorSchedule(RecurringDoctorSchedule doctorSchedule) throws SQLException {
        String sql = "UPDATE `schedule_recurringtbl` SET `doctor_username` = ?, `day` = ?, `time_start` = ?, `time_end` = ? WHERE `schedule_recurringtbl`.`schedule_id` = ?";
        ps = cnt.prepareStatement(sql);
        ps.setString(1, doctorSchedule.getDoctorUsername());
        ps.setString(2, doctorSchedule.getDay());
        ps.setTime(3, doctorSchedule.getTime_start());
        ps.setTime(4, doctorSchedule.getTime_end());
        ps.setInt(5, doctorSchedule.getId());
        return ps.executeUpdate() > 0;
    }

    public List<DoctorEvent> getDoctorEventAtDate(Date date) throws SQLException {
        String sql = "SELECT * FROM `doctor_event` WHERE `date` LIKE ?";
        ps = cnt.prepareStatement(sql);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        ps.setString(1, df.format(date));
        ResultSet rs = ps.executeQuery();
        List<DoctorEvent> result = new LinkedList<>();
        while (rs.next()) {
            DoctorEvent doctorEvent = new DoctorEvent(
                    rs.getString("doctor_username"),
                    rs.getDate("date"),
                    rs.getTime("time_start"),
                    rs.getTime("time_end"),
                    rs.getBoolean("is_available")
            );
            result.add(doctorEvent);
        }
        return result;
    }

}
