package View;

import Controller.Database.DatabaseController;
import Model.Database.User;
import View.Client.CreateClientAppointPanel;
import View.Client.RemoveAppointmentPanel;
import View.Doctor.EditSchedulePanel;
import View.Secretary.CreateSecretaryAppointPanel;
import View.Secretary.EditAppointmentPanel;
import java.util.Date;
import javax.swing.JPanel;

public class MainPanel extends javax.swing.JPanel implements ViewObserver {

    private User user;
    public DatabaseController databaseController;

    public User getUser() {
        return user;
    }

    public AgendaPanel getDailyAgendaPanel() {
        return dailyAgendaPanel;
    }

    public AgendaPanel getWeeklyAgendaPanel() {
        return weeklyAgendaPanel;
    }

    public CreateClientAppointPanel getCreateClientAppointPanel() {
        return createClientAppointPanel;
    }

    public EditAppointmentPanel getEditAppointmentPanel() {
        return editAppointmentPanel;
    }

    public RemoveAppointmentPanel getRemoveAppointmentPanel() {
        return removeAppointmentPanel;
    }

    public WeeklyPanel getWeeklyViewPanel() {
        return weeklyViewPanel;
    }

    public MainPanel(User user, DatabaseController databaseController) {
        initComponents();
        this.user = user;
        this.databaseController = databaseController;

        this.createPanel.setMainPanel(this);
        this.eventPanel.setMainPanel(this);
        this.dailyViewPanel.setMainPanel(this);
        this.weeklyViewPanel.setMainPanel(this);
        this.dailyAgendaPanel.setMainPanel(this);
        this.weeklyAgendaPanel.setMainPanel(this);

        this.calendarPanel.addObserver(this);
        
        this.calendarPanel.setMainPanel(this);
        this.createSecretaryAppointPanel.setMainPanel(this);
        this.createClientAppointPanel.setMainPanel(this);
        this.removeAppointmentPanel.setMainPanel(this);
        this.editAppointmentPanel.setMainPanel(this);
        this.editPanel.setMainPanel(this);
        
        this.calendarPanel.setUser(user);
        this.dailyViewPanel.setDate(new Date());
        this.weeklyViewPanel.setDate(new Date());
        this.dailyAgendaPanel.setDate(new Date());
        this.weeklyAgendaPanel.setDate(new Date());
        this.weeklyAgendaPanel.setWeekly(true);
        
        this.editPanel.addListener(dailyViewPanel);
        this.createPanel.addListener(dailyViewPanel);
        this.eventPanel.addListener(dailyViewPanel);
        this.createSecretaryAppointPanel.addListener(dailyViewPanel);
        this.createClientAppointPanel.addListener(dailyViewPanel);
        this.removeAppointmentPanel.addListener(dailyViewPanel);
        this.editAppointmentPanel.addListener(dailyViewPanel);
        
        this.editPanel.addListener(weeklyViewPanel);
        this.createPanel.addListener(weeklyViewPanel);
        this.eventPanel.addListener(weeklyViewPanel);
        this.createSecretaryAppointPanel.addListener(weeklyViewPanel);
        this.createClientAppointPanel.addListener(weeklyViewPanel);
        this.removeAppointmentPanel.addListener(weeklyViewPanel);
        this.editAppointmentPanel.addListener(weeklyViewPanel);
        
        this.editPanel.addListener(dailyAgendaPanel);
        this.createPanel.addListener(dailyAgendaPanel);
        this.eventPanel.addListener(dailyAgendaPanel);
        this.createSecretaryAppointPanel.addListener(dailyAgendaPanel);
        this.createClientAppointPanel.addListener(dailyAgendaPanel);
        this.removeAppointmentPanel.addListener(dailyAgendaPanel);
        this.editAppointmentPanel.addListener(dailyAgendaPanel);
        
        this.editPanel.addListener(weeklyAgendaPanel);
        this.createPanel.addListener(weeklyAgendaPanel);
        this.createPanel.addListener(weeklyAgendaPanel);
        this.createSecretaryAppointPanel.addListener(weeklyAgendaPanel);
        this.createClientAppointPanel.addListener(weeklyAgendaPanel);
        this.removeAppointmentPanel.addListener(weeklyAgendaPanel);
        this.editAppointmentPanel.addListener(weeklyAgendaPanel);
    }

    public AgendaPanel getAgendaPanel() {
        return dailyAgendaPanel;
    }

    public WeeklyPanel getWeeklyPanel() {
        return weeklyViewPanel;
    }

