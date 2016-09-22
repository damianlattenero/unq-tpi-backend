package ar.edu.unq.tip.marchionnelattenero.services;

import ar.edu.unq.tip.marchionnelattenero.factories.FoodOrderFactory;
import ar.edu.unq.tip.marchionnelattenero.models.FoodOrder;
import ar.edu.unq.tip.marchionnelattenero.models.Product;
import ar.edu.unq.tip.marchionnelattenero.repositories.FoodOrderRepository;
import ar.edu.unq.tip.marchionnelattenero.repositories.FoodOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("foodOrderService")
public class FoodOrderService {
    @Autowired
    private FoodOrderRepository foodOrderRepository;

    @Autowired
    private FoodOrderFactory foodOrderFactory;

    @Transactional
    public FoodOrder createFoodOrder(Product product, int amount) {
        FoodOrder foodOrder = new FoodOrder(product, amount);
        foodOrderRepository.save(foodOrder);
        return foodOrder;
    }
    @Transactional
    public List<FoodOrder> findAll() {
        return this.getFoodOrderRepository().findAll();
    }

    @Transactional
    public void deleteFoodOrder(FoodOrder foodOrder) {
        foodOrderRepository.delete(foodOrder);
    }

    public FoodOrder getFoodOrder(Integer id) {
        return this.foodOrderFactory.getFoodOrderById(id);
    }

    public FoodOrderRepository getFoodOrderRepository() {
        return foodOrderRepository;
    }
}
