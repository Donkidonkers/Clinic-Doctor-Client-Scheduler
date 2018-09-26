package View;

import Model.Database.User;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;

public class CalendarPanel extends javax.swing.JPanel implements ViewObservable, InvalidationListener {

    private MainPanel mainPanel;

    private User user;
    LinkedList<ViewObserver> listObserver;

    public void setMainPanel(MainPanel mainPanel) {
        try {
            this.mainPanel = mainPanel;
            List<User> doctors = this.mainPanel.databaseController.findUserByType("doctor");
            String doctorsString[] = new String[doctors.size() + 1];
            int counter = 1;
            doctorsString[0] = "All";
            for (User doctor : doctors) {
                doctorsString[counter] = doctor.getUsername();
                counter++;
            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(doctorsString);
            this.jComboBoxClientDoctor.setModel(model);
            this.jComboBoxSecretaryDoctor.setModel(model);
        } catch (SQLException ex) {
            Logger.getLogger(CalendarPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setButtonPanel(JPanel input) {
//        input.setMainPanel(this);
        this.jPanelButton.removeAll();
        this.jPanelButton.repaint();
        this.jPanelButton.revalidate();
        this.jPanelButton.add(input);
        this.jPanelButton.repaint();
        this.jPanelButton.revalidate();
    }

    @Override
    public void notifyAllObserver(JPanel newPanel) {
        listObserver.forEach((viewObserver) -> {
            viewObserver.updateDynamicPanel(newPanel);
        });
    }

    @Override
    public void addObserver(ViewObserver newObserver) {
        listObserver.add(newObserver);
    }

    @Override
    public void remove(ViewObserver toBeRemoved) {
        listObserver.remove(toBeRemoved);
    }

    public CalendarPanel() {
        initComponents();
        listObserver = new LinkedList<>();
        this.gSCalendarPanel.addListener(this);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    }

    public void setUser(User user) {
        this.user = user;
        switch (this.user.getType()) {
            case "doctor":
                this.setButtonPanel(this.doctorbtnsPanel);
                notifyAllObserver(this.mainPanel.getDoctorCard());
                break;
            case "client":
                this.setButtonPanel(this.clientbtnsPanel);
                notifyAllObserver(this.mainPanel.getCreateClientAppointPanel());
                break;
            case "secretary":
                this.setButtonPanel(this.secretarybtnsPanel);
                notifyAllObserver(this.mainPanel.getCreateSecretaryAppointPanel());
                break;
            default:
                System.exit(0);
        }
    }

    @Override
    public void invalidated(javafx.beans.Observable o) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            this.mainPanel.getDailyViewPanel().setDate(df.parse(this.dateText.getText()));
            this.mainPanel.getDailyAgendaPanel().setDate(df.parse(this.dateText.getText()));
            this.mainPanel.getWeeklyViewPanel().setDate(df.parse(this.dateText.getText()));
            this.mainPanel.getWeeklyAgendaPanel().setDate(df.parse(this.dateText.getText()));
        } catch (ParseException ex) {
            Logger.getLogger(CalendarPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jPanelButton = new javax.swing.JPanel();
        doctorbtnsPanel = new javax.swing.JPanel();
        createbSchedbtn = new javax.swing.JButton();
        editShedbtn = new javax.swing.JButton();
        secretarybtnsPanel = new javax.swing.JPanel();
        editSecretaryAppbtn = new javax.swing.JButton();
        addSecretaryAppbtn = new javax.swing.JButton();
        jComboBoxSecretaryDoctor = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        clientbtnsPanel = new javax.swing.JPanel();
        addClientAppbtn = new javax.swing.JButton();
        editAppClientbtn = new javax.swing.JButton();
        jComboBoxClientDoctor = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        dateText = new javax.swing.JTextField();
        gSCalendarPanel = new View.GSCalendarPanel();
        todayagendabtn = new javax.swing.JRadioButton();
        dailyviewbtn = new javax.swing.JRadioButton();
        weeklyviewbtn = new javax.swing.JRadioButton();
        weeklyagendabtn = new javax.swing.JRadioButton();

        setMaximumSize(new java.awt.Dimension(280, 524));
        setMinimumSize(new java.awt.Dimension(280, 524));
        setPreferredSize(new java.awt.Dimension(280, 524));
        setRequestFocusEnabled(false);

        jPanel2.setBackground(new java.awt.Color(0, 76, 112));
        jPanel2.setMaximumSize(new java.awt.Dimension(280, 524));
        jPanel2.setMinimumSize(new java.awt.Dimension(280, 524));
        jPanel2.setPreferredSize(new java.awt.Dimension(280, 524));

        jPanelButton.setLayout(new java.awt.CardLayout());

        doctorbtnsPanel.setBackground(new java.awt.Color(0, 76, 112));

        createbSchedbtn.setBackground(new java.awt.Color(255, 255, 255));
        createbSchedbtn.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 12)); // NOI18N
        createbSchedbtn.setText("Create");
        createbSchedbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createbSchedbtnActionPerformed(evt);
            }
        });