    public DayPanel getDailyViewPanel() {
        return dailyViewPanel;
    }

    @Override
    public void updateDynamicPanel(JPanel input) {
//        input.setMainPanel(this);
        this.dynamicPanel.removeAll();
        this.dynamicPanel.repaint();
        this.dynamicPanel.revalidate();
        this.dynamicPanel.add(input);
        this.dynamicPanel.repaint();
        this.dynamicPanel.revalidate();
    }

    public EditSchedulePanel getEditPanel() {
        return editPanel;
    }

    public JPanel getDoctorCard() {
        return doctorCard;
    }

    public CreateSecretaryAppointPanel getCreateSecretaryAppointPanel() {
        return createSecretaryAppointPanel;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dynamicPanel = new javax.swing.JPanel();
        doctorCard = new javax.swing.JPanel();
        jTabbedPane = new javax.swing.JTabbedPane();
        createPanel = new View.Doctor.CreateSchedulePanel();
        eventPanel = new View.Doctor.EventPanel();
        editPanel = new View.Doctor.EditSchedulePanel();
        secretaryCard = new javax.swing.JPanel();
        createSecretaryAppointPanel = new View.Secretary.CreateSecretaryAppointPanel();
        editAppointmentPanel = new View.Secretary.EditAppointmentPanel();
        clientCard = new javax.swing.JPanel();
        createClientAppointPanel = new View.Client.CreateClientAppointPanel();
        removeAppointmentPanel = new View.Client.RemoveAppointmentPanel();
        weeklyViewPanel = new View.WeeklyPanel();
        dailyAgendaPanel = new View.AgendaPanel();
        dailyViewPanel = new View.DayPanel();
        weeklyAgendaPanel = new View.AgendaPanel();
        calendarPanel = new View.CalendarPanel();

        setBackground(new java.awt.Color(0, 76, 112));

        dynamicPanel.setBackground(new java.awt.Color(0, 76, 112));
        dynamicPanel.setMinimumSize(new java.awt.Dimension(580, 453));
        dynamicPanel.setLayout(new java.awt.CardLayout());

        doctorCard.setLayout(new java.awt.CardLayout());

        jTabbedPane.addTab("Create", createPanel);

        eventPanel.setMinimumSize(new java.awt.Dimension(524, 409));
        eventPanel.setPreferredSize(new java.awt.Dimension(524, 409));
        jTabbedPane.addTab("Event", eventPanel);

        doctorCard.add(jTabbedPane, "card7");
        doctorCard.add(editPanel, "card6");

        dynamicPanel.add(doctorCard, "card7");

        secretaryCard.setLayout(new java.awt.CardLayout());
        secretaryCard.add(createSecretaryAppointPanel, "card2");
        secretaryCard.add(editAppointmentPanel, "card9");

        dynamicPanel.add(secretaryCard, "card7");

        clientCard.setLayout(new java.awt.CardLayout());
        clientCard.add(createClientAppointPanel, "card8");
        clientCard.add(removeAppointmentPanel, "card9");

        dynamicPanel.add(clientCard, "card7");
        dynamicPanel.add(weeklyViewPanel, "card8");
        dynamicPanel.add(dailyAgendaPanel, "card2");
        dynamicPanel.add(dailyViewPanel, "card4");
        dynamicPanel.add(weeklyAgendaPanel, "card2");

        calendarPanel.setBackground(new java.awt.Color(0, 76, 112));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(calendarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dynamicPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(calendarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dynamicPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private View.CalendarPanel calendarPanel;
    private javax.swing.JPanel clientCard;
    private View.Client.CreateClientAppointPanel createClientAppointPanel;
    private View.Doctor.CreateSchedulePanel createPanel;
    private View.Secretary.CreateSecretaryAppointPanel createSecretaryAppointPanel;
    private View.AgendaPanel dailyAgendaPanel;
    private View.DayPanel dailyViewPanel;
    private javax.swing.JPanel doctorCard;
    private javax.swing.JPanel dynamicPanel;
    private View.Secretary.EditAppointmentPanel editAppointmentPanel;
    private View.Doctor.EditSchedulePanel editPanel;
    private View.Doctor.EventPanel eventPanel;
    private javax.swing.JTabbedPane jTabbedPane;
    private View.Client.RemoveAppointmentPanel removeAppointmentPanel;
    private javax.swing.JPanel secretaryCard;
    private View.AgendaPanel weeklyAgendaPanel;
    private View.WeeklyPanel weeklyViewPanel;
    // End of variables declaration//GEN-END:variables
}
