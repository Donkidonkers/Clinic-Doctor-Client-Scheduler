package Controller.Database;

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

public class DBRecurringDoctorSchedule {

    private Connection cnt;

    protected PreparedStatement ps;
    protected ResultSet rs;
    protected Statement st;

    public DBRecurringDoctorSchedule(Connection cnt) {
        this.cnt = cnt;
    }

    public List<RecurringDoctorSchedule> getAllRecurringDoctorSchedules() throws SQLException {
        String sql = "SELECT * FROM `schedule_recurringtbl`";
        ps = cnt.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        List<RecurringDoctorSchedule> result = new LinkedList<>();
        while (rs.next()) {
            RecurringDoctorSchedule recurringDoctorSchedule = new RecurringDoctorSchedule(
                    rs.getString("doctor_username"),
                    rs.getString("day"),
                    rs.getTime("time_start"),
                    rs.getTime("time_end")
            );
            recurringDoctorSchedule.setId(rs.getInt("schedule_id"));
            result.add(recurringDoctorSchedule);
        }
        return result;
    }

    public List<RecurringDoctorSchedule> getRecurringDoctorSchedulesByUser(User user) throws SQLException {
        String sql = "SELECT * FROM `schedule_recurringtbl` WHERE `doctor_username` LIKE ?";
        ps = cnt.prepareStatement(sql);
        ps.setString(1, user.getUsername());
        ResultSet rs = ps.executeQuery();
        List<RecurringDoctorSchedule> result = new LinkedList<>();
        while (rs.next()) {
            RecurringDoctorSchedule recurringDoctorSchedule = new RecurringDoctorSchedule(
                    rs.getString("doctor_username"),
                    rs.getString("day"),
                    rs.getTime("time_start"),
                    rs.getTime("time_end")
            );
            recurringDoctorSchedule.setId(rs.getInt("schedule_id"));
            result.add(recurringDoctorSchedule);
        }
        return result;
    }

    public List<RecurringDoctorSchedule> getRecurringDoctorSchedulesByUserAtDay(User user, String day) throws SQLException {
        String sql = "SELECT * FROM `schedule_recurringtbl` WHERE `doctor_username` LIKE ? AND `day` LIKE ?";
        ps = cnt.prepareStatement(sql);
        ps.setString(1, user.getUsername());
        ps.setString(2, day);
        ResultSet rs = ps.executeQuery();
        List<RecurringDoctorSchedule> result = new LinkedList<>();
        while (rs.next()) {
            RecurringDoctorSchedule recurringDoctorSchedule = new RecurringDoctorSchedule(
                    rs.getString("doctor_username"),
                    rs.getString("day"),
                    rs.getTime("time_start"),
                    rs.getTime("time_end")
            );
            recurringDoctorSchedule.setId(rs.getInt("schedule_id"));
            result.add(recurringDoctorSchedule);
        }
        return result;
    }

    public boolean createRecurringDoctorSchedule(RecurringDoctorSchedule recurringDoctorSchedule) throws SQLException {
        String sql = "INSERT INTO `schedule_recurringtbl` (`schedule_id`, `doctor_username`, `day`, `time_start`, `time_end`) VALUES (NULL, ?, ?, ?, ?);";
        ps = cnt.prepareStatement(sql);
        ps.setString(1, recurringDoctorSchedule.getDoctorUsername());
        ps.setString(2, recurringDoctorSchedule.getDay());
        ps.setTime(3, recurringDoctorSchedule.getTime_start());
        ps.setTime(4, recurringDoctorSchedule.getTime_end());
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

    public List<RecurringDoctorSchedule> getRecurringDoctorSchedulesAtDay(String day) throws SQLException {
        String sql = "SELECT * FROM `schedule_recurringtbl` WHERE `day` LIKE ? ORDER BY `doctor_username` ASC";
        ps = cnt.prepareStatement(sql);
        ps.setString(1, day);
        ResultSet rs = ps.executeQuery();
        List<RecurringDoctorSchedule> result = new LinkedList<>();
        while (rs.next()) {
            RecurringDoctorSchedule recurringDoctorSchedule = new RecurringDoctorSchedule(
                    rs.getString("doctor_username"),
                    rs.getString("day"),
                    rs.getTime("time_start"),
                    rs.getTime("time_end")
            );
            recurringDoctorSchedule.setId(rs.getInt("schedule_id"));
            result.add(recurringDoctorSchedule);
        }
        return result;
    }

}
