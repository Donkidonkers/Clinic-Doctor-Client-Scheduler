package Model.Database;

import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class DailyAppointmentTimeLineBuilder {

    private List<Appointment> appointments;

    public Appointment[] appointmentTimeline;

    public DailyAppointmentTimeLineBuilder() {
        this.appointmentTimeline = new Appointment[48];
        this.appointments = new LinkedList<>();
    }

    private int timeToIndex(int hour, int minute) {
        return (hour * 2) + (minute / 30);
    }

    public DailyAppointmentTimeLineBuilder addAppointment(Appointment[] appointments) {
        for (Appointment appointment : appointments) {
            this.addAppointment(appointment);
        }
        return this;
    }

    public DailyAppointmentTimeLineBuilder addAppointment(Appointment appointment) {

        this.appointments.add(appointment);
        Calendar c = Calendar.getInstance();
        c.setTime(appointment.getTime_start());
        int startIndex = timeToIndex(c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE));
        c.setTime(appointment.getTime_end());
        int endIndex = timeToIndex(c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE));
        for (int i = startIndex; i < endIndex; i++) {
            this.appointmentTimeline[i] = appointment;
        }
        return this;
    }

    public Appointment[] generateRecurringDoctorScheduleTimeLine(){
        return Arrays.copyOf(this.appointmentTimeline, 48);
    }
    
}
