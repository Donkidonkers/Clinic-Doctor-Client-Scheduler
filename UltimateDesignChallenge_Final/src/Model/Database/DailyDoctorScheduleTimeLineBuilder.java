package Model.Database;

import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class DailyDoctorScheduleTimeLineBuilder {

    private List<DoctorSchedule> doctorSchedules;
    public RecurringDoctorSchedule[] doctorScheduleTimeline;
    public DoctorEvent[] doctorEventTimeline;

    public DailyDoctorScheduleTimeLineBuilder() {
        this.doctorScheduleTimeline = new RecurringDoctorSchedule[48];
        this.doctorEventTimeline = new DoctorEvent[48];
        this.doctorSchedules = new LinkedList<>();
    }

    private int timeToIndex(int hour, int minute) {
        return (hour * 2) + (minute / 30);
    }

    public DailyDoctorScheduleTimeLineBuilder addDoctorEvent(DoctorEvent[] doctorEvents) {
        for (DoctorEvent doctorEvent : doctorEvents) {
            this.addDoctorEvent(doctorEvent);
        }
        return this;
    }

    public DailyDoctorScheduleTimeLineBuilder addDoctorEvent(DoctorEvent doctorEvent) {

        this.doctorSchedules.add(doctorEvent);
        Calendar c = Calendar.getInstance();
        c.setTime(doctorEvent.getTime_start());
        int startIndex = timeToIndex(c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE));
        c.setTime(doctorEvent.getTime_end());
        int endIndex = timeToIndex(c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE));
        for (int i = startIndex; i < endIndex; i++) {
            this.doctorEventTimeline[i] = doctorEvent;
        }
        return this;
    }

    public DailyDoctorScheduleTimeLineBuilder addRecurringDoctorSchedule(RecurringDoctorSchedule[] recurringDoctorSchedules) {
        for (RecurringDoctorSchedule recurringDoctorSchedule : recurringDoctorSchedules) {
            this.addRecurringDoctorSchedule(recurringDoctorSchedule);
        }
        return this;
    }

    public DailyDoctorScheduleTimeLineBuilder addRecurringDoctorSchedule(RecurringDoctorSchedule recurringDoctorSchedule) {
        this.doctorSchedules.add(recurringDoctorSchedule);
        Calendar c = Calendar.getInstance();
        c.setTime(recurringDoctorSchedule.getTime_start());
        int startIndex = timeToIndex(c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE));
        c.setTime(recurringDoctorSchedule.getTime_end());
        int endIndex = timeToIndex(c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE));
        for (int i = startIndex; i < endIndex; i++) {
            this.doctorScheduleTimeline[i] = recurringDoctorSchedule;
        }
        return this;
    }
    
    public RecurringDoctorSchedule[] generateRecurringDoctorScheduleTimeLine(){
        return Arrays.copyOf(this.doctorScheduleTimeline, 48);
    }
    
    public DoctorEvent[] generateDoctorEventTimeLine(){
        return Arrays.copyOf(this.doctorEventTimeline, 48);
    }

}
