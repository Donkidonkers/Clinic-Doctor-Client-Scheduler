package View.Secretary;

import Model.Database.Appointment;
import Model.Database.User;
import View.Client.CreateClientAppointPanel;
import View.DynamicPanel;
import View.MainPanel;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class CreateSecretaryAppointPanel extends javax.swing.JPanel implements DynamicPanel, Observable {

    private MainPanel mainPanel;

    public List<InvalidationListener> invalidationListeners = new LinkedList<>();

    @Override
    public void addListener(InvalidationListener il) {
        this.invalidationListeners.add(il);
    }

    @Override
    public void removeListener(InvalidationListener il) {
        this.invalidationListeners.remove(il);
    }

    public void notifyAllListeners() {
        for (InvalidationListener invalidationListener : this.invalidationListeners) {
            invalidationListener.invalidated(this);
        }
    }

    @Override
    public void setMainPanel(MainPanel panel) {
        try {
            this.mainPanel = panel;
            List<User> doctors = this.mainPanel.databaseController.findUserByType("doctor");
            String doctorsString[] = new String[doctors.size()];
            int counter = 0;
            for (User doctor : doctors) {
                doctorsString[counter] = doctor.getUsername();
                counter++;
            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(doctorsString);
            this.jComboBoxDoctor.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(CreateClientAppointPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public CreateSecretaryAppointPanel() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gSCalendarPanel1 = new View.GSCalendarPanel();
        savebtn = new javax.swing.JButton();
        starthouropt = new javax.swing.JComboBox<>();
        startminuteopt = new javax.swing.JComboBox<>();
        endhouropt = new javax.swing.JComboBox<>();
        endminuteopt = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        datetxt = new javax.swing.JTextField();
        calendarbtn = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        nametxt = new javax.swing.JTextField();
        jComboBoxDoctor = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jCheckBoxIsRecurring = new javax.swing.JCheckBox();

        gSCalendarPanel1.setButton(calendarbtn);
        gSCalendarPanel1.setTextFocusable(true);
        gSCalendarPanel1.setTextRefernce(datetxt);

        setPreferredSize(new java.awt.Dimension(580, 453));

        savebtn.setText("Save");
        savebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savebtnActionPerformed(evt);
            }
        });

        starthouropt.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        starthouropt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));

        startminuteopt.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        startminuteopt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "30" }));

        endhouropt.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        endhouropt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" }));

        endminuteopt.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        endminuteopt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "30" }));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel6.setText("Starting Time");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel7.setText("Ending Time");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel1.setText(":");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel8.setText(":");

        datetxt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        datetxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel9.setText("Date");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel10.setText("Client Name");

        nametxt.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 14)); // NOI18N
        nametxt.setText("Username");

        jComboBoxDoctor.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 14)); // NOI18N
        jComboBoxDoctor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Emir", "Mart" }));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel11.setText("Doctor");

        jCheckBoxIsRecurring.setText("Recurring");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nametxt)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 480, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(96, 96, 96))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(datetxt, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(calendarbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(55, 55, 55))
                    .addComponent(jComboBoxDoctor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 85, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addGap(72, 72, 72))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(starthouropt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(startminuteopt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(143, 143, 143)
                                        .addComponent(endhouropt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(endminuteopt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(51, 51, 51))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(savebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(225, 225, 225))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jCheckBoxIsRecurring)
                        .addGap(246, 246, 246))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nametxt, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(calendarbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(datetxt, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(starthouropt, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(startminuteopt, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(endhouropt, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(endminuteopt, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8)))))
                .addGap(38, 38, 38)
                .addComponent(jCheckBoxIsRecurring)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(savebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void savebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savebtnActionPerformed
        try {
            boolean recurring = false;
            if (this.jCheckBoxIsRecurring.isSelected()) {
                recurring = true;
            }
            String username = nametxt.getText();
            if (username == null || username.equals("")) {
                throw new Exception("Please input client username first!");
            }
            String dateString = this.datetxt.getText();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

            Date date = df.parse(dateString);

            String startHours = this.starthouropt.getSelectedItem().toString();
            String startMinutes = this.startminuteopt.getSelectedItem().toString();
            String endHours = this.endhouropt.getSelectedItem().toString();
            String endMinutes = this.endminuteopt.getSelectedItem().toString();
            Time timeStart = Time.valueOf(startHours + ":" + startMinutes + ":00");
            Time timeEnd = Time.valueOf(endHours + ":" + endMinutes + ":00");
            if (timeStart.compareTo(timeEnd) >= 0) {
                throw new Exception("End time must be after the start time!");
            }
            String doctorUsername = (String) this.jComboBoxDoctor.getSelectedItem();
            Appointment newAppointment = new Appointment(username, doctorUsername, date, timeStart, timeEnd, recurring);
            boolean isSuccess = this.mainPanel.databaseController.createAppointment(newAppointment);
            if (isSuccess) {
                notifyAllListeners();
                JOptionPane.showMessageDialog(null, "Appointment has been created!", "Success!", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Something is wrong!", "Warning!", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(CreateClientAppointPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_savebtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton calendarbtn;
    private javax.swing.JTextField datetxt;
    private javax.swing.JComboBox<String> endhouropt;
    private javax.swing.JComboBox<String> endminuteopt;
    private View.GSCalendarPanel gSCalendarPanel1;
    private javax.swing.JCheckBox jCheckBoxIsRecurring;
    private javax.swing.JComboBox<String> jComboBoxDoctor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField nametxt;
    private javax.swing.JButton savebtn;
    private javax.swing.JComboBox<String> starthouropt;
    private javax.swing.JComboBox<String> startminuteopt;
    // End of variables declaration//GEN-END:variables

}
