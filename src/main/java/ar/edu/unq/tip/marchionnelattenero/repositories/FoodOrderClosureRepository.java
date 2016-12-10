package ar.edu.unq.tip.marchionnelattenero.repositories;

import ar.edu.unq.tip.marchionnelattenero.models.*;
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
import java.util.List;
@Transactional
@Repository("foodOrderClosureRepository")
public class FoodOrderClosureRepository extends HibernateGenericDAO<FoodOrderClosure> implements GenericRepository<FoodOrderClosure> {


    private static final long serialVersionUID = 1L;

    @Override
    protected synchronized Class<FoodOrderClosure> getDomainClass() {
        return FoodOrderClosure.class;
    }

}
