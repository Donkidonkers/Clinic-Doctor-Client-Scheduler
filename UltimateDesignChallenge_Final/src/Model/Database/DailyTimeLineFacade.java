package Model.Database;

import java.awt.Color;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class DailyTimeLineFacade {

    public double[] timestamp;
    public Appointment[] appointmentTimeline;
    public RecurringDoctorSchedule[] doctorScheduleTimeline;
    public DoctorEvent[] doctorEventTimeline;

    public DailyTimeLineFacade(Appointment[] appointmentTimeline, RecurringDoctorSchedule[] doctorScheduleTimeline, DoctorEvent[] doctorEventTimeline) {
        this.appointmentTimeline = appointmentTimeline;
        this.doctorScheduleTimeline = doctorScheduleTimeline;
        this.doctorEventTimeline = doctorEventTimeline;
        this.timestamp = new double[48];
        double start = 0;
        int count = 0;
        for (double u : timestamp) {
            timestamp[count] = start;
            count++;
            start += 0.5;
        }
    }

    private int timeToIndex(int hour, int minute) {
        return (hour * 2) + (minute / 30);
    }

    public boolean isSlotAvailable(Appointment appointment) {
        boolean[] doctorAvailabilityTimeline = this.calculateDoctorAvailabilityTimeline();
        boolean isAvailable = true;
        Calendar c = Calendar.getInstance();
        c.setTime(appointment.getTime_start());
        int startIndex = timeToIndex(c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE));
        c.setTime(appointment.getTime_end());
        int endIndex = timeToIndex(c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE));
        for (int i = startIndex; i < endIndex; i++) {
            if (!doctorAvailabilityTimeline[i]) {
                isAvailable = false;
                return isAvailable;
            }
        }
        return isAvailable;
    }

    public boolean[] calculateDoctorAvailabilityTimeline() {
        boolean[] doctorAvailabilityTimeline = new boolean[48];
        if (this.doctorScheduleTimeline != null) {
            for (int i = 0; i < 48; i++) {
                if (this.doctorScheduleTimeline[i] != null) {
                    doctorAvailabilityTimeline[i] = true;
                } else {
                    doctorAvailabilityTimeline[i] = false;
                }
            }
        }
        if (this.doctorEventTimeline != null) {
            for (int i = 0; i < 48; i++) {
                if (this.doctorEventTimeline[i] != null) {
                    if (this.doctorEventTimeline[i].isAvailable()) {
                        doctorAvailabilityTimeline[i] = true;
                    } else {
                        doctorAvailabilityTimeline[i] = false;
                    }
                }
            }
        }
        if (this.appointmentTimeline != null) {
            for (int i = 0; i < 48; i++) {
                if (this.appointmentTimeline[i] != null) {
                    doctorAvailabilityTimeline[i] = false;
                }
            }
        }
        return doctorAvailabilityTimeline;
    }

    public Color[] getColorTimeline() {
        Color[] result = new Color[48];
        if (this.doctorScheduleTimeline != null) {
            for (int i = 0; i < 48; i++) {
                if (this.doctorScheduleTimeline[i] != null) {
                    result[i] = this.doctorScheduleTimeline[i].getColor();
                }
            }
        }
        if (this.doctorEventTimeline != null) {
            for (int i = 0; i < 48; i++) {
                if (this.doctorEventTimeline[i] != null) {
                    result[i] = this.doctorEventTimeline[i].getColor();
                }
            }
        }
        if (this.appointmentTimeline != null) {
            for (int i = 0; i < 48; i++) {
                if (this.appointmentTimeline[i] != null) {
                    result[i] = this.appointmentTimeline[i].getColor();
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "DailyTimeLineCalculatorFacade{" + "doctorAvailablilityTimeline=" + doctorScheduleTimeline + ", appointmentTimeline=" + appointmentTimeline + ", doctorEventTimeline=" + doctorEventTimeline + '}';
    }

}
