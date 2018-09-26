package Controller.Database;

import Model.Database.Appointment;
import Model.Database.DailyDoctorScheduleTimeLineBuilder;
import Model.Database.DailyTimeLineFacade;
import Model.Database.DoctorEvent;
import Model.Database.RecurringDoctorSchedule;
import Model.Database.User;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLController extends DatabaseController {

    protected final String URL_MYSQL = "jdbc:mysql://localhost/" + DATABASE_NAME + "?autoReconnect=true&useSSL=false";
    private DBUser dbUser;
    private DBRecurringDoctorSchedule dBRecurringDoctorSchedule;
    private DBAppointment dBAppointment;
    private DBDoctorEvent dBDoctorEvent;

    public MySQLController(String DATABASE_NAME, String USERNAME, String PASSWORD) {
        super(DATABASE_NAME, USERNAME, PASSWORD);
        this.connect();
        dbUser = new DBUser(cnt);
    }

    public MySQLController() {
        super();
        this.connect();
        this.dbUser = new DBUser(cnt);
        this.dBRecurringDoctorSchedule = new DBRecurringDoctorSchedule(cnt);
        this.dBAppointment = new DBAppointment(cnt);
        this.dBDoctorEvent = new DBDoctorEvent(cnt);
    }

    @Override
    public User login(String username, String password) throws Exception {
        User user = dbUser.getUser(username);
        if (dbUser.checkUserPassword(user, password)) {
            return user;
        } else {
            throw new Exception("Password didn't match!");
        }
    }

    @Override
    public boolean connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");     //Loading class
//            Class.forName("com.mysql.cj.jdbc.Driver");  // might not have. 
            cnt = DriverManager.getConnection(URL_MYSQL, USERNAME, PASSWORD);
            System.out.println("Connect successfull");
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MySQLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<RecurringDoctorSchedule> findDoctorSchedule(User user) throws SQLException {
        return this.dBRecurringDoctorSchedule.getRecurringDoctorSchedulesByUser(user);
    }

    @Override
    public List<DoctorEvent> findDoctorEvent(User user) throws SQLException {
        return this.dBDoctorEvent.getDoctorEventByUser(user);
    }

    @Override
    public List<Appointment> findAppointment(User user) throws SQLException {
        switch (user.getType()) {
            case "doctor":
                return this.dBAppointment.getAppointmentsByDoctorUsername(user.getUsername());
            case "client":
                return this.dBAppointment.getAppointmentsByClientUsername(user.getUsername());
            default:
                return this.dBAppointment.getAllAppointments();
        }
    }

    /**
     *
     * @param doctorSchedule
     * @return
     * @throws SQLException
     * @throws Exception
     */
    @Override
    public boolean editDoctorSchedule(RecurringDoctorSchedule doctorSchedule) throws SQLException, Exception {
        List<RecurringDoctorSchedule> recurringDoctorSchedules = this.dBRecurringDoctorSchedule.getAllRecurringDoctorSchedules();
        boolean isConflict = false;
        for (RecurringDoctorSchedule recurringDoctorSchedule : recurringDoctorSchedules) {
            if (recurringDoctorSchedule.isConflict(doctorSchedule) && recurringDoctorSchedule.getId() != doctorSchedule.getId()) {
                isConflict = true;
                break;
            }
        }
        if (!isConflict) {
            return this.dBRecurringDoctorSchedule.editDoctorSchedule(doctorSchedule);
        } else {
            throw new Exception("The edited schedule is conflict with other schedule");
        }
    }

    @Override
    public boolean createAppointment(Appointment newAppointment) throws SQLException, Exception { // add , Exception
        List<Appointment> appointments = this.dBAppointment.getAllAppointments();
        boolean isConflict = false;
        for (Appointment appointment : appointments) {
            if (appointment.isConflict(newAppointment)) {
                isConflict = true;
                break;
            }
        }
        if (!isConflict) {
            DailyDoctorScheduleTimeLineBuilder dailyDoctorScheduleTimeLineBuilder = new DailyDoctorScheduleTimeLineBuilder();
            User doctor = this.dbUser.getUser(newAppointment.getDoctorUsername());
            DateFormat df = new SimpleDateFormat("EEEEEEEEE");
            List<RecurringDoctorSchedule> doctorSchedules = this.dBRecurringDoctorSchedule.getRecurringDoctorSchedulesByUserAtDay(doctor, df.format(newAppointment.getDate()));
            List<DoctorEvent> doctorEvents = this.dBDoctorEvent.getDoctorEventByUserAtDate(doctor, newAppointment.getDate());
            dailyDoctorScheduleTimeLineBuilder.addRecurringDoctorSchedule(doctorSchedules.toArray(new RecurringDoctorSchedule[doctorSchedules.size()]));
            dailyDoctorScheduleTimeLineBuilder.addDoctorEvent(doctorEvents.toArray(new DoctorEvent[doctorEvents.size()]));
            DailyTimeLineFacade dailyTimeLineFacade = new DailyTimeLineFacade(
                    null,
                    dailyDoctorScheduleTimeLineBuilder.generateRecurringDoctorScheduleTimeLine(),
                    dailyDoctorScheduleTimeLineBuilder.generateDoctorEventTimeLine()
            );
            boolean available = dailyTimeLineFacade.isSlotAvailable(newAppointment);
            if (available) {
                return this.dBAppointment.createAppointment(newAppointment);
            } else {
                throw new Exception("Doctor is not available at the given time!");
            }
        } else {
            throw new Exception("The inputted appointment slot is conflicted with another appointment");
        }
    }

    @Override
    public boolean createDoctorEvent(DoctorEvent newDoctorEvent) throws SQLException, Exception {
        List<DoctorEvent> doctorEvents = this.dBDoctorEvent.getAllDoctorEvent();
        boolean isConflict = false;
        for (DoctorEvent doctorEvent : doctorEvents) {
            if (doctorEvent.isConflict(newDoctorEvent)) {
                isConflict = true;
                break;
            }
        }
        if (!isConflict) {
            return this.dBDoctorEvent.createDoctorEvent(newDoctorEvent);
        } else {
            throw new Exception("The edited schedule is conflict with other schedule");
        }
    }

    @Override
    public boolean createDoctorSchedule(RecurringDoctorSchedule doctorSchedule) throws SQLException, Exception {
        List<RecurringDoctorSchedule> recurringDoctorSchedules = this.dBRecurringDoctorSchedule.getAllRecurringDoctorSchedules();
        boolean isConflict = false;
        for (RecurringDoctorSchedule recurringDoctorSchedule : recurringDoctorSchedules) {
            if (recurringDoctorSchedule.isConflict(doctorSchedule)) {
                isConflict = true;
                break;
            }
        }
        if (!isConflict) {
            return this.dBRecurringDoctorSchedule.createRecurringDoctorSchedule(doctorSchedule);
        } else {
            throw new Exception("The new schedule is conflict with other schedule");
        }
    }
    
    @Override
    public boolean editAppointment(Appointment newAppointment) throws SQLException, Exception{ // uhm change this to check whether it conflicts with other shit
        List<Appointment> appointments = this.dBAppointment.getAllAppointments();
        boolean isConflict = false;
        for(Appointment appointment : appointments){
            if(appointment.isConflict(newAppointment)&& appointment.getId() != newAppointment.getId()){
                isConflict = true;
                break;
            };
        }
        if (!isConflict){
            DailyDoctorScheduleTimeLineBuilder dailyDoctorScheduleTimeLineBuilder = new DailyDoctorScheduleTimeLineBuilder();
            User doctor = this.dbUser.getUser(newAppointment.getDoctorUsername());
            DateFormat df = new SimpleDateFormat("EEEEEEEEE");
            List<RecurringDoctorSchedule> doctorSchedules = this.dBRecurringDoctorSchedule.getRecurringDoctorSchedulesByUserAtDay(doctor, df.format(newAppointment.getDate()));
            List<DoctorEvent> doctorEvents = this.dBDoctorEvent.getDoctorEventByUserAtDate(doctor, newAppointment.getDate());
            dailyDoctorScheduleTimeLineBuilder.addRecurringDoctorSchedule(doctorSchedules.toArray(new RecurringDoctorSchedule[doctorSchedules.size()]));
            dailyDoctorScheduleTimeLineBuilder.addDoctorEvent(doctorEvents.toArray(new DoctorEvent[doctorEvents.size()]));
            DailyTimeLineFacade dailyTimeLineFacade = new DailyTimeLineFacade(
                    null,
                    dailyDoctorScheduleTimeLineBuilder.generateRecurringDoctorScheduleTimeLine(),
                    dailyDoctorScheduleTimeLineBuilder.generateDoctorEventTimeLine()
            );
            boolean available = dailyTimeLineFacade.isSlotAvailable(newAppointment);
            if (available) {
                return this.dBAppointment.editAppointment(newAppointment);
            } else {
                throw new Exception("Doctor is not available at the given time!");
            }
        } else {
            throw new Exception("The new appointment is conflicted with another");
        }
        
    }

    @Override
    public boolean deleteRecurringDoctorSchedule(RecurringDoctorSchedule doctorSchedule) throws SQLException {
        return this.dBRecurringDoctorSchedule.deleteRecurringDoctorSchedule(doctorSchedule);
    }

    @Override
    public List<RecurringDoctorSchedule> findDoctorSchedule(User user, String day) throws SQLException {
        return this.dBRecurringDoctorSchedule.getRecurringDoctorSchedulesByUserAtDay(user, day);
    }

    @Override
    public List<DoctorEvent> findDoctorEvent(User user, Date date) throws SQLException {
        return this.dBDoctorEvent.getDoctorEventByUserAtDate(user, date);
    }

    @Override
    public List<Appointment> findAppointment(User user, Date date) throws SQLException {
        if (user.getType().equals("secretary")) {
            return this.dBAppointment.getAppointmentsByDate(date);
        } else {
            return this.dBAppointment.getAppointmentByUserAtDate(user, date);
        }
    }

    @Override
    public List<User> findUserByType(String type) throws SQLException {
        return this.dbUser.getUserByType(type);
    }

    @Override
    public boolean deleteAppointment(Appointment appointment) throws SQLException {
        return this.dBAppointment.deleteAppointment(appointment);
    }

    @Override
    public List<RecurringDoctorSchedule> findDoctorSchedule(String day) throws SQLException {
        return this.dBRecurringDoctorSchedule.getRecurringDoctorSchedulesAtDay(day);
    }

    @Override
    public User findUserByUsername(String username) throws SQLException {
        return this.dbUser.getUser(username);
    }

    @Override
    public List<Appointment> findAllAppointment() throws SQLException, Exception {
        return this.dBAppointment.getAllAppointments();
    }

    @Override
    public List<DoctorEvent> findDoctorEvent(Date date) throws SQLException, Exception {
        return this.dBDoctorEvent.getDoctorEventAtDate(date);
    }

}
