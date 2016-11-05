package ar.edu.unq.tip.marchionnelattenero.repositories.utils;

import ar.edu.unq.tip.marchionnelattenero.models.utils.DateHelper;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import java.sql.Timestamp;
import java.util.Date;

public class CriteriaHelper {

    public static void addRestrictionForDay(Criteria criteria, String property, Timestamp timestamp)
    {
        addRestrictionForDay(criteria, property, DateHelper.getDateWithoutTime(timestamp));
    }

    public static void addRestrictionForDay(Criteria criteria, String property, Date date)
    {
        criteria.add(Restrictions.ge(property, date));
        criteria.add(Restrictions.lt(property, DateHelper.getTomorrowDate(date)));
    }

}

