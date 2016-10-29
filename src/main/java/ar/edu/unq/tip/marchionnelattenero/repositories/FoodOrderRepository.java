package ar.edu.unq.tip.marchionnelattenero.repositories;

import ar.edu.unq.tip.marchionnelattenero.models.Cache;
import ar.edu.unq.tip.marchionnelattenero.models.FoodOrder;
import ar.edu.unq.tip.marchionnelattenero.models.Product;
import ar.edu.unq.tip.marchionnelattenero.repositories.utils.GenericRepository;
import ar.edu.unq.tip.marchionnelattenero.repositories.utils.HibernateGenericDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository("foodRepository")
public class FoodOrderRepository extends HibernateGenericDAO<FoodOrder> implements GenericRepository<FoodOrder> {


    private static final long serialVersionUID = -4036325643305673330L;

    @Override
    protected Class<FoodOrder> getDomainClass() {
        return FoodOrder.class;
    }

    public List<FoodOrder> findAfterMoment(Timestamp moment) {
        Criteria criteria = this.getSession().createCriteria(this.getDomainClass());
        criteria.add(Restrictions.ge("moment", moment));
        return (List<FoodOrder>) criteria.list();
    }

    public List<FoodOrder> findProductsAfterMoment(Product product, Timestamp moment) {
        Criteria criteria = this.getSession().createCriteria(this.getDomainClass());
        criteria.add(Restrictions.eq("product", product));
        criteria.add(Restrictions.ge("moment", moment));
        return (List<FoodOrder>) criteria.list();
    }

    @Override
    public void save(FoodOrder foodOrder) {
        super.save(foodOrder);
        Cache.getInstance().addFoodOrder(foodOrder);
    }

    public List<FoodOrder> findByDay(Timestamp moment) {
        Criteria criteria = this.getSession().createCriteria(this.getDomainClass());
        criteria.add(Restrictions.eq("moment", moment));
        return (List<FoodOrder>) criteria.list();
    }

    public List<Timestamp> findAllDays(int count) {
        Timestamp moment = new Timestamp(DateTime.now().getMillis());

        Criteria criteria = this.getSession().createCriteria(this.getDomainClass());
        if (count > 0)
            criteria.setMaxResults(count);
        criteria.setProjection(Projections.distinct(Projections.property("moment")));
        criteria.add(Restrictions.le("moment", moment));
        criteria.addOrder(Order.desc("moment"));
        return (List<Timestamp>) criteria.list();
    }
}
