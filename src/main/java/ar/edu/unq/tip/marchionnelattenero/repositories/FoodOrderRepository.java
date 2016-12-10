package ar.edu.unq.tip.marchionnelattenero.repositories;

import ar.edu.unq.tip.marchionnelattenero.models.caches.Cache;
import ar.edu.unq.tip.marchionnelattenero.models.FoodOrder;
import ar.edu.unq.tip.marchionnelattenero.models.FoodOrderState;
import ar.edu.unq.tip.marchionnelattenero.models.Product;
import ar.edu.unq.tip.marchionnelattenero.repositories.utils.GenericRepository;
import ar.edu.unq.tip.marchionnelattenero.repositories.utils.HibernateGenericDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static ar.edu.unq.tip.marchionnelattenero.repositories.utils.CriteriaHelper.addRestrictionForDay;

@Transactional
@Repository("foodOrderRepository")
public class FoodOrderRepository extends HibernateGenericDAO<FoodOrder> implements GenericRepository<FoodOrder> {


    private static final long serialVersionUID = 1L;

    @Override
    protected Class<FoodOrder> getDomainClass() {
        return FoodOrder.class;
    }

    public synchronized List<FoodOrder> findAfterMoment(Timestamp moment) {
        Criteria criteria = this.getSession().createCriteria(this.getDomainClass());
        criteria.add(Restrictions.ge("moment", moment));
        return (List<FoodOrder>) criteria.list();
    }

    public synchronized List<FoodOrder> findProductsAfterMoment(Product product, Timestamp moment) {
        Criteria criteria = this.getSession().createCriteria(this.getDomainClass());
        criteria.add(Restrictions.eq("product", product));
        criteria.add(Restrictions.ge("moment", moment));
        return (List<FoodOrder>) criteria.list();
    }

    public synchronized List<FoodOrder> findOrdersToCook(Product product, Timestamp moment) {
        Criteria criteria = this.getSession().createCriteria(this.getDomainClass());
        criteria.add(Restrictions.eq("product", product));
        criteria.add(Restrictions.eq("state", FoodOrderState.ORDER));
        criteria.add(Restrictions.le("moment", moment));
        criteria.add(Restrictions.eq("isReadyToDeliver", false));
        criteria.addOrder(Order.desc("moment"));
        criteria.setFirstResult(0);
        criteria.setMaxResults(1);
        return (List<FoodOrder>) criteria.list();
    }

    @Override
    public synchronized void save(FoodOrder foodOrder) {
        super.save(foodOrder);
        Cache.getInstance().addFoodOrder(foodOrder);
    }

    @Override
    public synchronized void update(FoodOrder foodOrder) {
        super.update(foodOrder);
        Cache.getInstance().addFoodOrder(foodOrder);
    }

    public synchronized List<FoodOrder> findByDay(Timestamp moment) {
        Criteria criteria = this.getSession().createCriteria(this.getDomainClass());
        criteria.add(Restrictions.eq("moment", moment));
        return (List<FoodOrder>) criteria.list();
    }

    public synchronized List<Timestamp> findAllDaysNotArchived(int count) {
        Timestamp moment = new Timestamp(DateTime.now().getMillis());

        Criteria criteria = this.getSession().createCriteria(this.getDomainClass());
        if (count > 0)
            criteria.setMaxResults(count);
        criteria.setProjection(Projections.distinct(Projections.property("moment")));
        criteria.add(Restrictions.le("moment", moment));
        criteria.add(Restrictions.eq("archived", false));
        criteria.addOrder(Order.desc("moment"));
        return (List<Timestamp>) criteria.list();
    }

    public synchronized List<FoodOrder> findByDayForArchived(Date dateClosure) {
        Criteria criteria = this.getSession().createCriteria(this.getDomainClass());
        criteria.add(Restrictions.eq("archived", false));
        addRestrictionForDay(criteria, "moment", dateClosure);
        return (List<FoodOrder>) criteria.list();
    }

    public synchronized void setArchived(Date dateClosure, Product product, FoodOrderState state) {
        Criteria criteria = this.getSession().createCriteria(this.getDomainClass());

        criteria.add(Restrictions.eq("archived", false));
        addRestrictionForDay(criteria, "moment", dateClosure);
        criteria.add(Restrictions.eq("product", product));
        criteria.add(Restrictions.eq("state", state));
        List<FoodOrder> foodOrders = criteria.list();

        if (foodOrders.size() > 0) {
//            Transaction t = this.getSession().beginTransaction();
            for (FoodOrder foodOrder : foodOrders) {
                foodOrder.setArchived();
                this.update(foodOrder);
//                this.getSession().update(foodOrder);
            }
//            t.commit();
        }
    }
}
