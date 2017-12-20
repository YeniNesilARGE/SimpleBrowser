package yeninesilarge.calendarapp;
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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import javax.swing.Timer;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;

public class CalendarApp extends javax.swing.JFrame {

    DefaultTableModel dtm;
    DefaultListModel dlm;
    String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private GregorianCalendar cal;
    static int realYear, realMonth, realDay, currentDay, currentYear, currentMonth;
    public static Collection<Agenda> data = new HashSet<>();
    static String choosenDate, day2, month2, year2, irlDate;
    String boostTime;
    boolean found = true;
    final static String LINE_SEP = System.getProperty("line.separator"), TAB = "\\t", specialSep = ",   ";
    static String txtName = "Agenda.txt";
    static File f = new File(txtName);
    //File img1 = new File("C:\\Users\\nskml\\Desktop\\2.jpg");
    List<Long> IDs = new ArrayList<>();
    List<Agenda> Agendas = new ArrayList<>();

    public CalendarApp() throws IOException {
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
        irlDate = realDay + "." + (realMonth + 1) + "." + realYear;
        date.setText(irlDate);
        
        Timer timer = new Timer(500, (ActionEvent e) -> {
            tickTock();//saati günceller.
            calendarTable.setDefaultRenderer(calendarTable.getColumnClass(0), new CalendarRenderer());
            refreshCalendar(currentMonth, currentYear);
        });
        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.setInitialDelay(0);
        timer.start();
        if (!f.exists()) {
            JOptionPane.showMessageDialog(this, "Old txt file not found.\n Creating new one.", "Error", JOptionPane.ERROR_MESSAGE);
            List<String> lines = Arrays.asList("199314857,   Event,   Programming Exam,   16.12.2017,   false,   10.00,   12.00,   Red,   C109,   WORK HARD,   12 day before",
                    "304556711,   Event,   Study operating systems,   21.12.2017,   true,   -,   -,   Grey,   -,   Pdf 8 and 9,   1 week before",
                    "899815102,   Event,   Finals Starting,   4.1.2018,   true,   10.00,   12.00,   Red,   -,   WORK HARD,   None",
                    "438041405,   Event,   Last day of semester holiday,   12.2.2018,   true,   -,   -,   Red,   -,   GG,   None",
                    "523459998,   Event,   Exam,   10.1.2018,   false,   12.00,   14.00,   Red,   C109,   WORK HARD,   None",
                    "406702748,   Event,   Exam,   11.1.2018,   false,   10.00,   12.00,   Red,   D107,   WORK HARD,   None",
                    "561304554,   Event,   Exam,   13.1.2018,   false,   10.00,   12.00,   Red,   B121,   WORK HARD,   None",
                    "784797509,   Event,   Study sum,   21.12.2017,   false,   10.00,   12.00,   Grey,   -,   WORK HARD,   None",
                    "620867157,   Event,   Study,   3.1.2018,   true,   10.00,   12.00,   Green,   -,   WORK HARD,   None",
                    "367696710,   Event,   Finals over boi. Go home...,   13.1.2018,   false,   15.00,   18.00,   Blue,   -,   -,   None",
                    "97106890,   Anniversary,   New Year,   1.1.2017,   true,   -,   -,   Blue,   ...Location,   Happy New Year!!!,   1 Hour Before",
                    "21085447,   Anniversary,   New Year,   1.1.2018,   true,   -,   -,   Blue,   ...Location,   Happy New Year!!!,   1 Hour Before",
                    "95113693,   Anniversary,   New Year,   1.1.2019,   true,   -,   -,   Blue,   ...Location,   Happy New Year!!!,   1 Hour Before",
                    "90362170,   Anniversary,   New Year,   1.1.2020,   true,   -,   -,   Blue,   ...Location,   Happy New Year!!!,   1 Hour Before",
                    "23200431,   Anniversary,   New Year,   1.1.2021,   true,   -,   -,   Blue,   ...Location,   Happy New Year!!!,   1 Hour Before",
                    "38838585,   Anniversary,   New Year,   1.1.2022,   true,   -,   -,   Blue,   ...Location,   Happy New Year!!!,   1 Hour Before",
                    "18476117,   Anniversary,   New Year,   1.1.2023,   true,   -,   -,   Blue,   ...Location,   Happy New Year!!!,   1 Hour Before",
                    "76574886,   Anniversary,   New Year,   1.1.2024,   true,   -,   -,   Blue,   ...Location,   Happy New Year!!!,   1 Hour Before",
                    "40267471,   Anniversary,   New Year,   1.1.2025,   true,   -,   -,   Blue,   ...Location,   Happy New Year!!!,   1 Hour Before",
                    "20792965,   Anniversary,   New Year,   1.1.2026,   true,   -,   -,   Blue,   ...Location,   Happy New Year!!!,   1 Hour Before",
                    "26700604,   Anniversary,   New Year,   1.1.2027,   true,   -,   -,   Blue,   ...Location,   Happy New Year!!!,   1 Hour Before");
            Path file = Paths.get("Agenda.txt");
            Files.write(file, lines, Charset.forName("Cp1254"));
        }

        //Populate table
        for (int i = realYear; i <= realYear + 10; i++) {
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
        if (line.equals("")) {
            //boş satır varsa birşey yapma
        }else{
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

                    if (x.eventType.equals("Anniversary")) {
                        if (x.color.equals("Red") && value.toString().equals(day) && month.equals(txtMonth.getText())) {
                            setBackground(new Color(255, 51, 0));
                        }
                        if (getBackground().toString().equals("java.awt.Color[r=255,g=51,b=0]")) {
                            //kırmızı renk varsa başka renk koyma
                        } else {
                            if (x.color.equals("Blue") && value.toString().equals(day) && month.equals(txtMonth.getText())) {
                                setBackground(new Color(51, 102, 255));
                            } else if (x.color.equals("Green") && value.toString().equals(day) && month.equals(txtMonth.getText())) {
                                setBackground(new Color(102, 255, 51));
                            } else if (x.color.equals("Grey") && value.toString().equals(day) && month.equals(txtMonth.getText())) {
                                setBackground(new Color(128, 128, 128));
                            }
                        }
                    } else if (x.eventType.equals("Birtday")) {
                        if (x.color.equals("Red") && value.toString().equals(day) && month.equals(txtMonth.getText())) {
                            setBackground(new Color(255, 51, 0));
                        }
                        if (getBackground().toString().equals("java.awt.Color[r=255,g=51,b=0]")) {
                            //kırmızı renk varsa başka renk koyma
                        } else {
                            if (x.color.equals("Blue") && value.toString().equals(day) && month.equals(txtMonth.getText())) {
                                setBackground(new Color(51, 102, 255));
                            } else if (x.color.equals("Green") && value.toString().equals(day) && month.equals(txtMonth.getText())) {
                                setBackground(new Color(102, 255, 51));
                            } else if (x.color.equals("Grey") && value.toString().equals(day) && month.equals(txtMonth.getText())) {
                                setBackground(new Color(128, 128, 128));
                            }
                        }
                    } else {

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
               if (dlm.contains(x.eventName.toString() ) || dlm.contains(x.eventName.toString() + " " + x.fromHour + " - " + x.toHour) ) {
                        //içerde zaten varsa ekleme yapma
                    } else {
                        if (x.isAllDay) {
                            dlm.addElement(x.eventName.toString() );
                        } else {
                            dlm.addElement(x.eventName.toString() + " " + x.fromHour + " - " + x.toHour);
                        }
                    }
                break;
            }
        }
        if (!f) {
            dlm.clear();
            dlm.addElement("What would you like to do today?");
        }

    }

    void refreshCalendar(int month, int year) {
        int nod, som; //Number Of Days, Start Of Month

        //Allow/disallow buttons
        monthBack.setEnabled(true);
        monthForw.setEnabled(true);
        if (month == 0 && year <= realYear) {
            monthBack.setEnabled(false);
        } //Too early
        if (month == 11 && year >= realYear + 10) {
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

        jPanel1 = new javax.swing.JPanel();
        go2Today = new javax.swing.JButton();
        timeBooster = new javax.swing.JButton();
        back2Normal = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        calendarTable = new javax.swing.JTable();
        monthBack = new javax.swing.JButton();
        txtMonth = new javax.swing.JLabel();
        monthForw = new javax.swing.JButton();
        cmbYear = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        agendaList = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        add2Agenda = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(java.awt.Color.darkGray);

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

        monthBack.setText("<");
        monthBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthBackActionPerformed(evt);
            }
        });

