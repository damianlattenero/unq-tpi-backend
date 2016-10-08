package ar.edu.unq.tip.marchionnelattenero.google;

import org.joda.time.DateTime;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.Date;
import java.util.Locale;


public class DateUtils {
    public static Date parse(String dateString) {

        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZZ")
                .withLocale(Locale.ROOT)
                .withChronology(ISOChronology.getInstanceUTC());

        DateTime dt = formatter.parseDateTime(dateString);
        return new Date(dt.getMillis());
    }
}
