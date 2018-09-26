package Model.Database;

import java.awt.Color;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

public class RecurringDoctorSchedule extends DoctorSchedule {

    private int id;
    private Color color;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String day;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public RecurringDoctorSchedule(String doctorUsername, String day, Time time_start, Time time_end) {
        super(doctorUsername, time_start, time_end);
        this.day = day;
    }

    private int convertToIntDay(String day) {
        switch (day) {
            case "Sunday":
                return Calendar.SUNDAY;
            case "Monday":
                return Calendar.MONDAY;
            case "Tuesday":
                return Calendar.TUESDAY;
            case "Wednesday":
                return Calendar.WEDNESDAY;
            case "Thursday":
                return Calendar.THURSDAY;
            case "Friday":
                return Calendar.FRIDAY;
            case "Saturday":
                return Calendar.SATURDAY;
        }
        return 0;
    }

//    public boolean isConflict(Appointment appointment) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(appointment.getDate());
//        int intDay = calendar.get(Calendar.DAY_OF_WEEK);
//        if (convertDayToIntDay(this.day) == intDay) {
//
//        }
//        return false;
//    }
    public boolean isAppointmentInside(Appointment appointment) {
        Calendar c = Calendar.getInstance();
        c.setTime(appointment.getDate());
        int day_of_week = c.get(Calendar.DAY_OF_WEEK);
        if (day_of_week == convertToIntDay(day)) {
            return isTimeAInsideTimeB(
                    this.getTime_start(),
                    this.getTime_end(),
                    appointment.getTime_start(),
                    appointment.getTime_end()
            );
        } else {
            return false;
        }
    }

    private boolean isTimeAInsideTimeB(Date dateStartA, Date dateEndA, Date dateStartB, Date dateEndB) {
        return (dateStartA.compareTo(dateEndB) >= 0) && (dateEndA.compareTo(dateStartB) <= 0);
    }

    public boolean isConflict(RecurringDoctorSchedule otherSchedule) {
        if (this.day.equals(otherSchedule.day)) {
            return checkClash(
                    this.getTime_start(),
                    this.getTime_end(),
                    otherSchedule.getTime_start(),
                    otherSchedule.getTime_end()
            );
        } else {
            return false;
        }
    }

    private boolean checkClash(Date dateStartA, Date dateEndA, Date dateStartB, Date dateEndB) {
        boolean isClash = (dateStartA.compareTo(dateEndB) < 0) && (dateEndA.compareTo(dateStartB) > 0);
        boolean isSame = (dateStartA.compareTo(dateStartB) == 0) && (dateEndA.compareTo(dateEndB) == 0);
        if(isSame){
            return true;
        }else{
            return isClash;
        }
    }

    @Override
    public String toString() {
        return "SuperClass : " + super.toString() + " SubClass : RecurringDoctorSchedule{" + "day=" + day + '}';
    }

}
