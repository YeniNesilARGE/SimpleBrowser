package yeninesilarge.calendarapp;

/*
 Enes Kamil YILMAZ
 FSM Vakıf University
 Computer Engineering
 3rd Grade
 1521221039
 */
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.swing.JOptionPane;

public class AgendaFrame extends javax.swing.JFrame {

    boolean allday;
    static boolean isOpen;
    String timefrom = "-", timeto = "-";
    static String hourf, minf, hour2, min2, day2, month2, year2, timefromDate, timetoDate, time2time;
    static final String LINE_SEP = System.getProperty("line.separator");
    static int realYear, realMonth, realDay;
    static List<LocalDate> dates;

    public AgendaFrame() {
        initComponents();
        System.out.println(LINE_SEP);
        isOpen = true;
        this.setTitle("                 Add Agenda");
        //bu frame kapandığında ana frame in kapanmasını önler.
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                isOpen = false;
            }
        });

        this.setResizable(false);
        this.setLocation(1250, 200);

        //CheckBox lardan sadece birinin seçili olması için
        buttonGroup1.add(chckbxAnniversary);
        buttonGroup1.add(chckbxBirtday);
        buttonGroup1.add(chkbxEvent);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        chkbxEvent = new javax.swing.JCheckBox();
        chckbxBirtday = new javax.swing.JCheckBox();
        chckbxAnniversary = new javax.swing.JCheckBox();
        txtEventName = new javax.swing.JTextField();
        timeFrom = new javax.swing.JButton();
        timeTo = new javax.swing.JButton();
        chcbxAllDay = new javax.swing.JCheckBox();
        txtLoc = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescp = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        btnDone = new javax.swing.JButton();
        cmbColor = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        cmbReminder = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        chkbxEvent.setText("Event");
        chkbxEvent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkbxEventActionPerformed(evt);
            }
        });

        chckbxBirtday.setText("Birtday");
        chckbxBirtday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chckbxBirtdayActionPerformed(evt);
            }
        });

        chckbxAnniversary.setText("Anniversary");
        chckbxAnniversary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chckbxAnniversaryActionPerformed(evt);
            }
        });

        txtEventName.setText("...Event Name");
        txtEventName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtEventNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEventNameFocusLost(evt);
            }
        });

        timeFrom.setText("Time From");
        timeFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeFromActionPerformed(evt);
            }
        });

        timeTo.setText(" Time To");
        timeTo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeToActionPerformed(evt);
            }
        });

        chcbxAllDay.setText("All Day");
        chcbxAllDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chcbxAllDayActionPerformed(evt);
            }
        });

        txtLoc.setText("...Location");
        txtLoc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtLocFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtLocFocusLost(evt);
            }
        });

        txtDescp.setColumns(20);
        txtDescp.setRows(5);
        txtDescp.setText("...Description");
        txtDescp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDescpFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDescpFocusLost(evt);
            }
        });
        jScrollPane2.setViewportView(txtDescp);

        jLabel1.setText("Add Reminder =>");

        btnDone.setText("Done");
        btnDone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoneActionPerformed(evt);
            }
        });

        cmbColor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Red", "Blue", "Green", "Grey" }));
        cmbColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbColorActionPerformed(evt);
            }
        });

        jLabel2.setText("Choose Color =>");

        cmbReminder.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "None", "1 Day Before", "2 Day Before", "3 Day Before", "4 Day Before", "1 Week Before", "2 Week Before" }));
        cmbReminder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbReminderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnDone)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(chcbxAllDay)
                                    .addGap(98, 98, 98))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(chkbxEvent)
                                    .addGap(18, 18, 18)
                                    .addComponent(chckbxBirtday)
                                    .addGap(18, 18, 18)
                                    .addComponent(chckbxAnniversary))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(3, 3, 3)
                                            .addComponent(cmbReminder, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(timeFrom)
                                                    .addGap(18, 18, 18))
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                    .addComponent(jLabel2)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(6, 6, 6)
                                                    .addComponent(cmbColor, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(timeTo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(txtEventName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkbxEvent)
                    .addComponent(chckbxBirtday)
                    .addComponent(chckbxAnniversary))
                .addGap(9, 9, 9)
                .addComponent(txtEventName, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chcbxAllDay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(timeTo)
                    .addComponent(timeFrom))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbReminder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDone)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    void addToData() throws IOException {

        if (chcbxAllDay.isSelected()) {
            allday = true;
            timefrom = "-";
            timeto = "-";
        } else {
            allday = false;
            if (timefrom.equals("") || timeto.equals("")) {
                JOptionPane.showMessageDialog(this, "Please log in properly", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (txtLoc.equals("...Location")) {
            txtLoc.setText("-");
        }
        File f = new File("Agenda.txt");
        FileWriter fw = new FileWriter(f, true);
        BufferedWriter bw = new BufferedWriter(fw);

        long id = 0;
        Random generator = new Random();
        id = generator.nextInt(99999999) + 1;

        int yıl = Integer.parseInt(CalendarApp.choosenDate.substring(CalendarApp.choosenDate.length() - 2, CalendarApp.choosenDate.length()));

        //timetoDate,timefromDate
        LocalDate localdate = null;
        String date3;

        if (dates == null) {
            JOptionPane.showMessageDialog(this, "Please enter Time To variable", "Error", JOptionPane.ERROR_MESSAGE);
        }

        for (int i = 0; i < dates.size(); i++) {
            localdate = dates.get(i);
            date3 = DateTimeFormatter.ofPattern("d.M.yyyy").format(localdate);
            System.out.println(date3);
        }
        if (dates.size() != 0) {
            for (int i = 0; i < dates.size(); i++) {
                localdate = dates.get(i);
                date3 = DateTimeFormatter.ofPattern("d.M.yyyy").format(localdate);
                if (dates.get(i) == dates.get(dates.size() - 1)) {
                    bw.write(LINE_SEP + id + ",   " + "Event" + ",   " + txtEventName.getText().toString() + ",   " + date3 + ",   " + allday + ",   " + timefrom + ",   " + time2time + ",   " + cmbColor.getSelectedItem().toString()
                            + ",   " + txtLoc.getText().toString() + ",   " + txtDescp.getText().toString() + ",   " + cmbReminder.getSelectedItem().toString());
                    System.out.println("Event eklendi");
                } else {
                    bw.write(LINE_SEP + id + ",   " + "Event" + ",   " + txtEventName.getText().toString() + ",   " + date3 + ",   " + allday + ",   " + timefrom + ",   " + "-" + ",   " + cmbColor.getSelectedItem().toString()
                            + ",   " + txtLoc.getText().toString() + ",   " + txtDescp.getText().toString() + ",   " + cmbReminder.getSelectedItem().toString());
                    System.out.println("Event eklendi");
                }
                System.out.println(date3);

            }
            bw.close();
        } else {

            if (chkbxEvent.isSelected()) {
                bw.write(LINE_SEP + id + ",   " + "Event" + ",   " + txtEventName.getText().toString() + ",   " + CalendarApp.choosenDate + ",   " + allday + ",   " + timefrom + ",   " + time2time + ",   " + cmbColor.getSelectedItem().toString()
                        + ",   " + txtLoc.getText().toString() + ",   " + txtDescp.getText().toString() + ",   " + cmbReminder.getSelectedItem().toString());
                bw.close();
                System.out.println("Event eklendi");
            } else if (chckbxBirtday.isSelected()) {
                for (yıl = 17; yıl <= 27; yıl++) {
                    id = generator.nextInt(99999999) + 1;
                    CalendarApp.choosenDate = CalendarApp.choosenDate.substring(0, CalendarApp.choosenDate.length() - 2) + yıl;
                    bw.write(LINE_SEP + id + ",   " + "Birtday" + ",   " + txtEventName.getText().toString() + ",   " + CalendarApp.choosenDate + ",   " + allday + ",   " + timefrom + ",   " + time2time + ",   " + cmbColor.getSelectedItem().toString()
                            + ",   " + "" + ",   " + txtDescp.getText().toString() + ",   " + cmbReminder.getSelectedItem().toString());
                }

                bw.close();

                System.out.println("Birtdayler eklendi");
            } else if (chckbxAnniversary.isSelected()) {
                for (yıl = 17; yıl <= 27; yıl++) {
                    id = generator.nextInt(99999999) + 1;
                    CalendarApp.choosenDate = CalendarApp.choosenDate.substring(0, CalendarApp.choosenDate.length() - 2) + yıl;
                    bw.write(LINE_SEP + id + ",   " + "Anniversary" + ",   " + txtEventName.getText().toString() + ",   " + CalendarApp.choosenDate + ",   " + allday + ",   " + timefrom + ",   " + time2time + ",   " + cmbColor.getSelectedItem().toString()
                            + ",   " + txtLoc.getText().toString() + ",   " + txtDescp.getText().toString() + ",   " + cmbReminder.getSelectedItem().toString());
                }
                bw.close();
                System.out.println("Anniversaryler eklendi");
            }
        }
    }

    private void timeFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeFromActionPerformed
        timefrom = (String) JOptionPane.showInputDialog(this, "Please log in to the appropriate format. \n Example : 13:45 or 03:30 \n Date : " + CalendarApp.choosenDate, "Enter time from...", JOptionPane.INFORMATION_MESSAGE, null, null, "");
        int h = 0, m = 0;
        System.out.println(timefrom.length() + "  " + timefrom.charAt(2));
        if (timefrom.length() == 5 && timefrom.charAt(2) == ':') {
            hourf = timefrom.substring(0, 2);
            minf = timefrom.substring(3, 5);
            System.out.println(hourf + " " + minf);
            h = Integer.parseInt(hourf);
            m = Integer.parseInt(minf);
        } else if (timefrom.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please log in", "Time from: Null Exp", JOptionPane.ERROR_MESSAGE);
        } else {
            if (m > 60) {
                JOptionPane.showMessageDialog(this, "Minute value cannot be bigger than 60", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (h > 24) {
                JOptionPane.showMessageDialog(this, "Hour value cannot be bigger than 24", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Please log in to the appropriate format", "Incorrect format", JOptionPane.ERROR_MESSAGE);
                timefrom = "-";
            }
        }
    }//GEN-LAST:event_timeFromActionPerformed

    private void btnDoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoneActionPerformed

        if (chkbxEvent.isSelected() || chckbxBirtday.isSelected() || chckbxAnniversary.isSelected()) {
            if (!txtEventName.getText().equals("...Event Name")) {

                try {
                    addToData();
                    CalendarApp.readAgenda();
                    File f = new File(CalendarApp.txtName);
                    FileWriter fw = new FileWriter(f, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    bw.write(" ");
                } catch (IOException ex) {
                    Logger.getLogger(AgendaFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.dispose();
                isOpen = false;

            }
        } else {
            JOptionPane.showMessageDialog(this, "Please log in properly", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnDoneActionPerformed

    private void txtDescpFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDescpFocusLost
        // TODO add your handling code here:
        if (txtDescp.getText().equals("")) {
            txtDescp.setText("...Description");
        }
    }//GEN-LAST:event_txtDescpFocusLost

    private void txtEventNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEventNameFocusLost
        // TODO add your handling code here:
        if (txtEventName.getText().equals("")) {
            txtEventName.setText("...Event Name");
        }
    }//GEN-LAST:event_txtEventNameFocusLost

    private void txtLocFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLocFocusLost
        // TODO add your handling code here:
        if (txtLoc.getText().equals("")) {
            txtLoc.setText("...Location");
        }
    }//GEN-LAST:event_txtLocFocusLost

    private void chcbxAllDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chcbxAllDayActionPerformed
        // TODO add your handling code here:
        if (chcbxAllDay.isSelected()) {
            timeFrom.setEnabled(false);
            //timeTo.setEnabled(false);
        } else {
            timeFrom.setEnabled(true);
            //timeTo.setEnabled(true);
        }
    }//GEN-LAST:event_chcbxAllDayActionPerformed

    private void txtEventNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEventNameFocusGained
        // TODO add your handling code here:
        if (txtEventName.getText().equals("...Event Name")) {
            txtEventName.setText("");
        }
    }//GEN-LAST:event_txtEventNameFocusGained

    private void txtLocFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLocFocusGained
        // TODO add your handling code here:
        if (txtLoc.getText().equals("...Location")) {
            txtLoc.setText("");
        }
    }//GEN-LAST:event_txtLocFocusGained

    private void txtDescpFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDescpFocusGained
        // TODO add your handling code here:
        if (txtDescp.getText().equals("...Description")) {
            txtDescp.setText("");
        }
    }//GEN-LAST:event_txtDescpFocusGained

    private void chckbxAnniversaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chckbxAnniversaryActionPerformed
        // TODO add your handling code here:
        if (chckbxAnniversary.isSelected()) {
            chcbxAllDay.setSelected(true);
            chcbxAllDay.setEnabled(false);
            timeFrom.setEnabled(false);
            timeTo.setEnabled(false);
            txtLoc.setEnabled(false);
        }
    }//GEN-LAST:event_chckbxAnniversaryActionPerformed

    private void chckbxBirtdayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chckbxBirtdayActionPerformed
        // TODO add your handling code here:
        if (chckbxBirtday.isSelected()) {
            chcbxAllDay.setSelected(true);
            chcbxAllDay.setEnabled(false);
            timeFrom.setEnabled(false);
            timeTo.setEnabled(false);
            txtLoc.setEnabled(false);
        }
    }//GEN-LAST:event_chckbxBirtdayActionPerformed

    private void chkbxEventActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkbxEventActionPerformed
        // TODO add your handling code here:
        if (chkbxEvent.isSelected()) {
            chcbxAllDay.setSelected(false);
            chcbxAllDay.setEnabled(true);
            timeFrom.setEnabled(true);
            timeTo.setEnabled(true);
            txtLoc.setEnabled(true);
        }
    }//GEN-LAST:event_chkbxEventActionPerformed

    public static List<LocalDate> getDatesBetweenUsingJava8(LocalDate startDate, LocalDate endDate) {
        long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate.plusDays(1));
        return IntStream.iterate(0, i -> i + 1).limit(numOfDaysBetween).mapToObj(i -> startDate.plusDays(i)).collect(Collectors.toList());
    }

    private void timeToActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeToActionPerformed

        if (chcbxAllDay.isSelected()) {//sadece tarih
            timeto = (String) JOptionPane.showInputDialog(this, "Please log in to the appropriate format. \n Example : 12.12.2017 or 1.1.2018", "Enter time from...", JOptionPane.INFORMATION_MESSAGE, null, null, CalendarApp.choosenDate);
            int day = 0, month = 0, year = 0, day2 = 0, month2 = 0, year2 = 0;

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
            LocalDate localDate = LocalDate.parse(CalendarApp.choosenDate, formatter);
            //System.out.println(localDate);
            String g, a, y;
            g = DateTimeFormatter.ofPattern("d").format(localDate);
            a = DateTimeFormatter.ofPattern("M").format(localDate);
            y = DateTimeFormatter.ofPattern("yyyy").format(localDate);
            day = Integer.parseInt(g);
            month = Integer.parseInt(a);
            year = Integer.parseInt(y);

            LocalDate localDate2 = LocalDate.parse(timeto, formatter);
            //System.out.println(localDate2);

            String gün, ay, yıl;
            gün = DateTimeFormatter.ofPattern("d").format(localDate2);
            ay = DateTimeFormatter.ofPattern("M").format(localDate2);
            yıl = DateTimeFormatter.ofPattern("yyyy").format(localDate2);

            day2 = Integer.parseInt(gün);
            month2 = Integer.parseInt(ay);
            year2 = Integer.parseInt(yıl);

            if (timeto.length() >= 8 && day <= 31 && month <= 12 && year >= 2017 && year < 2028 && timeto.isEmpty() == false) {
                if (year2 >= year) {//day2 >= day && month2 >= month && year2 >= year
                    //timeto doğru sıkıntı yok
                    dates = getDatesBetweenUsingJava8(localDate, localDate2);
                } else {
                    //todo bir sonraki aydan değer verince buraya düştü
                    JOptionPane.showMessageDialog(this, "Please log in a date that bigger or equal to choosen date", "Error", JOptionPane.ERROR_MESSAGE);
                    timeto = "-";
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please log in to the appropriate format", "Error", JOptionPane.ERROR_MESSAGE);
                timeto = "-";
            }
        } else { //tarih ve saat
            int h = 0, m = 0;
            timeto = (String) JOptionPane.showInputDialog(this, "Please log in to the appropriate format. \n Example : 12.12.2017 , 13:50 or 1.1.2018 , 03:45", "Enter time to...", JOptionPane.INFORMATION_MESSAGE, null, null, CalendarApp.choosenDate + " , ");
            String[] time2 = null;

            String g = "", a = "", y = "";
            String gün = "", ay = "", yıl = "";
            int day = 0, month = 0, year = 0, day2 = 0, month2 = 0, year2 = 0;

            if (timeto.length() > 0 && timeto.length() > 10) {
                time2 = timeto.split(" , ");
                timetoDate = time2[0];
                time2time = time2[1];

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
                LocalDate localDate = LocalDate.parse(CalendarApp.choosenDate, formatter);
                //System.out.println(localDate);

                g = DateTimeFormatter.ofPattern("d").format(localDate);
                a = DateTimeFormatter.ofPattern("M").format(localDate);
                y = DateTimeFormatter.ofPattern("yyyy").format(localDate);
                day = Integer.parseInt(g);
                month = Integer.parseInt(a);
                year = Integer.parseInt(y);

                LocalDate localDate2 = LocalDate.parse(timetoDate, formatter);
                //System.out.println(localDate2);

                gün = DateTimeFormatter.ofPattern("d").format(localDate2);
                ay = DateTimeFormatter.ofPattern("M").format(localDate2);
                yıl = DateTimeFormatter.ofPattern("yyyy").format(localDate2);

                day2 = Integer.parseInt(gün);
                month2 = Integer.parseInt(ay);
                year2 = Integer.parseInt(yıl);
                if (timeto.length() >= 8 && day <= 31 && month <= 12 && year >= 2017 && year < 2028 && timeto.isEmpty() == false) {
                    if (day2 >= day && month2 >= month && year2 >= year) {
                        //timeto doğru sıkıntı yok
                        dates = getDatesBetweenUsingJava8(localDate, localDate2);
                    } else {
                        JOptionPane.showMessageDialog(this, "Please log in a date that bigger or equal to choosen date", "Error", JOptionPane.ERROR_MESSAGE);
                        timeto = "-";
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Please log in to the appropriate format", "Error", JOptionPane.ERROR_MESSAGE);
                    timeto = "-";
                }

                if (timeto.length() > timetoDate.length() && time2time.length() == 5) {
                    hourf = time2time.substring(0, 2);
                    minf = time2time.substring(3, 5);
                    System.out.println(hourf + " " + minf);
                    h = Integer.parseInt(hourf);
                    m = Integer.parseInt(minf);
                    if (m > 60) {
                        JOptionPane.showMessageDialog(this, "Minute value cannot be bigger than 60", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (h > 24) {
                        JOptionPane.showMessageDialog(this, "Hour value cannot be bigger than 24", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        //hata yok devam
                    }
                } else if (time2time.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please log in", "Null Exp", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please log in to the appropriate format", "Incorrect format", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_timeToActionPerformed

    private void cmbColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbColorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbColorActionPerformed

    private void cmbReminderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbReminderActionPerformed
        // TODO add your handling code here:
        //System.out.println(cmbReminder.getSelectedItem());
    }//GEN-LAST:event_cmbReminderActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AgendaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgendaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgendaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgendaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AgendaFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDone;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chcbxAllDay;
    private javax.swing.JCheckBox chckbxAnniversary;
    private javax.swing.JCheckBox chckbxBirtday;
    private javax.swing.JCheckBox chkbxEvent;
    private javax.swing.JComboBox cmbColor;
    private javax.swing.JComboBox cmbReminder;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton timeFrom;
    private javax.swing.JButton timeTo;
    private javax.swing.JTextArea txtDescp;
    private javax.swing.JTextField txtEventName;
    private javax.swing.JTextField txtLoc;
    // End of variables declaration//GEN-END:variables
}
