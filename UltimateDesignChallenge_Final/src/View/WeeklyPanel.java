package View;

import Model.Database.Appointment;
import Model.Database.DailyAppointmentTimeLineBuilder;
import Model.Database.DailyDoctorScheduleTimeLineBuilder;
import Model.Database.DailyTimeLineFacade;
import Model.Database.DoctorEvent;
import Model.Database.RecurringDoctorSchedule;
import Model.Database.User;
import java.awt.Color;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

public class WeeklyPanel extends javax.swing.JPanel implements DynamicPanel, InvalidationListener {

    private MainPanel mainPanel;
    private DailyTimeLineFacade[] data;
    private Date date;
    private String doctorFilter;
    private Color[] colors = {
        Color.CYAN,
        Color.GREEN,
        Color.YELLOW,
        Color.MAGENTA
    };

    public Date[] getWeeklyDate(Date date) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);

        Date[] days = new Date[7];
        int delta = -now.get(GregorianCalendar.DAY_OF_WEEK) + 1; //add 2 if your week start on monday
        now.add(Calendar.DAY_OF_MONTH, delta);
        for (int i = 0; i < 7; i++) {
            days[i] = now.getTime();
            now.add(Calendar.DAY_OF_MONTH, 1);
        }
        return days;
    }

    public String getDoctorFilter() {
        return doctorFilter;
    }

    public void setDoctorFilter(String doctorFilter) {
        this.doctorFilter = doctorFilter;
        updateTableData();
    }

    public void setDate(Date date) {
        this.date = date;
        updateTableData();
    }

    public WeeklyPanel() {
        initComponents();
    }

    public static DayPanel createInstance(Date date) {
        DayPanel newDayPanel = new DayPanel();
        newDayPanel.setDate(date);
        return newDayPanel;
    }

    @Override
    public void setMainPanel(MainPanel panel) {
        this.mainPanel = panel;
    }

    public void updateTableData() {
        try {
            DateFormat defaultDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat dayDateFormat = new SimpleDateFormat("EEEEEEEEE");
            Date[] weeklyDates = this.getWeeklyDate(date);
            List<DailyTimeLineFacade> dailyTimeLineFacades = new LinkedList<>();
            if (this.mainPanel.getUser().getType().equals("doctor")) {
                for (Date weeklyDate : weeklyDates) {
                    List<RecurringDoctorSchedule> recurringDoctorSchedules = this.mainPanel.databaseController.findDoctorSchedule(this.mainPanel.getUser(), dayDateFormat.format(weeklyDate));
                    for (RecurringDoctorSchedule recurringDoctorSchedule : recurringDoctorSchedules) {
                        recurringDoctorSchedule.setColor(Color.CYAN);
                    }
                    DailyDoctorScheduleTimeLineBuilder dailyDoctorScheduleTimeLineBuilder = new DailyDoctorScheduleTimeLineBuilder();
                    dailyDoctorScheduleTimeLineBuilder.addRecurringDoctorSchedule(recurringDoctorSchedules.toArray(new RecurringDoctorSchedule[recurringDoctorSchedules.size()]));

                    List<DoctorEvent> doctorEvents = this.mainPanel.databaseController.findDoctorEvent(this.mainPanel.getUser(), weeklyDate);
                    for (DoctorEvent doctorEvent : doctorEvents) {
                        if (doctorEvent.isAvailable()) {
                            doctorEvent.setColor(Color.BLUE);
                        } else {
                            doctorEvent.setColor(Color.GRAY);
                        }
                    }
                    dailyDoctorScheduleTimeLineBuilder.addDoctorEvent(doctorEvents.toArray(new DoctorEvent[doctorEvents.size()]));

                    List<Appointment> appointments = this.mainPanel.databaseController.findAppointment(this.mainPanel.getUser());
                    List<Appointment> dayFilteredAppointments = new LinkedList<>();
                    for (Appointment appointment : appointments) {
                        if (appointment.isRecurring()) {
                            if (dayDateFormat.format(appointment.getDate()).equals(dayDateFormat.format(weeklyDate))) {
                                dayFilteredAppointments.add(appointment);
                            }
                        } else {
                            if (defaultDateFormat.format(appointment.getDate()).equals(defaultDateFormat.format(weeklyDate))) {
                                dayFilteredAppointments.add(appointment);
                            }
                        }
                    }
                    DailyAppointmentTimeLineBuilder appointmentTimeLineBuilder = new DailyAppointmentTimeLineBuilder();
                    appointmentTimeLineBuilder.addAppointment(dayFilteredAppointments.toArray(new Appointment[dayFilteredAppointments.size()]));

                    Appointment[] appointmentTimeline = appointmentTimeLineBuilder.generateRecurringDoctorScheduleTimeLine();
                    RecurringDoctorSchedule[] doctorScheduleTimeline = dailyDoctorScheduleTimeLineBuilder.generateRecurringDoctorScheduleTimeLine();
                    DoctorEvent[] doctorEventTimeline = dailyDoctorScheduleTimeLineBuilder.generateDoctorEventTimeLine();
                    DailyTimeLineFacade newDailyTimeLineFacade = new DailyTimeLineFacade(appointmentTimeline, doctorScheduleTimeline, doctorEventTimeline);
                    dailyTimeLineFacades.add(newDailyTimeLineFacade);
                }
            } else if (this.mainPanel.getUser().getType().equals("secretary")) {
                for (Date weeklyDate : weeklyDates) {
                    List<RecurringDoctorSchedule> recurringDoctorSchedules;
                    List<DoctorEvent> doctorEvents;
                    if (this.doctorFilter != null) {
                        if (!this.doctorFilter.equals("")) {
                            User doctor = this.mainPanel.databaseController.findUserByUsername(this.doctorFilter);
                            recurringDoctorSchedules = this.mainPanel.databaseController.findDoctorSchedule(doctor, dayDateFormat.format(weeklyDate));
                            doctorEvents = this.mainPanel.databaseController.findDoctorEvent(doctor, weeklyDate);
                        } else {
                            recurringDoctorSchedules = this.mainPanel.databaseController.findDoctorSchedule(dayDateFormat.format(weeklyDate));
                            doctorEvents = this.mainPanel.databaseController.findDoctorEvent(weeklyDate);
                        }
                    } else {
                        recurringDoctorSchedules = this.mainPanel.databaseController.findDoctorSchedule(dayDateFormat.format(weeklyDate));
                        doctorEvents = this.mainPanel.databaseController.findDoctorEvent(weeklyDate);
                    }
                    String currentDoctor = "";
                    int counter = -1;
                    Map<String, Color> doctorColor = new HashMap<String, Color>();
                    for (RecurringDoctorSchedule recurringDoctorSchedule : recurringDoctorSchedules) {
                        if (!currentDoctor.equals(recurringDoctorSchedule.getDoctorUsername())) {
                            currentDoctor = recurringDoctorSchedule.getDoctorUsername();
                            counter++;
                        }
                        doctorColor.put(currentDoctor, this.colors[counter]);
                        recurringDoctorSchedule.setColor(this.colors[counter]);
                    }
                    DailyDoctorScheduleTimeLineBuilder dailyDoctorScheduleTimeLineBuilder = new DailyDoctorScheduleTimeLineBuilder();
                    dailyDoctorScheduleTimeLineBuilder.addRecurringDoctorSchedule(recurringDoctorSchedules.toArray(new RecurringDoctorSchedule[recurringDoctorSchedules.size()]));

                    for (DoctorEvent doctorEvent : doctorEvents) {
                        if (doctorEvent.isAvailable()) {
                            doctorEvent.setColor(doctorColor.get(doctorEvent.getDoctorUsername()));
                        } else {
                            doctorEvent.setColor(Color.GRAY);
                        }
                    }
                    dailyDoctorScheduleTimeLineBuilder.addDoctorEvent(doctorEvents.toArray(new DoctorEvent[doctorEvents.size()]));

                    List<Appointment> appointments = this.mainPanel.databaseController.findAllAppointment();
                    List<Appointment> dayFilteredAppointments = new LinkedList<>();
                    for (Appointment appointment : appointments) {
                        if (appointment.isRecurring()) {
                            if (dayDateFormat.format(appointment.getDate()).equals(dayDateFormat.format(weeklyDate))) {
                                dayFilteredAppointments.add(appointment);
                            }
                        } else {
                            if (defaultDateFormat.format(appointment.getDate()).equals(defaultDateFormat.format(weeklyDate))) {
                                dayFilteredAppointments.add(appointment);
                            }
                        }
                    }
                    DailyAppointmentTimeLineBuilder appointmentTimeLineBuilder = new DailyAppointmentTimeLineBuilder();
                    appointmentTimeLineBuilder.addAppointment(dayFilteredAppointments.toArray(new Appointment[dayFilteredAppointments.size()]));

                    Appointment[] appointmentTimeline = appointmentTimeLineBuilder.generateRecurringDoctorScheduleTimeLine();
                    RecurringDoctorSchedule[] doctorScheduleTimeline = dailyDoctorScheduleTimeLineBuilder.generateRecurringDoctorScheduleTimeLine();
                    DoctorEvent[] doctorEventTimeline = dailyDoctorScheduleTimeLineBuilder.generateDoctorEventTimeLine();
                    DailyTimeLineFacade newDailyTimeLineFacade = new DailyTimeLineFacade(appointmentTimeline, doctorScheduleTimeline, doctorEventTimeline);
                    dailyTimeLineFacades.add(newDailyTimeLineFacade);
                }
            } else if (this.mainPanel.getUser().getType().equals("client")) {
                for (Date weeklyDate : weeklyDates) {
                    List<RecurringDoctorSchedule> recurringDoctorSchedules;
                    List<DoctorEvent> doctorEvents;
                    if (this.doctorFilter != null) {
                        if (!this.doctorFilter.equals("")) {
                            User doctor = this.mainPanel.databaseController.findUserByUsername(this.doctorFilter);
                            recurringDoctorSchedules = this.mainPanel.databaseController.findDoctorSchedule(doctor, dayDateFormat.format(weeklyDate));
                            doctorEvents = this.mainPanel.databaseController.findDoctorEvent(doctor, weeklyDate);
                        } else {
                            doctorEvents = this.mainPanel.databaseController.findDoctorEvent(weeklyDate);
                            recurringDoctorSchedules = this.mainPanel.databaseController.findDoctorSchedule(dayDateFormat.format(weeklyDate));
                        }
                    } else {
                        doctorEvents = this.mainPanel.databaseController.findDoctorEvent(weeklyDate);
                        recurringDoctorSchedules = this.mainPanel.databaseController.findDoctorSchedule(dayDateFormat.format(weeklyDate));
                    }
                    String currentDoctor = "";
                    int counter = -1;
                    Map<String, Color> doctorColor = new HashMap<String, Color>();
                    for (RecurringDoctorSchedule recurringDoctorSchedule : recurringDoctorSchedules) {
                        if (!currentDoctor.equals(recurringDoctorSchedule.getDoctorUsername())) {
                            currentDoctor = recurringDoctorSchedule.getDoctorUsername();
                            counter++;
                        }
                        doctorColor.put(currentDoctor, this.colors[counter]);
                        recurringDoctorSchedule.setColor(this.colors[counter]);
                    }
                    DailyDoctorScheduleTimeLineBuilder dailyDoctorScheduleTimeLineBuilder = new DailyDoctorScheduleTimeLineBuilder();
                    dailyDoctorScheduleTimeLineBuilder.addRecurringDoctorSchedule(recurringDoctorSchedules.toArray(new RecurringDoctorSchedule[recurringDoctorSchedules.size()]));

                    for (DoctorEvent doctorEvent : doctorEvents) {
                        if (doctorEvent.isAvailable()) {
                            doctorEvent.setColor(doctorColor.get(doctorEvent.getDoctorUsername()));
                        } else {
                            doctorEvent.setColor(Color.GRAY);
                        }
                    }
                    dailyDoctorScheduleTimeLineBuilder.addDoctorEvent(doctorEvents.toArray(new DoctorEvent[doctorEvents.size()]));

                    List<Appointment> appointments = this.mainPanel.databaseController.findAllAppointment();
                    List<Appointment> dayFilteredAppointments = new LinkedList<>();
                    for (Appointment appointment : appointments) {
                        if (appointment.isRecurring()) {
                            if (dayDateFormat.format(appointment.getDate()).equals(dayDateFormat.format(weeklyDate))) {
                                appointment.setColor(Color.RED);
                                if (appointment.getClientUsername().equals(this.mainPanel.getUser().getUsername())) {
                                    appointment.setColor(Color.GREEN);
                                }
                                dayFilteredAppointments.add(appointment);
                            }
                        } else {
                            if (defaultDateFormat.format(appointment.getDate()).equals(defaultDateFormat.format(weeklyDate))) {
                                appointment.setColor(Color.RED);
                                if (appointment.getClientUsername().equals(this.mainPanel.getUser().getUsername())) {
                                    appointment.setColor(Color.GREEN);
                                }
                                dayFilteredAppointments.add(appointment);
                            }
                        }
                    }
                    DailyAppointmentTimeLineBuilder appointmentTimeLineBuilder = new DailyAppointmentTimeLineBuilder();
                    appointmentTimeLineBuilder.addAppointment(dayFilteredAppointments.toArray(new Appointment[dayFilteredAppointments.size()]));

                    Appointment[] appointmentTimeline = appointmentTimeLineBuilder.generateRecurringDoctorScheduleTimeLine();
                    RecurringDoctorSchedule[] doctorScheduleTimeline = dailyDoctorScheduleTimeLineBuilder.generateRecurringDoctorScheduleTimeLine();
                    DoctorEvent[] doctorEventTimeline = dailyDoctorScheduleTimeLineBuilder.generateDoctorEventTimeLine();
                    DailyTimeLineFacade newDailyTimeLineFacade = new DailyTimeLineFacade(appointmentTimeline, doctorScheduleTimeline, doctorEventTimeline);
                    dailyTimeLineFacades.add(newDailyTimeLineFacade);
                }
            }

            this.data = dailyTimeLineFacades.toArray(new DailyTimeLineFacade[dailyTimeLineFacades.size()]);
            if (this.mainPanel.getUser().getType().equals("client")) {
                this.jTable1.setModel(new WeeklyPanelClientTableModel(data));
            } else {
                this.jTable1.setModel(new WeeklyPanelTableModel(data));
            }
            for (int i = 1; i <= 7; i++) {
                this.jTable1.getColumnModel().getColumn(i).setCellRenderer(new WeeklyCellRenderer());
            }
            this.jTable1.getColumnModel().getColumn(0).setMinWidth(40);
        } catch (NullPointerException ex) {
            Logger.getLogger(DayPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DayPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DayPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void invalidated(Observable o) {
        updateTableData();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setMaximumSize(new java.awt.Dimension(575, 425));
        setMinimumSize(new java.awt.Dimension(575, 425));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"00:00", null, null, null, null, null, null, null},
                {"", null, null, null, null, null, null, null},
                {"01:00", null, null, null, null, null, null, null},
                {"", null, null, null, null, null, null, null},
                {"02:00", null, null, null, null, null, null, null},
                {"", null, null, null, null, null, null, null},
                {"03:00", null, null, null, null, null, null, null},
                {"", null, null, null, null, null, null, null},
                {"04:00", null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {"05:00", null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {"06:00", null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {"07:00", null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {"08:00", null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {"09:00", null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {"10:00", null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {"11:00", null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {"12:00", null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {"13:00", null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {"14:00", null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {"15:00", null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {"16:00", null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {"17:00", null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {"18:00", null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {"19:00", null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {"20:00", null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {"21:00", null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {"22:00", null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {"23:00", null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Time", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}
