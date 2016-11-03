package ar.edu.unq.tip.marchionnelattenero.repositories;

import ar.edu.unq.tip.marchionnelattenero.models.Cache;
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

import java.sql.Timestamp;
import java.util.List;

@Repository("foodRepository")
public class FoodOrderRepository extends HibernateGenericDAO<FoodOrder> implements GenericRepository<FoodOrder> {


    private static final long serialVersionUID = 1L;

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

    public List<FoodOrder> findByDayForArchived(Timestamp moment) {
        Criteria criteria = this.getSession().createCriteria(this.getDomainClass());
        //TODO: Formatear moment as yyyyMMdd
        criteria.setProjection(Projections.projectionList()
                .add(Projections.groupProperty("moment"))
                .add(Projections.groupProperty("product"))
                .add(Projections.groupProperty("state"))
                .add(Projections.sum("amount"))
        );

        criteria.add(Restrictions.eq("archived", false));
        criteria.add(Restrictions.eq("moment", moment));
        return (List<FoodOrder>) criteria.list();
    }

    public List<Timestamp> findAllDaysNotArchived(int count) {
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

    public void setArchived(Timestamp moment, Product product, FoodOrderState state) {
        Criteria criteria = this.getSession().createCriteria(this.getDomainClass());
        criteria.add(Restrictions.eq("archived", false));
        criteria.add(Restrictions.eq("moment", moment));
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
