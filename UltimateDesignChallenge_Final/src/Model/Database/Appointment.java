package Model.Database;

import java.awt.Color;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Appointment {

    private int id;
    private String doctorUsername;
    private String clientUsername;
    private Date date;
    private Time time_start;
    private Time time_end;
    private boolean recurring;
    private Date date_start;
    private Date date_end;
    private Color color = Color.ORANGE;

    public void setColor(Color color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Appointment(String clientUsername, String doctorUsername, Date date, Time time_start, Time time_end, boolean recurring) {
        this.doctorUsername = doctorUsername;
        this.clientUsername = clientUsername;
        this.date = date;
        this.time_start = time_start;
        this.time_end = time_end;
        this.recurring = recurring;
        convertToDates();
    }

    public boolean isConflict(Appointment appointment) {
        if (this.recurring || appointment.isRecurring()) {

            DateFormat dayDateFormat = new SimpleDateFormat("EEEEEEEEE");
            boolean isSameday = dayDateFormat.format(this.date).equals(dayDateFormat.format(appointment.getDate()));
            if (isSameday) {
                return checkClash(this.time_start, this.time_end, appointment.time_start, appointment.time_end);
            } else {
                return false;
            }
        } else {
            //TODO Check if its recurring
            return checkClash(this.date_start, this.date_end, appointment.date_start, appointment.date_end);
        }
    }

    private boolean checkClash(Date dateStartA, Date dateEndA, Date dateStartB, Date dateEndB) {
        boolean isClash = (dateStartA.compareTo(dateEndB) < 0) && (dateEndA.compareTo(dateStartB) > 0);
        boolean isSame = (dateStartA.compareTo(dateStartB) == 0) && (dateEndA.compareTo(dateEndB) == 0);
        if (isSame) {
            return true;
        } else {
            return isClash;
        }
    }

    private void convertToDates() {
        try {
            Calendar calendar = Calendar.getInstance();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            DateFormat dateFormatDate = new SimpleDateFormat("yyyy-MM-dd");
            this.date_start = dateFormat.parse(dateFormatDate.format(date) + " " + time_start.toString());
            this.date_end = dateFormat.parse(dateFormatDate.format(date) + " " + time_end.toString());
        } catch (ParseException ex) {
            Logger.getLogger(Appointment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return "Appointment{" + "doctorUsername=" + doctorUsername + ", clientUsername=" + clientUsername + ", date=" + date + ", time_start=" + time_start + ", time_end=" + time_end + ", recurring=" + recurring + ", date_start=" + date_start + ", date_end=" + date_end + '}';
    }

    public Date getDate_start() {
        return date_start;
    }

    public Date getDate_end() {
        return date_end;
    }

    public String getDoctorUsername() {
        return doctorUsername;
    }

    public void setDoctorUsername(String doctorUsername) {
        this.doctorUsername = doctorUsername;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
        convertToDates();
    }

    public Time getTime_start() {
        return time_start;
    }

    public void setTime_start(Time time_start) {
        this.time_start = time_start;
        convertToDates();
    }

    public Time getTime_end() {
        return time_end;
    }

    public void setTime_end(Time time_end) {
        this.time_end = time_end;
        convertToDates();
    }

    public boolean isRecurring() {
        return recurring;
    }

    public void setRecurring(boolean recurring) {
        this.recurring = recurring;
    }

    public Color getColor() {
        return this.color;
    }
}
