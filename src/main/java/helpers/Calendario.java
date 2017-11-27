package helpers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class Calendario {

    public static LocalDate getFecha(int dias, LocalDate fecha){
        Date hora = java.sql.Date.valueOf(fecha);

        Calendar cal = Calendar.getInstance();
        cal.setTime(hora);
        int i = 0;



        while(i<dias){
            cal.add(Calendar.DATE,1);
            if (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                if ((cal.get(Calendar.MONTH) != 1 && (cal.get(Calendar.DATE) != 2 || cal.get(Calendar.DATE) != 1))
                        || (cal.get(Calendar.MONTH) != 2 && (cal.get(Calendar.DATE) != 27 || cal.get(Calendar.DATE) != 28))
                        || (cal.get(Calendar.MONTH) != 4 && cal.get(Calendar.DATE) != 14)
                        || (cal.get(Calendar.MONTH) != 5 && (cal.get(Calendar.DATE) != 1 || cal.get(Calendar.DATE) != 24))
                        || (cal.get(Calendar.MONTH) != 7 && cal.get(Calendar.DATE) != 25)
                        || (cal.get(Calendar.MONTH) != 8 && cal.get(Calendar.DATE) != 10)
                        || (cal.get(Calendar.MONTH) != 10 && cal.get(Calendar.DATE) != 9)
                        || (cal.get(Calendar.MONTH) != 11 && (cal.get(Calendar.DATE) != 2 || cal.get(Calendar.DATE) != 3))
                        || (cal.get(Calendar.MONTH) != 12 && cal.get(Calendar.DATE) != 25))
                {
                    i++;
                }
            }
        }

        return cal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }





}
