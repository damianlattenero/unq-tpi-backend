package ar.edu.unq.tip.marchionnelattenero.models.utils;

import java.util.Calendar;
import java.util.Date;

public class DateHelper {

    public synchronized static Date getDateWithoutTime(long datetime) {
        return getDateWithoutTime(new Date(datetime));
    }

    public synchronized static Date getDateWithoutTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public synchronized static Date getTomorrowDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }

    public synchronized static Date getYesterdayDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }
}
