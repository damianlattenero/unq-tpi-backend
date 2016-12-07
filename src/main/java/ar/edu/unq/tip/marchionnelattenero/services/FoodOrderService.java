package ar.edu.unq.tip.marchionnelattenero.services;

import ar.edu.unq.tip.marchionnelattenero.models.FoodOrder;
import ar.edu.unq.tip.marchionnelattenero.models.FoodOrderState;
import ar.edu.unq.tip.marchionnelattenero.models.Product;
import ar.edu.unq.tip.marchionnelattenero.models.UserModel;
import ar.edu.unq.tip.marchionnelattenero.repositories.FoodOrderRepository;
import ar.edu.unq.tip.marchionnelattenero.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service("foodOrderService")
public class FoodOrderService {
    @Autowired
    private FoodOrderRepository foodOrderRepository;

/*
    @Autowired
    private FoodOrderHistoryRepository foodOrderHistoryRepository;
*/

    @Autowired
    private ProductRepository productRepository;




    @Transactional
    public FoodOrder order(int idProduct, UserModel user, int amount) {
        return createFoodOrder(idProduct, user, amount, FoodOrderState.ORDER);
    }

    @Transactional
    public FoodOrder cancelOrder(int idProduct, UserModel user, int amount) {
        return createFoodOrder(idProduct, user, amount, FoodOrderState.CANCELORDER);
    }

    @Transactional
    public FoodOrder coocked(int idProduct, UserModel user, int amount) {
        return createFoodOrder(idProduct, user, amount, FoodOrderState.COOKED);
    }

    @Transactional
    public FoodOrder cancelCoocked(int idProduct, UserModel user, int amount) {
        return this.createFoodOrder(idProduct, user, amount, FoodOrderState.CANCELCOOKED);
    }

    @Transactional
    private FoodOrder createFoodOrder(int idProduct, UserModel user, int amount, FoodOrderState state) {
        Product p = productRepository.findById(idProduct);
        FoodOrder foodOrder = new FoodOrder(p, state, amount, user);
        this.foodOrderRepository.save(foodOrder);
        return foodOrder;
    }


/*
    public FoodOrderHistoryRepository getFoodOrderHistoryRepository() {
        return foodOrderHistoryRepository;
    }
*/

    @Transactional
    public List<FoodOrder> findAll() {
        return foodOrderRepository.findAll();
    }

    public FoodOrder findById(Integer id) {
        return foodOrderRepository.findById(id);
    }

    public List<FoodOrder> findByDay(Timestamp day) {
        return foodOrderRepository.findByDay(day);
    }

    public List<Timestamp> findAllDays() {
        return this.findAllDays(0);
    }

    public List<Timestamp> findAllDays(int count) {
        return foodOrderRepository.findAllDaysNotArchived(count);
    }


}