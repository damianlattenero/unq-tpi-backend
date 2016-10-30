package ar.edu.unq.tip.marchionnelattenero.repositories;

import ar.edu.unq.tip.marchionnelattenero.models.FoodOrderHistory;
import ar.edu.unq.tip.marchionnelattenero.repositories.utils.GenericRepository;
import ar.edu.unq.tip.marchionnelattenero.repositories.utils.HibernateGenericDAO;
import org.springframework.stereotype.Repository;

@Repository("foodOrderHistoryRepository")
public class FoodOrderHistoryRepository extends HibernateGenericDAO<FoodOrderHistory> implements GenericRepository<FoodOrderHistory> {

    private static final long serialVersionUID = 1L;

    @Override
    protected Class<FoodOrderHistory> getDomainClass() {
        return FoodOrderHistory.class;
    }

}
