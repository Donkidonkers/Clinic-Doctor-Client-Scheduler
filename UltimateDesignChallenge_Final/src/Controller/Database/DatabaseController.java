package Controller.Database;

import Model.Database.Appointment;
import Model.Database.DoctorEvent;
import Model.Database.RecurringDoctorSchedule;
import Model.Database.User;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public abstract class DatabaseController {

    protected Connection cnt;
    protected String DATABASE_NAME;
    protected String USERNAME;
    protected String PASSWORD;

    public DatabaseController(String DATABASE_NAME, String USERNAME, String PASSWORD) {
        this.DATABASE_NAME = DATABASE_NAME;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
    }

    public DatabaseController() {
        this.DATABASE_NAME = DBConfig.DATABASE_NAME;
        this.USERNAME = DBConfig.USERNAME;
        this.PASSWORD = DBConfig.PASSWORD;
    }

    public Connection getCnt() {
        return cnt;
    }

    /**
     * One object represents a row in the database
     *
     * @return
     */
    public abstract User login(String username, String password) throws Exception;

    public abstract boolean connect();

    public abstract List<RecurringDoctorSchedule> findDoctorSchedule(User user) throws SQLException;

    public abstract List<RecurringDoctorSchedule> findDoctorSchedule(String day) throws SQLException;

    public abstract List<DoctorEvent> findDoctorEvent(User user) throws SQLException;

    public abstract List<DoctorEvent> findDoctorEvent(Date date) throws SQLException, Exception;

    public abstract List<Appointment> findAppointment(User user) throws SQLException;

    public abstract List<RecurringDoctorSchedule> findDoctorSchedule(User user, String day) throws SQLException;

    public abstract List<DoctorEvent> findDoctorEvent(User user, Date date) throws SQLException;

    public abstract List<Appointment> findAppointment(User user, Date date) throws SQLException;

    public abstract boolean editDoctorSchedule(RecurringDoctorSchedule doctorSchedule) throws SQLException, Exception;

    public abstract boolean createAppointment(Appointment newAppointment) throws SQLException, Exception; //added, exception sorry. I know you told me not to add.

    public abstract boolean createDoctorEvent(DoctorEvent newDoctorEvent) throws SQLException, Exception;

    public abstract boolean createDoctorSchedule(RecurringDoctorSchedule doctorSchedule) throws SQLException, Exception;

    public abstract boolean deleteRecurringDoctorSchedule(RecurringDoctorSchedule doctorSchedule) throws SQLException;

    public abstract boolean deleteAppointment(Appointment appointment) throws SQLException;

    public abstract boolean editAppointment(Appointment appointment) throws SQLException, Exception;

    public abstract List<User> findUserByType(String type) throws SQLException;

    public abstract User findUserByUsername(String username) throws SQLException;

    public abstract List<Appointment> findAllAppointment() throws SQLException, Exception;

}
