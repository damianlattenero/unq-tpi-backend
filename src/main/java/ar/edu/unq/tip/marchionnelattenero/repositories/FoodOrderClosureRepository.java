package ar.edu.unq.tip.marchionnelattenero.repositories;

import ar.edu.unq.tip.marchionnelattenero.models.FoodOrderClosure;
import ar.edu.unq.tip.marchionnelattenero.repositories.utils.GenericRepository;
import ar.edu.unq.tip.marchionnelattenero.repositories.utils.HibernateGenericDAO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("foodOrderClosureRepository")
public class FoodOrderClosureRepository extends HibernateGenericDAO<FoodOrderClosure> implements GenericRepository<FoodOrderClosure> {


    private static final long serialVersionUID = 1L;

    @Override
    protected Class<FoodOrderClosure> getDomainClass() {
        return FoodOrderClosure.class;
    }

}
