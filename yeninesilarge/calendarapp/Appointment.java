package yeninesilarge.calendarapp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/*
     Enes Kamil YILMAZ
    FSM Vakıf University
    Computer Engineering
        3rd Grade
        1521221039
                        */

abstract class Appointment {
    String desc,type;
    int numm;
    boolean report = false;
    abstract boolean occursOn(int d, int m, int y);
}

class Day extends Appointment{
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
    LocalDate dayLD;
    String dd,mm,yyyy;
    int ddd,mmm,yyy;
    public Day(String date,int num) {
        dayLD = LocalDate.parse(date, formatter);
        dayLD = dayLD.minusDays(num);
        dd = DateTimeFormatter.ofPattern("d").format(dayLD);
        mm = DateTimeFormatter.ofPattern("M").format(dayLD);
        yyyy = DateTimeFormatter.ofPattern("yyyy").format(dayLD);
        ddd = Integer.parseInt(dd);
        mmm = Integer.parseInt(mm);
        yyy = Integer.parseInt(yyyy);
        desc =num + "  Day Later at " + date;
        numm = num;
        type = "Day";
        //System.out.println(CalendarApp.currentDay +" "+ ddd +" - "+ CalendarApp.currentMonth+ " " + mmm +" - "+CalendarApp.currentYear+" "+ yyy);
    }
    public boolean occursOn(int d, int m, int y) {
        if (CalendarApp.currentDay == ddd && CalendarApp.currentMonth+1 == mmm && CalendarApp.currentYear == yyy) 
            return true;
        else 
            return false;
    }
}


class Week extends Appointment {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
    LocalDate weekLD;    
    String dd,mm,yyyy;
    int ddd,mmm,yyy;
    public Week(String date, int num) {
        weekLD = LocalDate.parse(date, formatter);
        weekLD = weekLD.minusWeeks(num);
        dd = DateTimeFormatter.ofPattern("d").format(weekLD);
        mm = DateTimeFormatter.ofPattern("M").format(weekLD);
        yyyy = DateTimeFormatter.ofPattern("yyyy").format(weekLD);
        ddd = Integer.parseInt(dd);
        mmm = Integer.parseInt(mm);
        yyy = Integer.parseInt(yyyy);
        desc =num + " Week Later at " + date ;
        numm = num;
        type = "Week";
        //System.out.println(CalendarApp.currentDay +" "+ ddd +" - "+ CalendarApp.currentMonth+ " " + mmm +" - "+CalendarApp.currentYear+" "+ yyy);
    }
    public boolean occursOn(int d, int m, int y) {
        if (CalendarApp.currentDay == ddd && CalendarApp.currentMonth+1 == mmm && CalendarApp.currentYear == yyy) 
            return true;
        else 
            return false;
    }
}
