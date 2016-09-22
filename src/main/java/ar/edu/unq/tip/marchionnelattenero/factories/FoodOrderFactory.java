package ar.edu.unq.tip.marchionnelattenero.factories;

import ar.edu.unq.tip.marchionnelattenero.models.FoodOrder;
import ar.edu.unq.tip.marchionnelattenero.repositories.FoodOrderRepository;
import ar.edu.unq.tip.marchionnelattenero.repositories.ProductRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static org.apache.click.extras.hibernate.SessionContext.getSession;

@Component("foodFactory")
public class FoodOrderFactory {

    @Autowired
    private FoodOrderRepository foodOrderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public void createBasicProducts() {
        FoodOrder order1 = new FoodOrder(productRepository.findById(1));
        FoodOrder order2 = new FoodOrder(productRepository.findById(2));
        FoodOrder order3 = new FoodOrder(productRepository.findById(3));
        this.foodOrderRepository.save(order1);
        this.foodOrderRepository.save(order2);
        this.foodOrderRepository.save(order3);
    }

    public FoodOrder getFoodOrderById(Integer id) {
        Criteria criteria = getSession().createCriteria(FoodOrder.class);
        FoodOrder yourObject = (FoodOrder) criteria.add(Restrictions.eq("id", id)).uniqueResult();
        return yourObject;
    }
}
