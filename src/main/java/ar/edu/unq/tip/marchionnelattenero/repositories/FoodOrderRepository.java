package ar.edu.unq.tip.marchionnelattenero.repositories;

import ar.edu.unq.tip.marchionnelattenero.models.FoodOrder;
import ar.edu.unq.tip.marchionnelattenero.repositories.utils.GenericRepository;
import ar.edu.unq.tip.marchionnelattenero.repositories.utils.HibernateGenericDAO;
import org.springframework.stereotype.Repository;

@Repository("foodRepository")
public class FoodOrderRepository extends HibernateGenericDAO<FoodOrder> implements GenericRepository<FoodOrder> {


    private static final long serialVersionUID = -4036325633305673330L;

    @Override
    protected Class<FoodOrder> getDomainClass() {
        return FoodOrder.class;
    }

}
