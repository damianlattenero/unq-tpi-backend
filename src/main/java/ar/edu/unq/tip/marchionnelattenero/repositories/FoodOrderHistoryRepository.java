package ar.edu.unq.tip.marchionnelattenero.repositories;

import ar.edu.unq.tip.marchionnelattenero.models.FoodOrderHistory;
import ar.edu.unq.tip.marchionnelattenero.models.FoodOrderState;
import ar.edu.unq.tip.marchionnelattenero.models.Product;
import ar.edu.unq.tip.marchionnelattenero.repositories.utils.GenericRepository;
import ar.edu.unq.tip.marchionnelattenero.repositories.utils.HibernateGenericDAO;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import static ar.edu.unq.tip.marchionnelattenero.repositories.utils.CriteriaHelper.addRestrictionForDay;

@Repository("foodOrderHistoryRepository")
public class FoodOrderHistoryRepository extends HibernateGenericDAO<FoodOrderHistory> implements GenericRepository<FoodOrderHistory> {

    private static final long serialVersionUID = 1L;
    private static final String ID = "FoodOrderHistory_ID";

    @Override
    protected Class<FoodOrderHistory> getDomainClass() {
        return FoodOrderHistory.class;
    }

    public FoodOrderHistory findBy(Date dateClosure, Product product) {
        Criteria criteria = this.getSession().createCriteria(this.getDomainClass());
        addRestrictionForDay(criteria, "moment", dateClosure);
        criteria.add(Restrictions.eq("product", product));
        return (FoodOrderHistory) criteria.uniqueResult();
    }

    //TODO: Mejorar Busqueda porque da Error
    public FoodOrderHistory findBy(Date dateClosure, Product product, FoodOrderState state) {
        String sql = "SELECT HISTORY." + ID + ", HISTORY.moment, HISTORY.product_Product_ID " +
                ", AMOUNTS.state, AMOUNTS.amount " +
                "FROM unqdb.FoodOrderHistory HISTORY \n" +
                "INNER JOIN unqdb.FoodOrderHistory_Amounts AMOUNTS \n" +
                "ON (HISTORY." + ID +" = AMOUNTS." + ID + ") \n" +
                "WHERE " +
//                "HISTORY.moment = '' AND \n" +
                "HISTORY.product_Product_ID = '" + product.getId() + "' AND \n" +
                "AMOUNTS.state = '" + state.toString() + "' \n" +
                ";";
        SQLQuery query = this.getSession().createSQLQuery(sql);
        query.addEntity(this.getDomainClass());
        return (FoodOrderHistory) query.uniqueResult();
    }

//    public FoodOrderHistory findBy(Date dateClosure, Product product, FoodOrderState state) {
////        Criteria criteriaHistory= this.getSession().createCriteria(this.getDomainClass());
////        addRestrictionForDay(criteriaHistory, "moment", dateClosure);
////        criteriaHistory.add(Restrictions.eq("product", product));
//
////        Criteria criteria2 = criteria.createCriteria(ALIAS + "." + ID, "FoodOrderHistory_Amounts");
////        criteria2.add(Restrictions.eq("FoodOrderHistory_Amounts.state", state));
//
///*        DetachedCriteria ownerCriteria = DetachedCriteria.forClass(Owner.class);
//        ownerCriteria.setProjection(Property.forName("id"));
//        ownerCriteria.add(Restrictions.eq("ownername", "bob"));
//
//        Criteria criteria = getSession().createCriteria(Pet.class);
//        criteria.add(Property.forName("ownerId").in(ownerCriteria));
//*/
//
////        Criteria criteriaAmounts = this.getSession().createCriteria("FoodOrderHistory_Amounts");
////        criteriaAmounts.add(Restrictions.eq("state", state));
////        criteriaAmounts.add(Property.forName(ID).in(criteriaHistory));
//
//        Criteria criteriaAmounts = this.getSession().createCriteria("FoodOrderHistory_Amounts");
//        criteriaAmounts.setProjection(Property.forName(ID));
//        criteriaAmounts.add(Restrictions.eq("state", state));
//
//        Criteria criteriaHistory = this.getSession().createCriteria(this.getDomainClass());
//        addRestrictionForDay(criteriaHistory, "moment", dateClosure);
//        criteriaHistory.add(Restrictions.eq("product", product));
//        criteriaHistory.add(Restrictions.in("id", criteriaAmounts.list()));
//
//        System.out.println("SQL: " + criteriaHistory.toString());
//        return (FoodOrderHistory) criteriaHistory.uniqueResult();
//    }

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
