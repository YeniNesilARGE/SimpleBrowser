package calendarapp;
/*
 Enes Kamil YILMAZ
 FSM Vakıf University
 Computer Engineering
 3rd Grade
 1521221039
 */

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import javax.swing.Timer;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

public class CalendarApp extends javax.swing.JFrame {

    DefaultTableModel dtm;
    DefaultListModel dlm;
    private int[] daysInMonth = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private GregorianCalendar cal;
    private Object[] days = new Object[50];
    int realYear, realMonth, realDay, currentYear, currentMonth;
    public static Collection<Agenda> data = new HashSet<>();
    static String choosenDate, day2, month2, year2;
    String boostTime;
    boolean found = true;
    final static String LINE_SEP = System.getProperty("line.separator"), TAB = "\\t", specialSep = ",   ";
    static File f = new File("src", "Agenda.txt");;
    List<Long> IDs = new ArrayList<>();
    List<Agenda> Agendas = new ArrayList<>();

    public CalendarApp() {
        initComponents();
        String[] columns = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        dtm = new DefaultTableModel(null, columns);
        calendarTable.setModel(dtm);
        dlm = new DefaultListModel();
        agendaList.setModel(dlm);
        cal = new GregorianCalendar();
        
        this.setTitle("                                                        "
                + "                                                Calendar");
        this.setResizable(false);

        //No resize/reorder
        calendarTable.getTableHeader().setResizingAllowed(false);
        calendarTable.getTableHeader().setReorderingAllowed(false);

        //Single cell selection
        calendarTable.setColumnSelectionAllowed(true);
        calendarTable.setRowSelectionAllowed(true);
        calendarTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //Set row/column count
        calendarTable.setRowHeight(38);
        dtm.setColumnCount(7);
        dtm.setRowCount(6);

        //Setting real dates
        realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); //Get day
        realMonth = cal.get(GregorianCalendar.MONTH); //Get month
        realYear = cal.get(GregorianCalendar.YEAR); //Get year
        currentMonth = realMonth; //Match month and year
        currentYear = realYear;

        //Set date and time labels
        date.setText(realDay + "." + (realMonth + 1) + "." + realYear);

        Timer timer = new Timer(500, (ActionEvent e) -> {
            f = new File("src", "Agenda.txt");
            tickTock();//saati günceller.
            readAgenda();
            calendarTable.setDefaultRenderer(calendarTable.getColumnClass(0), new CalendarRenderer());
            refreshCalendar(currentMonth, currentYear);
        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.setInitialDelay(0);
        timer.start();

        //Populate table
        for (int i = realYear - 50; i <= realYear + 50; i++) {
            cmbYear.addItem(String.valueOf(i));
        }

        //Prepare the table
        refreshCalendar(realMonth, realYear); //Refresh calendar

        readAgenda();
        fillTheAgenda();
        calendarTable.setDefaultRenderer(calendarTable.getColumnClass(0), new CalendarRenderer());
    }

    void tickTock() {
        String timeS = DateFormat.getDateTimeInstance().format(new Date());
        timeS = timeS.substring(12, 20);
        time.setText(timeS);
    }

    static void readAgenda() {
        try {
            InputStream in = new FileInputStream(f);
            byte[] ba = new byte[in.available()];
            in.read(ba);
            in.close();
            String str = new String(ba, "Cp1254");
            String[] sa = str.split(LINE_SEP);
            System.out.printf("%s satir okundu %n", sa.length);
            for (String s : sa) {
                readLine(s);
            }
        } catch (IOException x) {
            System.out.println(x);
        }
    }

    static void readLine(String line) { //ilk
        String[] satır = line.split(",   ");
        long id = Long.parseLong(satır[0]);
        String a = satır[1];
        String b = satır[2];
        String c = satır[3];
        String c2 = satır[4];
        boolean c22 = Boolean.parseBoolean(c2);
        String d = satır[5];
        String e = satır[6];
        String f = satır[7];
        String g = satır[8];
        String h = satır[9];
        String j = satır[10];
        
        Agenda agenda = new Agenda(id,a, b, c, c22, d, e, f, g, h, j);
        data.add(agenda);
    }

    class CalendarRenderer extends DefaultTableCellRenderer { //haftasonlarını, bugünün tarihini ve agenda değeri olan tarihleri boyar.

        public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            if (column == 5 || column == 6) { //Week-end
                setBackground(new Color(255, 220, 220));
            } else { //Week
                setBackground(new Color(255, 255, 255));
            }
            if (value != null) {
                if (Integer.parseInt(value.toString()) == realDay && currentMonth == realMonth && currentYear == realYear) { //Today
                    setBackground(new Color(220, 220, 255));
                }
                String day, month, year;
                for (Agenda x : data) {
                    if (x.date.substring(1, 2).equals(".")) {//gün 1 baslı
                        day = x.date.substring(0, 1);
                        if (x.date.substring(4, 5).equals(".")) {//ay 2 baslı
                            month = x.date.substring(2, 4);
                            year = x.date.substring(5, 9);
                        } else {//ay 1 baslı
                            month = x.date.substring(2, 3);
                            year = x.date.substring(4, 8);
                        }
                    } else {//gün 2 baslı
                        day = x.date.substring(0, 2);
                        if (x.date.substring(5, 6).equals(".")) {//ay 2 baslı
                            month = x.date.substring(3, 5);
                            year = x.date.substring(6, 10);
                        } else {//ay 1 baslı
                            month = x.date.substring(3, 4);
                            year = x.date.substring(5, 9);
                        }
                    }
                    int m = Integer.parseInt(month);
                    month = months[m - 1];
                    
                    if (x.color.equals("Red") && value.toString().equals(day) && month.equals(txtMonth.getText()) && cmbYear.getSelectedItem().toString().equals(year)) {
                        setBackground(new Color(255, 51, 0));
                    }
                    if (getBackground().toString().equals("java.awt.Color[r=255,g=51,b=0]")) {
                        //kırmızı renk varsa başka renk koyma
                    } else {
                        if (x.color.equals("Blue") && value.toString().equals(day) && month.equals(txtMonth.getText()) && cmbYear.getSelectedItem().toString().equals(year)) {
                            setBackground(new Color(51, 102, 255));
                        } else if (x.color.equals("Green") && value.toString().equals(day) && month.equals(txtMonth.getText()) && cmbYear.getSelectedItem().toString().equals(year)) {
                            setBackground(new Color(102, 255, 51));
                        } else if (x.color.equals("Grey") && value.toString().equals(day) && month.equals(txtMonth.getText()) && cmbYear.getSelectedItem().toString().equals(year)) {
                            setBackground(new Color(128, 128, 128));
                        }
                    }
                }
            }
            setForeground(Color.black);
            return this;
        }
    }

