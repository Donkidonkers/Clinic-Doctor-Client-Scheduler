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

public class DoctorEvent extends DoctorSchedule {

    private Date date;
    private Date date_start;
    private Date date_end;
    private boolean is_available;
    private Color color;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isAvailable() {
        return is_available;
    }

    public void setAvailable(boolean is_available) {
        this.is_available = is_available;
    }

    public DoctorEvent(String doctorUsername, Date date, Time time_start, Time time_end, boolean is_available) {
        super(doctorUsername, time_start, time_end);
        this.date = date;
        this.is_available = is_available;
        convertToDates();
    }

    public boolean isConflict(DoctorEvent doctorEvent) {
        return checkClash(this.date_start, this.date_end, doctorEvent.date_start, doctorEvent.date_end);
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
            this.date_start = dateFormat.parse(dateFormatDate.format(date) + " " + getTime_start().toString());
            this.date_end = dateFormat.parse(dateFormatDate.format(date) + " " + getTime_end().toString()); 
        } catch (ParseException ex) {
            Logger.getLogger(Appointment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Date getDate() {
        return date;
    }

    public Date getDate_start() {
        return date_start;
    }

    public Date getDate_end() {
        return date_end;
    }

}
