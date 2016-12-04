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

    private FoodOrder createFoodOrder(int idProduct, UserModel user, int amount, FoodOrderState state) {
        Product p = productRepository.findById(idProduct);
        FoodOrder foodOrder = new FoodOrder(p, state, amount, user);
        this.getFoodOrderRepository().save(foodOrder);
        return foodOrder;
    }

    public FoodOrder order(int idProduct, UserModel user, int amount) {
        return createFoodOrder(idProduct, user, amount, FoodOrderState.ORDER);
    }

    public FoodOrder cancelOrder(int idProduct, UserModel user, int amount) {
        return createFoodOrder(idProduct, user, amount, FoodOrderState.CANCELORDER);
    }

    public FoodOrder coocked(int idProduct, UserModel user, int amount) {
        return createFoodOrder(idProduct, user, amount, FoodOrderState.COOKED);
    }

    public FoodOrder cancelCoocked(int idProduct, UserModel user, int amount) {
        return createFoodOrder(idProduct, user, amount, FoodOrderState.CANCELCOOKED);
    }


    public FoodOrderRepository getFoodOrderRepository() {
        return foodOrderRepository;
    }

/*
    public FoodOrderHistoryRepository getFoodOrderHistoryRepository() {
        return foodOrderHistoryRepository;
    }
*/

    @Transactional
    public List<FoodOrder> findAll() {
        return this.getFoodOrderRepository().findAll();
    }

    public FoodOrder findById(Integer id) {
        return this.getFoodOrderRepository().findById(id);
    }

    public List<FoodOrder> findByDay(Timestamp day) {
        return this.getFoodOrderRepository().findByDay(day);
    }

    public List<Timestamp> findAllDays() {
        return this.findAllDays(0);
    }

    public List<Timestamp> findAllDays(int count) {
        return this.getFoodOrderRepository().findAllDaysNotArchived(count);
    }


}
