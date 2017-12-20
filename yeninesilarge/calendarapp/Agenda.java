package yeninesilarge.calendarapp;
/*
     Enes Kamil YILMAZ
    FSM Vakıf University
    Computer Engineering
        3rd Grade
        1521221039
                        */
public class Agenda {
    String eventType, eventName,color, fromHour, toHour, date, location,description,reminder;
    long id;
    Boolean isAllDay;
    
    public Agenda(long i,String a, String b, String c,Boolean c2,String d, String e, String f,String g, String h,String j){
        eventType = a; eventName = b; date = c; isAllDay=c2;
        id = i;
        if (isAllDay) d = e = "";
        fromHour=d; toHour=e; color=f; location=g; description=h;reminder = j;
    }
    
    public String toString(){
        return eventName;
    }
    
    public boolean equals(Object x) {
        if (!(x instanceof Agenda)) 
            return false;
        Agenda s = (Agenda)x; 
        return (s.id == id);
    }
}
