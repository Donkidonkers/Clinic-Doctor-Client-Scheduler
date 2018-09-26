package View.Client;

import Model.Database.Appointment;
import Model.Database.User;
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

public class CreateClientAppointPanel extends javax.swing.JPanel implements DynamicPanel, Observable {

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

    public CreateClientAppointPanel() {
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
        jLabel11 = new javax.swing.JLabel();
        jComboBoxDoctor = new javax.swing.JComboBox<>();
        jCheckBoxIsRecurring = new javax.swing.JCheckBox();

        gSCalendarPanel1.setButton(calendarbtn);
        gSCalendarPanel1.setTextFocusable(true);
        gSCalendarPanel1.setTextRefernce(datetxt);

        setPreferredSize(new java.awt.Dimension(580, 502));

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

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel11.setText("Doctor");

        jComboBoxDoctor.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 14)); // NOI18N

        jCheckBoxIsRecurring.setText("Recurring");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jCheckBoxIsRecurring)
                        .addGap(250, 250, 250))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(savebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(227, 227, 227))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(endminuteopt, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addGap(71, 71, 71))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(96, 96, 96))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(datetxt, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(calendarbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(51, 51, 51)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(55, 55, 55))
                            .addComponent(jComboBoxDoctor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(58, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(76, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(calendarbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(datetxt)
                    .addComponent(jComboBoxDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
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
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(endhouropt, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(endminuteopt, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8)))))
                .addGap(52, 52, 52)
                .addComponent(jCheckBoxIsRecurring)
                .addGap(45, 45, 45)
                .addComponent(savebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void savebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savebtnActionPerformed
        try {
            boolean recurring = false;
            if (this.jCheckBoxIsRecurring.isSelected()) {
                recurring = true;
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
            Appointment newAppointment = new Appointment(this.mainPanel.getUser().getUsername(), doctorUsername, date, timeStart, timeEnd, recurring);
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
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton savebtn;
    private javax.swing.JComboBox<String> starthouropt;
    private javax.swing.JComboBox<String> startminuteopt;
    // End of variables declaration//GEN-END:variables

}
