package View;

import Model.Database.Appointment;
import java.awt.Color;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class AgendaPanel extends javax.swing.JPanel implements DynamicPanel, InvalidationListener {

    private MainPanel mainPanel;
    private Date date;
    private boolean weekly;

    public boolean isWeekly() {
        return weekly;
    }

    public void setWeekly(boolean weekly) {
        this.weekly = weekly;
    }

    public AgendaPanel() {
        initComponents();
        weekly = false;
    }

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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPaneDailyAgenda = new javax.swing.JTextPane();

        setPreferredSize(new java.awt.Dimension(575, 425));

        jTextPaneDailyAgenda.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jScrollPane2.setViewportView(jTextPaneDailyAgenda);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPaneDailyAgenda;
    // End of variables declaration//GEN-END:variables

    @Override
    public void setMainPanel(MainPanel panel) {
        this.mainPanel = panel;
    }

    public void updateAgendaViewData() {
        try {
            if (weekly) {
                DateFormat dayDateFormat = new SimpleDateFormat("EEEEEEEEE");
                List<Appointment> appointments = this.mainPanel.databaseController.findAppointment(this.mainPanel.getUser());
                List<Appointment> dayFilteredAppointments = new LinkedList<>();
                DateFormat defaultDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date[] weeklyDates = this.getWeeklyDate(date);
                for (Appointment appointment : appointments) {
                    for (Date weeklyDate : weeklyDates) {
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
                }
                String result = "";
                DateFormat resultDayFormat = new SimpleDateFormat("HH:mm");
                for (Appointment dayFilteredAppointment : dayFilteredAppointments) {
                    result += dayDateFormat.format(dayFilteredAppointment.getDate())
                            + " - "
                            + resultDayFormat.format(dayFilteredAppointment.getTime_start())
                            + " - "
                            + resultDayFormat.format(dayFilteredAppointment.getTime_end())
                            + " - "
                            + dayFilteredAppointment.getClientUsername()
                            + " - "
                            + dayFilteredAppointment.getDoctorUsername()
                            + "\n";
                }
                this.jTextPaneDailyAgenda.setText(result);
            } else {
                DateFormat dayDateFormat = new SimpleDateFormat("EEEEEEEEE");
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
                String result = "";
                DateFormat resultDayFormat = new SimpleDateFormat("HH:mm");
                for (Appointment dayFilteredAppointment : dayFilteredAppointments) {
                    result += resultDayFormat.format(dayFilteredAppointment.getTime_start())
                            + " - "
                            + resultDayFormat.format(dayFilteredAppointment.getTime_end())
                            + " - "
                            + dayFilteredAppointment.getClientUsername()
                            + " - "
                            + dayFilteredAppointment.getDoctorUsername()
                            + "\n";
                }
                this.jTextPaneDailyAgenda.setText(result);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgendaPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void invalidated(Observable o) {
        updateAgendaViewData();
    }

    public void setDate(Date date) {
        this.date = date;
        updateAgendaViewData();
    }

}
