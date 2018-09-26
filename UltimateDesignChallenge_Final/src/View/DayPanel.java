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
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

public class DayPanel extends javax.swing.JPanel implements DynamicPanel, InvalidationListener {

    private MainPanel mainPanel;
    private DailyTimeLineFacade data;
    private Date date;
    private String doctorFilter;
    private Color[] colors = {
        Color.CYAN,
        Color.GREEN,
        Color.YELLOW,
        Color.MAGENTA
    };

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

    public DayPanel() {
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
            if (this.mainPanel.getUser().getType().equals("doctor")) {//when the user is doctor
                DateFormat dayDateFormat = new SimpleDateFormat("EEEEEEEEE");
                List<RecurringDoctorSchedule> recurringDoctorSchedules = this.mainPanel.databaseController.findDoctorSchedule(this.mainPanel.getUser(), dayDateFormat.format(date));
                for (RecurringDoctorSchedule recurringDoctorSchedule : recurringDoctorSchedules) {
                    recurringDoctorSchedule.setColor(Color.CYAN);
                }
                DailyDoctorScheduleTimeLineBuilder dailyDoctorScheduleTimeLineBuilder = new DailyDoctorScheduleTimeLineBuilder();
                dailyDoctorScheduleTimeLineBuilder.addRecurringDoctorSchedule(recurringDoctorSchedules.toArray(new RecurringDoctorSchedule[recurringDoctorSchedules.size()]));

                List<DoctorEvent> doctorEvents = this.mainPanel.databaseController.findDoctorEvent(this.mainPanel.getUser(), date);
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
                DateFormat defaultDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                for (Appointment appointment : appointments) {
                    if (appointment.isRecurring()) {
                        if (dayDateFormat.format(appointment.getDate()).equals(dayDateFormat.format(date))) {
                            dayFilteredAppointments.add(appointment);
                        }
                    } else {
                        if (defaultDateFormat.format(appointment.getDate()).equals(defaultDateFormat.format(date))) {
                            dayFilteredAppointments.add(appointment);
                        }
                    }
                }
                DailyAppointmentTimeLineBuilder appointmentTimeLineBuilder = new DailyAppointmentTimeLineBuilder();
                appointmentTimeLineBuilder.addAppointment(dayFilteredAppointments.toArray(new Appointment[dayFilteredAppointments.size()]));

                Appointment[] appointmentTimeline = appointmentTimeLineBuilder.generateRecurringDoctorScheduleTimeLine();
                RecurringDoctorSchedule[] doctorScheduleTimeline = dailyDoctorScheduleTimeLineBuilder.generateRecurringDoctorScheduleTimeLine();
                DoctorEvent[] doctorEventTimeline = dailyDoctorScheduleTimeLineBuilder.generateDoctorEventTimeLine();

                this.data = new DailyTimeLineFacade(appointmentTimeline, doctorScheduleTimeline, doctorEventTimeline);
                this.jTable1.setModel(new DayPanelTableModel(data));
            } else if (this.mainPanel.getUser().getType().equals("secretary")) {//when the user is secretary
                DateFormat dayDateFormat = new SimpleDateFormat("EEEEEEEEE");
                List<RecurringDoctorSchedule> recurringDoctorSchedules;
                List<DoctorEvent> doctorEvents;
                if (this.doctorFilter != null) {
                    if (!this.doctorFilter.equals("")) {
                        User doctor = this.mainPanel.databaseController.findUserByUsername(this.doctorFilter);
                        recurringDoctorSchedules = this.mainPanel.databaseController.findDoctorSchedule(doctor, dayDateFormat.format(date));
                        doctorEvents = this.mainPanel.databaseController.findDoctorEvent(doctor, date);
                    } else {
                        doctorEvents = this.mainPanel.databaseController.findDoctorEvent(date);
                        recurringDoctorSchedules = this.mainPanel.databaseController.findDoctorSchedule(dayDateFormat.format(date));
                    }
                } else {
                    doctorEvents = this.mainPanel.databaseController.findDoctorEvent(date);
                    recurringDoctorSchedules = this.mainPanel.databaseController.findDoctorSchedule(dayDateFormat.format(date));
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
                        doctorEvent.setColor(doctorColor.get(doctorEvent.getDoctorUsername()).darker());
                    } else {
                        doctorEvent.setColor(Color.GRAY);
                    }
                }
                dailyDoctorScheduleTimeLineBuilder.addDoctorEvent(doctorEvents.toArray(new DoctorEvent[doctorEvents.size()]));

                List<Appointment> appointments = this.mainPanel.databaseController.findAllAppointment();
                List<Appointment> dayFilteredAppointments = new LinkedList<>();
                DateFormat defaultDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                for (Appointment appointment : appointments) {
                    if (appointment.isRecurring()) {
                        if (dayDateFormat.format(appointment.getDate()).equals(dayDateFormat.format(date))) {
                            dayFilteredAppointments.add(appointment);
                        }
                    } else {
                        if (defaultDateFormat.format(appointment.getDate()).equals(defaultDateFormat.format(date))) {
                            dayFilteredAppointments.add(appointment);
                        }
                    }
                }
                DailyAppointmentTimeLineBuilder appointmentTimeLineBuilder = new DailyAppointmentTimeLineBuilder();
                appointmentTimeLineBuilder.addAppointment(dayFilteredAppointments.toArray(new Appointment[dayFilteredAppointments.size()]));

                Appointment[] appointmentTimeline = appointmentTimeLineBuilder.generateRecurringDoctorScheduleTimeLine();
                RecurringDoctorSchedule[] doctorScheduleTimeline = dailyDoctorScheduleTimeLineBuilder.generateRecurringDoctorScheduleTimeLine();
                DoctorEvent[] doctorEventTimeline = dailyDoctorScheduleTimeLineBuilder.generateDoctorEventTimeLine();

                this.data = new DailyTimeLineFacade(appointmentTimeline, doctorScheduleTimeline, doctorEventTimeline);
                this.jTable1.setModel(new DayPanelTableModel(data));
            } else if (this.mainPanel.getUser().getType().equals("client")) {//when the user is secretary
                DateFormat dayDateFormat = new SimpleDateFormat("EEEEEEEEE");
                List<RecurringDoctorSchedule> recurringDoctorSchedules;
                List<DoctorEvent> doctorEvents;
                if (this.doctorFilter != null) {
                    if (!this.doctorFilter.equals("")) {
                        User doctor = this.mainPanel.databaseController.findUserByUsername(this.doctorFilter);
                        recurringDoctorSchedules = this.mainPanel.databaseController.findDoctorSchedule(doctor, dayDateFormat.format(date));
                        doctorEvents = this.mainPanel.databaseController.findDoctorEvent(doctor, date);
                    } else {
                        doctorEvents = this.mainPanel.databaseController.findDoctorEvent(date);
                        recurringDoctorSchedules = this.mainPanel.databaseController.findDoctorSchedule(dayDateFormat.format(date));
                    }
                } else {
                    doctorEvents = this.mainPanel.databaseController.findDoctorEvent(date);
                    recurringDoctorSchedules = this.mainPanel.databaseController.findDoctorSchedule(dayDateFormat.format(date));
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
                DateFormat defaultDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                for (Appointment appointment : appointments) {
                    if (appointment.isRecurring()) {
                        if (dayDateFormat.format(appointment.getDate()).equals(dayDateFormat.format(date))) {
                            appointment.setColor(Color.RED);
                            if (appointment.getClientUsername().equals(this.mainPanel.getUser().getUsername())) {
                                appointment.setColor(Color.GREEN);
                            }
                            dayFilteredAppointments.add(appointment);
                        }
                    } else {
                        if (defaultDateFormat.format(appointment.getDate()).equals(defaultDateFormat.format(date))) {
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

                this.data = new DailyTimeLineFacade(appointmentTimeline, doctorScheduleTimeline, doctorEventTimeline);

                this.jTable1.setModel(new DayPanelClientTableModel(data));
            }
            this.jTable1.getColumnModel().getColumn(1).setCellRenderer(new DayCellRenderer());
            this.jTable1.getColumnModel().getColumn(0).setMinWidth(40);
            this.jTable1.getColumnModel().getColumn(1).setMinWidth(900);
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

        setPreferredSize(new java.awt.Dimension(575, 425));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"00:00", null},
                {"", null},
                {"01:00", null},
                {"", null},
                {"02:00", null},
                {"", null},
                {"03:00", null},
                {"", null},
                {"04:00", null},
                {null, null},
                {"05:00", null},
                {null, null},
                {"06:00", null},
                {null, null},
                {"07:00", null},
                {null, null},
                {"08:00", null},
                {null, null},
                {"09:00", null},
                {null, null},
                {"10:00", null},
                {null, null},
                {"11:00", null},
                {null, null},
                {"12:00", null},
                {null, null},
                {"13:00", null},
                {null, null},
                {"14:00", null},
                {null, null},
                {"15:00", null},
                {null, null},
                {"16:00", null},
                {null, null},
                {"17:00", null},
                {null, null},
                {"18:00", null},
                {null, null},
                {"19:00", null},
                {null, null},
                {"20:00", null},
                {null, null},
                {"21:00", null},
                {null, null},
                {"22:00", null},
                {null, null},
                {"23:00", null},
                {null, null}
            },
            new String [] {
                "Time", "Occasion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setMinWidth(100);
            jTable1.getColumnModel().getColumn(0).setMaxWidth(100);
        }

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