    void fillTheAgenda() {
        boolean f = false;
        dlm.clear();
        for (Agenda x : data) {
            if (date.getText().equals(x.date)) {
                f = true;
               if (dlm.contains(x.eventName.toString() + " for day long") || dlm.contains(x.eventName.toString() + " " + x.fromHour + " - " + x.toHour) ) {
                        //içerde zaten varsa ekleme yapma
                    } else {
                        if (x.isAllDay) {
                            dlm.addElement(x.eventName.toString() + " for day long");
                        } else {
                            dlm.addElement(x.eventName.toString() + " " + x.fromHour + " - " + x.toHour);
                        }
                    }
                break;
            }
        }
        if (!f) {
            dlm.clear();
            dlm.addElement("What are you want to do?");
        }

    }

    void refreshCalendar(int month, int year) {
        int nod, som; //Number Of Days, Start Of Month

        //Allow/disallow buttons
        monthBack.setEnabled(true);
        monthForw.setEnabled(true);
        if (month == 0 && year <= realYear - 10) {
            monthBack.setEnabled(false);
        } //Too early
        if (month == 11 && year >= realYear + 50) {
            monthForw.setEnabled(false);
        } //Too late
        txtMonth.setText(months[month]); //Refresh the month label (at the top)
        cmbYear.setSelectedItem(String.valueOf(year)); //Select the correct year in the combo box

        //Clear table
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                dtm.setValueAt(null, i, j);
            }
        }

        //Get first day of month and number of days
        GregorianCalendar cal = new GregorianCalendar(year, month, 1);
        nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        som = cal.get(GregorianCalendar.DAY_OF_WEEK);

        //Draw calendar
        for (int i = 1; i <= nod; i++) {
            int row = new Integer((i + som - 2) / 7);
            int column = (i + som - 2) % 7;
            dtm.setValueAt(i, row, column);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        calendarTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        agendaList = new javax.swing.JList();
        monthBack = new javax.swing.JButton();
        monthForw = new javax.swing.JButton();
        add2Agenda = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        cmbYear = new javax.swing.JComboBox();
        txtMonth = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        go2Today = new javax.swing.JButton();
        timeBooster = new javax.swing.JButton();
        back2Normal = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        calendarTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, "1", "2", "3", "4", "5"},
                {"6", "7", "8", "9", "10", "11", "12"},
                {"13", "14", "15", "16", "17", "18", "19"},
                {"20", "21", "22", "23", "24", "25", "26"},
                {"27", "28", "29", "30", null, null, null}
            },
            new String [] {
                "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"
            }
        ));
        calendarTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                calendarTableMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(calendarTable);

        agendaList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                agendaListMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(agendaList);

        monthBack.setText("<");
        monthBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthBackActionPerformed(evt);
            }
        });

        monthForw.setText(">");
        monthForw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthForwActionPerformed(evt);
            }
        });

        add2Agenda.setText("+");
        add2Agenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add2AgendaActionPerformed(evt);
            }
        });

        jLabel1.setText("Agenda");

        date.setText("12.01.2018");

        time.setText("12:43:07");

        cmbYear.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbYearİtemStateChanged(evt);
            }
        });
        cmbYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbYearActionPerformed(evt);
            }
        });

        txtMonth.setText("Month");

        jLabel2.setText("Year :");

        go2Today.setText("Go to Today");
        go2Today.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                go2TodayActionPerformed(evt);
            }
        });

        timeBooster.setText("Time Boost");
        timeBooster.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                timeBoosterActionPerformed(evt);
            }
        });

        back2Normal.setText("Back to Normal");
        back2Normal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back2NormalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(monthBack)
                        .addGap(50, 50, 50)
                        .addComponent(txtMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(monthForw))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(timeBooster)
                                .addGap(18, 18, 18)
                                .addComponent(back2Normal))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(go2Today)
                                .addGap(225, 225, 225)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(cmbYear, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(add2Agenda, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(date)
                            .addGap(18, 18, 18)
                            .addComponent(time))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(monthForw)
                            .addComponent(txtMonth)
                            .addComponent(monthBack))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(date)
                            .addComponent(time))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(go2Today)
                    .addComponent(cmbYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(add2Agenda))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(timeBooster)
                    .addComponent(back2Normal))
                .addGap(0, 15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void calendarTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_calendarTableMousePressed
        int row = calendarTable.getSelectedRow();
        int col = calendarTable.getSelectedColumn();
        IDs.clear();
        Agendas.clear();
        String selectedDate = calendarTable.getValueAt(row, col) + "." + (currentMonth + 1) + "." + currentYear;
        choosenDate = selectedDate;
        if (calendarTable.getValueAt(row, col) == null) {
            JOptionPane.showMessageDialog(this, "Please select a date that is not null", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            found = false;
            dlm.clear();
            for (Agenda x : data) {
                if (selectedDate.equals(x.date) && !IDs.contains(x.id)) {
                    found = true;
                    IDs.add(x.id);
                    Agendas.add(x);
                    if (dlm.contains(x.eventName.toString() + " for day long") || dlm.contains(x.eventName.toString() + " " + x.fromHour + " - " + x.toHour) ) {
                        //içerde zaten varsa ekleme yapma
                    } else {
                        if (x.isAllDay) {
                            dlm.addElement(x.eventName.toString() + " for day long");
                        } else {
                            dlm.addElement(x.eventName.toString() + " " + x.fromHour + " - " + x.toHour);
                        }
                    }
                }
            }
            if (!found) {
                dlm.clear();
                dlm.addElement("What are you want to do?");
            }
        }

        //System.out.println(selectedDate);
    }//GEN-LAST:event_calendarTableMousePressed

    private void monthBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthBackActionPerformed
        if (currentMonth == 0) { //Back one year
            currentMonth = 11;
            currentYear -= 1;
        } else { //Back one month
            currentMonth -= 1;
        }
        refreshCalendar(currentMonth, currentYear);
    }//GEN-LAST:event_monthBackActionPerformed

    private void monthForwActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthForwActionPerformed
        if (currentMonth == 11) { //Forward one year
            currentMonth = 0;
            currentYear += 1;
        } else { //Forward one month
            currentMonth += 1;
        }
        refreshCalendar(currentMonth, currentYear);
    }//GEN-LAST:event_monthForwActionPerformed

    private void cmbYearİtemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbYearİtemStateChanged
        //---
    }//GEN-LAST:event_cmbYearİtemStateChanged

    private void cmbYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbYearActionPerformed
        // TODO add your handling code here:
        if (cmbYear.getSelectedItem() != null) {
            String b = cmbYear.getSelectedItem().toString();
            currentYear = Integer.parseInt(b);
            refreshCalendar(currentMonth, currentYear);
        }
    }//GEN-LAST:event_cmbYearActionPerformed

    private void add2AgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add2AgendaActionPerformed
        // Opens EventPopUp frame...
        int row = calendarTable.getSelectedRow();
        int col = calendarTable.getSelectedColumn();
        if (row == -1 && col == -1) {
            day2 = realDay + "";
            month2 = realMonth + 1 + "";
            year2 = realYear + "";
            choosenDate = day2 + "." + month2 + "." + year2;
        } else {
            day2 = calendarTable.getValueAt(row, col) + "";
            month2 = currentMonth + 1 + "";
            year2 = currentYear + "";
            choosenDate = day2 + "." + month2 + "." + year2;
        }

        if (EventPopUp.isOpen) {
            JOptionPane.showMessageDialog(this, "If you want to choose another date, please close Agenda Frame.", "You can't open more than 1 Agenda Frame", JOptionPane.ERROR_MESSAGE);
        } else if (day2.equals("null")) {
            JOptionPane.showMessageDialog(this, "Please select a date", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            EventPopUp a = new EventPopUp();
            a.setVisible(true);
        }
        calendarTable.setDefaultRenderer(calendarTable.getColumnClass(0), new CalendarRenderer());
    }//GEN-LAST:event_add2AgendaActionPerformed

    private void go2TodayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_go2TodayActionPerformed
        // TODO add your handling code here:
        //Setting real dates
        realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); //Get day
        realMonth = cal.get(GregorianCalendar.MONTH); //Get month
        realYear = cal.get(GregorianCalendar.YEAR); //Get year
        currentMonth = realMonth; //Match month and year
        currentYear = realYear;
        refreshCalendar(currentMonth, currentYear);

        fillTheAgenda();
    }//GEN-LAST:event_go2TodayActionPerformed

    private void agendaListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_agendaListMouseClicked
        // TODO add your handling code here:
        //System.out.println(agendaList.getSelectedValue());
        for (Agenda x : Agendas) {
            if (x.date.equals(choosenDate) && agendaList.getSelectedIndex() == Agendas.indexOf(x)) {
                //System.out.println(agendaList.getSelectedIndex() + " - " + Agendas.indexOf(x));
                if (x.isAllDay) {
                    JOptionPane.showMessageDialog(this, x.eventName + " \n" + "All day long" + "\n" + x.location + "\n" + x.description, x.eventType, JOptionPane.INFORMATION_MESSAGE, null);
                } else {
                    String shown = x.eventName + " " + x.fromHour + " - " + x.toHour + "\n" + x.location + "\n" + x.description;
                    JOptionPane.showMessageDialog(this, shown, x.eventType, JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_agendaListMouseClicked

    private void timeBoosterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_timeBoosterActionPerformed
        // TODO add your handling code here:
        boolean isStr = false;
        boostTime = JOptionPane.showInputDialog(this, "Please enter minute value for accelerating the time. ", " -Time Booster- ", JOptionPane.INFORMATION_MESSAGE);
        if (boostTime.equals("")) {
            //do nothing
        } else if (boostTime.length() > 0) {
            Pattern pattern = Pattern.compile("[a-zA-Z]");
            Matcher matcher = pattern.matcher(boostTime);
            if (matcher.find()) {
                System.out.println("harf var");
                isStr = true;
            }
            if (isStr) {
                JOptionPane.showMessageDialog(this, "Please enter only numbers... ", "Try Again", JOptionPane.ERROR_MESSAGE);
                boostTime = "";
            } else {
                int b = Integer.parseInt(boostTime);
                if (b < 0) {
                    JOptionPane.showMessageDialog(this, "Sorry, I can't go to the past :/ ", "Try Again", JOptionPane.ERROR_MESSAGE);
                    boostTime = "";
                } else if (b == 0) {
                    JOptionPane.showMessageDialog(this, "Sorry, I can't stop the time :/ ", "Try Again", JOptionPane.ERROR_MESSAGE);
                    boostTime = "";
                } else {
                    System.out.println("başarılı");
                }
            }
        }
        System.out.println(boostTime);

    }//GEN-LAST:event_timeBoosterActionPerformed

    private void back2NormalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back2NormalActionPerformed
        // TODO add your handling code here:
        //stop the appointment thread
        for (Agenda x : data) {
            System.out.println(x.eventName);
        }
    }//GEN-LAST:event_back2NormalActionPerformed

    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(CalendarApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CalendarApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CalendarApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CalendarApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CalendarApp().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add2Agenda;
    private javax.swing.JList agendaList;
    private javax.swing.JButton back2Normal;
    private javax.swing.JTable calendarTable;
    private javax.swing.JComboBox cmbYear;
    private javax.swing.JLabel date;
    private javax.swing.JButton go2Today;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton monthBack;
    private javax.swing.JButton monthForw;
    private javax.swing.JLabel time;
    private javax.swing.JButton timeBooster;
    private javax.swing.JLabel txtMonth;
    // End of variables declaration//GEN-END:variables
}
