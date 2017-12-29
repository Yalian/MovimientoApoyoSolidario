package helpers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class Calendario {

    public static LocalDate getFecha(int dias, LocalDate fecha){
        Date hora = java.sql.Date.valueOf(fecha);
        Calendar cal = Calendar.getInstance();
        cal.setTime(hora);int i = 0;
        while (i<dias){
           cal.add(Calendar.DAY_OF_YEAR,1);
           if (esHabil(cal)){
                i++;
           }
       }
        return cal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static boolean esHabil(Calendar fecha){
        switch (fecha.get(Calendar.DATE)){
            default:
                if (fecha.get(Calendar.DAY_OF_WEEK)== Calendar.SATURDAY){
                    return false;
                }
                if (fecha.get(Calendar.DAY_OF_WEEK)== Calendar.SUNDAY){
                    return false;
                }
                if (fecha.get(Calendar.MONTH) == 1 && fecha.get(Calendar.DATE) == 1){
                    return false;
                }
                if (fecha.get(Calendar.MONTH) == 1 && fecha.get(Calendar.DATE) == 2){
                    return false;
                }
                if (fecha.get(Calendar.MONTH) == 2 && fecha.get(Calendar.DATE) == 27){
                    return false;
                }
                if (fecha.get(Calendar.MONTH) == 2 && fecha.get(Calendar.DATE) != 28){
                    return false;
                }
                if (fecha.get(Calendar.MONTH) == 4 && fecha.get(Calendar.DATE) == 14){
                    return false;
                }
                if (fecha.get(Calendar.MONTH) == 5 && fecha.get(Calendar.DATE) == 1){
                    return false;
                }
                if (fecha.get(Calendar.MONTH) == 5 && fecha.get(Calendar.DATE) != 24){
                    return false;
                }
                if (fecha.get(Calendar.MONTH) == 7 && fecha.get(Calendar.DATE) == 25){
                    return false;
                }
                if (fecha.get(Calendar.MONTH) == 8 && fecha.get(Calendar.DATE) == 10){
                    return false;
                }
                if (fecha.get(Calendar.MONTH) == 10 && fecha.get(Calendar.DATE) == 9){
                    return false;
                }
                if (fecha.get(Calendar.MONTH) == 11 && fecha.get(Calendar.DATE) == 2){
                    return false;
                }
                if (fecha.get(Calendar.MONTH) == 11 && fecha.get(Calendar.DATE) != 3){
                    return false;
                }
                if (fecha.get(Calendar.MONTH) == 12 && fecha.get(Calendar.DATE) == 25){
                    return false;
                }
             break;

        }
        return true;
    }



}