        editShedbtn.setBackground(new java.awt.Color(255, 255, 255));
        editShedbtn.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 12)); // NOI18N
        editShedbtn.setText("Edit");
        editShedbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editShedbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout doctorbtnsPanelLayout = new javax.swing.GroupLayout(doctorbtnsPanel);
        doctorbtnsPanel.setLayout(doctorbtnsPanelLayout);
        doctorbtnsPanelLayout.setHorizontalGroup(
            doctorbtnsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(doctorbtnsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(createbSchedbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(editShedbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        doctorbtnsPanelLayout.setVerticalGroup(
            doctorbtnsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(doctorbtnsPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(doctorbtnsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createbSchedbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editShedbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jPanelButton.add(doctorbtnsPanel, "card2");

        secretarybtnsPanel.setBackground(new java.awt.Color(0, 76, 112));

        editSecretaryAppbtn.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 12)); // NOI18N
        editSecretaryAppbtn.setText("Edit Appointments");
        editSecretaryAppbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editSecretaryAppbtnActionPerformed(evt);
            }
        });

        addSecretaryAppbtn.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 12)); // NOI18N
        addSecretaryAppbtn.setText("Add Walk-Ins");
        addSecretaryAppbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSecretaryAppbtnActionPerformed(evt);
            }
        });

        jComboBoxSecretaryDoctor.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 12)); // NOI18N
        jComboBoxSecretaryDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSecretaryDoctorActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Malgun Gothic Semilight", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Doctor Filter");

        javax.swing.GroupLayout secretarybtnsPanelLayout = new javax.swing.GroupLayout(secretarybtnsPanel);
        secretarybtnsPanel.setLayout(secretarybtnsPanelLayout);
        secretarybtnsPanelLayout.setHorizontalGroup(
            secretarybtnsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(secretarybtnsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(secretarybtnsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addSecretaryAppbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(secretarybtnsPanelLayout.createSequentialGroup()
                        .addComponent(editSecretaryAppbtn)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(secretarybtnsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxSecretaryDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap())
        );
        secretarybtnsPanelLayout.setVerticalGroup(
            secretarybtnsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(secretarybtnsPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(secretarybtnsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addSecretaryAppbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(secretarybtnsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editSecretaryAppbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxSecretaryDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelButton.add(secretarybtnsPanel, "card2");

        clientbtnsPanel.setBackground(new java.awt.Color(0, 76, 112));
        clientbtnsPanel.setPreferredSize(new java.awt.Dimension(260, 122));

        addClientAppbtn.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 12)); // NOI18N
        addClientAppbtn.setText("Add Appointment");
        addClientAppbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addClientAppbtnActionPerformed(evt);
            }
        });

        editAppClientbtn.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 12)); // NOI18N
        editAppClientbtn.setText("Edit Appointment");
        editAppClientbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editAppClientbtnActionPerformed(evt);
            }
        });

        jComboBoxClientDoctor.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 12)); // NOI18N
        jComboBoxClientDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxClientDoctorActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 76, 112));
        jLabel1.setFont(new java.awt.Font("Malgun Gothic Semilight", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Doctor Filter");

        javax.swing.GroupLayout clientbtnsPanelLayout = new javax.swing.GroupLayout(clientbtnsPanel);
        clientbtnsPanel.setLayout(clientbtnsPanelLayout);
        clientbtnsPanelLayout.setHorizontalGroup(
            clientbtnsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clientbtnsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(clientbtnsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(editAppClientbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addClientAppbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(clientbtnsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(clientbtnsPanelLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jComboBoxClientDoctor, 0, 101, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(clientbtnsPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        clientbtnsPanelLayout.setVerticalGroup(
            clientbtnsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clientbtnsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(clientbtnsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addClientAppbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(clientbtnsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editAppClientbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxClientDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanelButton.add(clientbtnsPanel, "card2");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("View List");

        dateText.setBackground(new java.awt.Color(0, 76, 112));
        dateText.setFont(new java.awt.Font("Malgun Gothic", 1, 18)); // NOI18N
        dateText.setForeground(new java.awt.Color(255, 255, 255));
        dateText.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        gSCalendarPanel.setTextRefernce(dateText);

        todayagendabtn.setBackground(new java.awt.Color(0, 76, 112));
        buttonGroup1.add(todayagendabtn);
        todayagendabtn.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 16)); // NOI18N
        todayagendabtn.setForeground(new java.awt.Color(255, 255, 255));
        todayagendabtn.setText("Daily Agenda");
        todayagendabtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                todayagendabtnActionPerformed(evt);
            }
        });

        dailyviewbtn.setBackground(new java.awt.Color(0, 76, 112));
        buttonGroup1.add(dailyviewbtn);
        dailyviewbtn.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 16)); // NOI18N
        dailyviewbtn.setForeground(new java.awt.Color(255, 255, 255));
        dailyviewbtn.setSelected(true);
        dailyviewbtn.setText("Daily View");
        dailyviewbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dailyviewbtnActionPerformed(evt);
            }
        });

        weeklyviewbtn.setBackground(new java.awt.Color(0, 76, 112));
        buttonGroup1.add(weeklyviewbtn);
        weeklyviewbtn.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 16)); // NOI18N
        weeklyviewbtn.setForeground(new java.awt.Color(255, 255, 255));
        weeklyviewbtn.setText("Weekly View");
        weeklyviewbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                weeklyviewbtnActionPerformed(evt);
            }
        });

        weeklyagendabtn.setBackground(new java.awt.Color(0, 76, 112));
        buttonGroup1.add(weeklyagendabtn);
        weeklyagendabtn.setFont(new java.awt.Font("Malgun Gothic Semilight", 0, 16)); // NOI18N
        weeklyagendabtn.setForeground(new java.awt.Color(255, 255, 255));
        weeklyagendabtn.setText("Weekly Agenda");
        weeklyagendabtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                weeklyagendabtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(83, 83, 83)
                                .addComponent(jLabel9))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(todayagendabtn)
                                    .addComponent(dailyviewbtn))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(weeklyviewbtn)
                                    .addComponent(weeklyagendabtn))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanelButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dateText, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(gSCalendarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(278, 278, 278))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dateText, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gSCalendarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelButton, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(todayagendabtn)
                    .addComponent(weeklyagendabtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dailyviewbtn)
                    .addComponent(weeklyviewbtn))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void todayagendabtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_todayagendabtnActionPerformed
        notifyAllObserver(this.mainPanel.getAgendaPanel());
        //updateAgendaViewData();
    }//GEN-LAST:event_todayagendabtnActionPerformed

    private void dailyviewbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dailyviewbtnActionPerformed
        notifyAllObserver(this.mainPanel.getDailyViewPanel());
        //     updateDayTableData();
    }//GEN-LAST:event_dailyviewbtnActionPerformed

    private void weeklyviewbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_weeklyviewbtnActionPerformed
        
        notifyAllObserver(this.mainPanel.getWeeklyViewPanel());
    }//GEN-LAST:event_weeklyviewbtnActionPerformed

    private void createbSchedbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createbSchedbtnActionPerformed
        // TODO add your handling code here:
        notifyAllObserver(this.mainPanel.getDoctorCard());
    }//GEN-LAST:event_createbSchedbtnActionPerformed

    private void editShedbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editShedbtnActionPerformed
        // TODO add your handling code here:
        this.mainPanel.getEditPanel().refreshTable();
        notifyAllObserver(this.mainPanel.getEditPanel());
    }//GEN-LAST:event_editShedbtnActionPerformed

    private void editSecretaryAppbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editSecretaryAppbtnActionPerformed
        // TODO add your handling code here:
        this.mainPanel.getEditAppointmentPanel().refreshTable();
        notifyAllObserver(this.mainPanel.getEditAppointmentPanel());
    }//GEN-LAST:event_editSecretaryAppbtnActionPerformed

    private void addSecretaryAppbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSecretaryAppbtnActionPerformed
        // TODO add your handling code here:
        notifyAllObserver(this.mainPanel.getCreateSecretaryAppointPanel());
    }//GEN-LAST:event_addSecretaryAppbtnActionPerformed

    private void addClientAppbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addClientAppbtnActionPerformed
        // TODO add your handling code here:
        notifyAllObserver(this.mainPanel.getCreateClientAppointPanel());
    }//GEN-LAST:event_addClientAppbtnActionPerformed

    private void editAppClientbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editAppClientbtnActionPerformed
        // TODO add your handling code here:
        this.mainPanel.getRemoveAppointmentPanel().refreshTable();
        notifyAllObserver(this.mainPanel.getRemoveAppointmentPanel());
    }//GEN-LAST:event_editAppClientbtnActionPerformed

    private void weeklyagendabtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_weeklyagendabtnActionPerformed

        this.mainPanel.getWeeklyAgendaPanel().updateAgendaViewData();
        notifyAllObserver(this.mainPanel.getWeeklyAgendaPanel());
    }//GEN-LAST:event_weeklyagendabtnActionPerformed

    private void jComboBoxClientDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxClientDoctorActionPerformed
        String selected = (String) this.jComboBoxClientDoctor.getSelectedItem();
        if (selected.equals("All")) {
            this.mainPanel.getDailyViewPanel().setDoctorFilter(null);
            this.mainPanel.getWeeklyViewPanel().setDoctorFilter(null);
        } else {
            this.mainPanel.getDailyViewPanel().setDoctorFilter((String) this.jComboBoxClientDoctor.getSelectedItem());
            this.mainPanel.getWeeklyViewPanel().setDoctorFilter((String) this.jComboBoxClientDoctor.getSelectedItem());
        }
    }//GEN-LAST:event_jComboBoxClientDoctorActionPerformed

    private void jComboBoxSecretaryDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSecretaryDoctorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxSecretaryDoctorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addClientAppbtn;
    private javax.swing.JButton addSecretaryAppbtn;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel clientbtnsPanel;
    private javax.swing.JButton createbSchedbtn;
    private javax.swing.JRadioButton dailyviewbtn;
    private javax.swing.JTextField dateText;
    private javax.swing.JPanel doctorbtnsPanel;
    private javax.swing.JButton editAppClientbtn;
    private javax.swing.JButton editSecretaryAppbtn;
    private javax.swing.JButton editShedbtn;
    private View.GSCalendarPanel gSCalendarPanel;
    private javax.swing.JComboBox<String> jComboBoxClientDoctor;
    private javax.swing.JComboBox<String> jComboBoxSecretaryDoctor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelButton;
    private javax.swing.JPanel secretarybtnsPanel;
    private javax.swing.JRadioButton todayagendabtn;
    private javax.swing.JRadioButton weeklyagendabtn;
    private javax.swing.JRadioButton weeklyviewbtn;
    // End of variables declaration//GEN-END:variables

}