        txtMonth.setText("Month");

        monthForw.setText(">");
        monthForw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthForwActionPerformed(evt);
            }
        });

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

        jLabel2.setText("Year :");

        date.setText("12.01.2018");

        time.setText("12:43:07");

        agendaList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                agendaListMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(agendaList);

        jLabel1.setText("Agenda");

        add2Agenda.setText("+");
        add2Agenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add2AgendaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(154, 154, 154)
                .addComponent(monthBack)
                .addGap(45, 45, 45)
                .addComponent(txtMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(monthForw)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(timeBooster)
                        .addGap(18, 18, 18)
                        .addComponent(back2Normal)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(24, 24, 24)
                                .addComponent(cmbYear, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(add2Agenda, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(date)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(time)))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(go2Today)
                .addGap(582, 582, 582))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(monthForw)
                                    .addComponent(txtMonth))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(monthBack)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(date)
                            .addComponent(time))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(add2Agenda)
                    .addComponent(go2Today))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(timeBooster)
                    .addComponent(back2Normal))
                .addGap(58, 58, 58))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                try {
                    new CalendarApp().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(CalendarApp.class.getName()).log(Level.SEVERE, null, ex);
                }
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton monthBack;
    private javax.swing.JButton monthForw;
    private javax.swing.JLabel time;
    private javax.swing.JButton timeBooster;
    private javax.swing.JLabel txtMonth;
    // End of variables declaration//GEN-END:variables
}
