package Model.Database;

import java.sql.Time;

public abstract class DoctorSchedule {

    private String doctorUsername;
    private Time time_start;
    private Time time_end;
    
    public DoctorSchedule(String doctorUsername, Time time_start, Time time_end) {
        this.doctorUsername = doctorUsername;
        this.time_start = time_start;
        this.time_end = time_end;
    }
    
    public String getDoctorUsername() {
        return doctorUsername;
    }

    public void setDoctorUsername(String doctorUsername) {
        this.doctorUsername = doctorUsername;
    }

    public Time getTime_start() {
        return time_start;
    }

    public void setTime_start(Time time_start) {
        this.time_start = time_start;
    }

    public Time getTime_end() {
        return time_end;
    }

    public void setTime_end(Time time_end) {
        this.time_end = time_end;
    }

    @Override
    public String toString() {
        return "DoctorSchedule{" + "doctorUsername=" + doctorUsername + ", time_start=" + time_start + ", time_end=" + time_end + '}';
    }

}
