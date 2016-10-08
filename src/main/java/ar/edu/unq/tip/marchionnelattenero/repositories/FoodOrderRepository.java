package ar.edu.unq.tip.marchionnelattenero.repositories;

import ar.edu.unq.tip.marchionnelattenero.models.Cache;
import ar.edu.unq.tip.marchionnelattenero.models.FoodOrder;
import ar.edu.unq.tip.marchionnelattenero.models.Product;
import ar.edu.unq.tip.marchionnelattenero.repositories.utils.GenericRepository;
import ar.edu.unq.tip.marchionnelattenero.repositories.utils.HibernateGenericDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
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

    public List<FoodOrder> findAfterMoment(Timestamp moment)
    {
        Criteria criteria = this.getSession().createCriteria(this.getDomainClass());
        criteria.add(Restrictions.ge("moment", moment));
        return (List<FoodOrder>) criteria.list();
    }

    public List<FoodOrder> findProductsAfterMoment(Product product, Timestamp moment)
    {
        Criteria criteria = this.getSession().createCriteria(this.getDomainClass());
        criteria.add(Restrictions.eq("product", product));
        criteria.add(Restrictions.ge("moment", moment));
        return (List<FoodOrder>) criteria.list();
    }

    @Override
    public void save(FoodOrder foodOrder){
        super.save(foodOrder);
        Cache.getInstance().addFoodOrder(foodOrder);
    }

}
