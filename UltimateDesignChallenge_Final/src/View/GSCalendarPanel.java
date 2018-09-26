package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

public class GSCalendarPanel extends javax.swing.JPanel implements Observable {

    public GSCalendarPanel() {
        initComponents();
        execute();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtMounth = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cmdNext = new javax.swing.JButton();
        cmdBack = new javax.swing.JButton();
        cmdDown = new javax.swing.JButton();
        cmdUp = new javax.swing.JButton();
        lbText = new javax.swing.JLabel();
        txtYear = new javax.swing.JTextField();
        cmdToday = new javax.swing.JButton();
        body = new javax.swing.JLayeredPane();

        txtMounth.setForeground(new java.awt.Color(68, 68, 68));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(225, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sun");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(52, 51, 51));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Fri");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(52, 51, 51));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Sat");
        jLabel7.setPreferredSize(new java.awt.Dimension(12, 14));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(52, 51, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Mon");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(52, 51, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Tue");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(52, 51, 51));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Wed");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(52, 51, 51));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Thu");

        cmdNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/next.png"))); // NOI18N
        cmdNext.setContentAreaFilled(false);
        cmdNext.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdNext.setFocusable(false);
        cmdNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cmdNextMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmdNextMouseReleased(evt);
            }
        });
        cmdNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdNextActionPerformed(evt);
            }
        });

        cmdBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/back.png"))); // NOI18N
        cmdBack.setContentAreaFilled(false);
        cmdBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdBack.setFocusable(false);
        cmdBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cmdBackMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmdBackMouseReleased(evt);
            }
        });
        cmdBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBackActionPerformed(evt);
            }
        });

        cmdDown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/icon_down.png"))); // NOI18N
        cmdDown.setContentAreaFilled(false);
        cmdDown.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdDown.setFocusable(false);
        cmdDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdDownActionPerformed(evt);
            }
        });

        cmdUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/icon_up.PNG"))); // NOI18N
        cmdUp.setContentAreaFilled(false);
        cmdUp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdUp.setFocusable(false);
        cmdUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdUpActionPerformed(evt);
            }
        });

        lbText.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/text.PNG"))); // NOI18N

        txtYear.setBackground(new java.awt.Color(251, 251, 251));
        txtYear.setForeground(new java.awt.Color(68, 68, 68));
        txtYear.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtYear.setBorder(null);
        txtYear.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtYearKeyTyped(evt);
            }
        });

        cmdToday.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        cmdToday.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Pictures/today.png"))); // NOI18N
        cmdToday.setContentAreaFilled(false);
        cmdToday.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdToday.setFocusable(false);
        cmdToday.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cmdTodayMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cmdTodayMouseReleased(evt);
            }
        });
        cmdToday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdTodayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bodyLayout = new javax.swing.GroupLayout(body);
        body.setLayout(bodyLayout);
        bodyLayout.setHorizontalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        bodyLayout.setVerticalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 163, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(txtMounth, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(cmdBack, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(cmdNext, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(cmdToday, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(cmdUp, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(cmdDown, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbText))
                .addGap(0, 13, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel2)
                .addGap(6, 6, 6)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(body)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtMounth, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(cmdBack, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(cmdNext, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(cmdToday, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cmdUp, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(cmdDown, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(lbText)))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(body))
        );
    }// </editor-fold>//GEN-END:initComponents

    private ArrayList<JLabel> arr;
    private int day, month, year, d, m, y;
    private String type = "-";
    private String selectedDate;
    private int lenYear;
    private JTextField txt;
    private JPopupMenu pp;
    private Color bg = this.getBackground();
    private final String allMonth[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private String dateFormat = "yyyy-MM-dd";

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

    private void execute() {
        lenYear = 4;
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        String data[] = df.format(date).split("-");
        y = year = Integer.valueOf(data[0]);
        m = month = Integer.valueOf(data[1]);
        d = day = Integer.valueOf(data[2]);
        txtMounth.setText(allMonth[month - 1]);
        txtYear.setText(year + "");
        arr = new ArrayList<>();
        txt = new JTextField();
        pp = new JPopupMenu() {
            @Override
            protected void paintComponent(Graphics grphcs) {
                grphcs.setColor(new Color(114, 113, 113));
                grphcs.fillRect(0, 0, getWidth(), getHeight());
                grphcs.setColor(bg);
                grphcs.fillRect(1, 1, getWidth() - 2, getHeight() - 2);
            }
        };
        for (int i = 0; i < 31; i++) {
            SubLabel obj = new SubLabel((i + 1) + "");
            obj.setSize(35, 25);
            obj.setHorizontalAlignment(0);
            obj.setOpaque(true);
            obj.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            arr.add(obj);
        }
        setDate();
        Calendar c = Calendar.getInstance();
        c.set(year, month-1, day);
        DateFormat sdf = new SimpleDateFormat(dateFormat);
        selectedDate = sdf.format(c.getTime());
//        DecimalFormat f = new DecimalFormat("00");
//        selectedDate = f.format(month) + type + f.format(day) + type + year;
        txt.setText(selectedDate);
        notifyAllListeners();
    }

    private void showDate(Component obj, int x, int y) {
        pp.add(this);
        pp.show(obj, x, y);
    }

    public void showPopup() {
        if (pp.isVisible()) {
            pp.setVisible(false);
        } else {
            showDate(txt, 1, txt.getHeight());
        }
    }

    private void setDate() {
        body.removeAll();
        Calendar cd = Calendar.getInstance();
        cd.set(year, month - 1, 1);
        int start = cd.get(Calendar.DAY_OF_WEEK);
        int max = cd.getActualMaximum(Calendar.DAY_OF_MONTH);
        int x = 4, yy = 4;
        for (int i = 1; i < start; i++) {
            x += 36;
        }
        for (int i = 1; i <= max; i++) {
            JLabel obj = arr.get(i - 1);
            obj.setLocation(x, yy);
            if (start % 2 == 0) {
                obj.setBackground(new Color(229, 229, 229));
                obj.setForeground(new Color(72, 72, 72));
            } else {
                obj.setBackground(new Color(202, 202, 202));
                obj.setForeground(new Color(72, 72, 72));
            }
            if (start == 1) {
                obj.setBackground(new Color(211, 139, 150));
                obj.setForeground(new Color(189, 33, 33));
            }
            if (i == d && month == m && year == this.y) {
                obj.setBackground(new Color(95, 105, 99));
                obj.setForeground(new Color(236, 236, 236));
            }
            start++;
            x += 36;
            if (start == 8) {
                start = 1;
                x = 4;
                yy += 26;
            }
            body.add(obj);
        }
        body.revalidate();
        body.repaint();
    }

    private class SubLabel extends JLabel {

        public SubLabel() {
        }

        public SubLabel(String text) {
            super(text);
            addMouseListener(new MouseAdapter() {

                @Override
                public void mousePressed(MouseEvent e) {
                    evenMousePressed(e);
                }

                @Override
                public void mouseClicked(MouseEvent e) {
                    evenMouseClicked(e);
                }

            });
        }

        private void evenMousePressed(MouseEvent e) {
            setDate();
//            DecimalFormat f = new DecimalFormat("00");
            setBackground(new Color(138, 165, 186));
            day = Integer.valueOf(getText());
//            selectedDate = f.format(month) + type + f.format(day) + type + year;
            Calendar c = Calendar.getInstance();
            c.set(year, month-1, day);
            DateFormat sdf = new SimpleDateFormat(dateFormat);
            selectedDate = sdf.format(c.getTime());
            txt.setText(selectedDate);
            notifyAllListeners();
        }

        private void evenMouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
                pp.setVisible(false);
            }
        }
    }

    final public static int GET_MONTH = 1;
    final public static int GET_DAY = 2;
    final public static int GET_YEAR = 3;

    @Override
    public void setBackground(Color color) {
        super.setBackground(color);
        this.bg = color;
    }

    public void setGrabFocus() {
        txt.grabFocus();
    }

    public void setTextFocusable(boolean focusable) {
        txt.setFocusable(focusable);
    }

    public void showDialog(Component com, int x, int y) {
        showDate(com, x, y);
    }

    public void setTextRefernce(JTextField txt) {
        this.txt = txt;
        this.txt.setFocusable(false);
        this.txt.setText(selectedDate);
        notifyAllListeners();
    }

    public void setButton(JButton cmd) {
        cmd.setIcon(new ImageIcon("/Pictures/icon.png"));
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                showPopup();
            }
        });
    }

    public void setIntervalType(String type) {
        this.type = type;
        Calendar c = Calendar.getInstance();
        c.set(year, month-1, day);
        DateFormat sdf = new SimpleDateFormat(dateFormat);
        selectedDate = sdf.format(c.getTime());
        txt.setText(selectedDate);
        notifyAllListeners();
    }

    public String getSelectedDate() {
        return selectedDate;
    }

    public String getSelectedDate(int type) {
        if (type == 1) {
            return month + "";
        } else if (type == 2) {
            return day + "";
        } else if (type == 3) {
            return year + "";
        } else {
            return getSelectedDate();
        }
    }

    public int getLenYear() {
        return lenYear;
    }

    public void setLenYear(int lenYear) {
        this.lenYear = lenYear;
    }

    public String getText() {
        return txt.getText();
    }
    private void cmdNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdNextActionPerformed
        if (month == 12) {
            month = 1;
            txtYear.setText(year + 1 + "");
        } else {
            month++;
        }
        txtYear.setText(year + "");
        txtMounth.setText(allMonth[month - 1]);
        setDate();
    }//GEN-LAST:event_cmdNextActionPerformed

    private void setIcon(JButton cmd, String icon) {
        cmd.setIcon(new ImageIcon(getClass().getResource(icon)));
    }
    private void cmdBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBackActionPerformed
        if (month == 1) {
            month = 12;
            txtYear.setText(year - 1 + "");
        } else {
            month--;
        }
        txtYear.setText(year + "");
        txtMounth.setText(allMonth[month - 1]);
        setDate();
    }//GEN-LAST:event_cmdBackActionPerformed

    private void cmdDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdDownActionPerformed
        txtYear.setText(Integer.valueOf(txtYear.getText()) - 1 + "");
        if (arr != null) {
            year = Integer.valueOf(txtYear.getText());
            setDate();
        }
    }//GEN-LAST:event_cmdDownActionPerformed

    private void cmdUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdUpActionPerformed
        txtYear.setText(Integer.valueOf(txtYear.getText()) + 1 + "");
        if (arr != null) {
            year = Integer.valueOf(txtYear.getText());
            setDate();
        }
    }//GEN-LAST:event_cmdUpActionPerformed

    private void txtYearKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtYearKeyTyped
        if (evt.getKeyChar() == 10) {
            pp.setVisible(false);
            return;
        }
        if (!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != 8) {
            evt.consume();
        } else if (txtYear.getText().length() >= 4) {
            evt.consume();
        } else {
            if (!txtYear.getText().trim().equals("")) {
                if (evt.getKeyChar() == 8) {
                    year = Integer.valueOf(txtYear.getText());
                } else {
                    year = Integer.valueOf(txtYear.getText() + evt.getKeyChar());
                }
                setDate();
            }
        }
    }//GEN-LAST:event_txtYearKeyTyped

    private void cmdTodayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdTodayActionPerformed
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("MM" + type + "dd" + type + "YYYY");
        String data[] = df.format(date).split(type);
        m = month = Integer.valueOf(data[0]);
        d = day = Integer.valueOf(data[1]);
        y = year = Integer.valueOf(data[2]);
        txtMounth.setText(allMonth[month - 1]);
        txtYear.setText(year + "");
        setDate();
        Calendar c = Calendar.getInstance();
        c.set(year, month-1, day);
        DateFormat sdf = new SimpleDateFormat(dateFormat);
        selectedDate = sdf.format(c.getTime());
        txt.setText(selectedDate);
        notifyAllListeners();
    }//GEN-LAST:event_cmdTodayActionPerformed

    private void cmdNextMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdNextMouseReleased
        setIcon(cmdNext, "/Pictures/next.png");
    }//GEN-LAST:event_cmdNextMouseReleased

    private void cmdBackMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdBackMouseReleased
        setIcon(cmdBack, "/Pictures/back.png");
    }//GEN-LAST:event_cmdBackMouseReleased

    private void cmdBackMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdBackMousePressed
        setIcon(cmdBack, "/Pictures/back_click.png");
    }//GEN-LAST:event_cmdBackMousePressed

    private void cmdNextMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdNextMousePressed
        setIcon(cmdNext, "/Pictures/next_click.png");
    }//GEN-LAST:event_cmdNextMousePressed

    private void cmdTodayMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdTodayMousePressed
        setIcon(cmdToday, "/Pictures/today_click.png");
    }//GEN-LAST:event_cmdTodayMousePressed

    private void cmdTodayMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmdTodayMouseReleased
        setIcon(cmdToday, "/Pictures/today.png");
    }//GEN-LAST:event_cmdTodayMouseReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane body;
    private javax.swing.JButton cmdBack;
    private javax.swing.JButton cmdDown;
    private javax.swing.JButton cmdNext;
    private javax.swing.JButton cmdToday;
    private javax.swing.JButton cmdUp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lbText;
    private javax.swing.JLabel txtMounth;
    private javax.swing.JTextField txtYear;
    // End of variables declaration//GEN-END:variables

}
