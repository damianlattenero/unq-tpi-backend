package ar.edu.unq.tip.marchionnelattenero.repositories;

import ar.edu.unq.tip.marchionnelattenero.models.FoodOrderHistory;
import ar.edu.unq.tip.marchionnelattenero.models.FoodOrderState;
import ar.edu.unq.tip.marchionnelattenero.models.Product;
import ar.edu.unq.tip.marchionnelattenero.repositories.utils.GenericRepository;
import ar.edu.unq.tip.marchionnelattenero.repositories.utils.HibernateGenericDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import static ar.edu.unq.tip.marchionnelattenero.repositories.utils.CriteriaHelper.addRestrictionForDay;

@Repository("foodOrderHistoryRepository")
public class FoodOrderHistoryRepository extends HibernateGenericDAO<FoodOrderHistory> implements GenericRepository<FoodOrderHistory> {

    private static final long serialVersionUID = 1L;

    @Override
    protected Class<FoodOrderHistory> getDomainClass() {
        return FoodOrderHistory.class;
    }

    public List<FoodOrderHistory> findBy(Date dateClosure, Product product, FoodOrderState state) {
        Criteria criteria = this.getSession().createCriteria(this.getDomainClass());
        addRestrictionForDay(criteria, "moment", dateClosure);
        criteria.add(Restrictions.eq("product", product));
        criteria.add(Restrictions.eq("state", state));
        return (List<FoodOrderHistory>) criteria.list();
    }

    public List<FoodOrderHistory> findByDay(Date date) {
        Criteria criteria = this.getSession().createCriteria(this.getDomainClass());
        addRestrictionForDay(criteria, "moment", date);
        return (List<FoodOrderHistory>) criteria.list();
    }

    public List<FoodOrderHistory> findByDayFromTo(Date dateFrom, Date dateTo) {
        Criteria criteria = this.getSession().createCriteria(this.getDomainClass());
        criteria.add(Restrictions.ge("moment", dateFrom));
        criteria.add(Restrictions.le("moment", dateTo));
        return (List<FoodOrderHistory>) criteria.list();
    }
}
