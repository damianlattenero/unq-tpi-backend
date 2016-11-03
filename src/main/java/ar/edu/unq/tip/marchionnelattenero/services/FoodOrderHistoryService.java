package ar.edu.unq.tip.marchionnelattenero.services;

import ar.edu.unq.tip.marchionnelattenero.models.FoodOrder;
import ar.edu.unq.tip.marchionnelattenero.models.FoodOrderHistory;
import ar.edu.unq.tip.marchionnelattenero.models.FoodOrderState;
import ar.edu.unq.tip.marchionnelattenero.models.Product;
import ar.edu.unq.tip.marchionnelattenero.repositories.FoodOrderHistoryRepository;
import ar.edu.unq.tip.marchionnelattenero.repositories.FoodOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service("foodOrderHistoryService")
public class FoodOrderHistoryService {

    @Autowired
    private FoodOrderRepository foodOrderRepository;

    @Autowired
    private FoodOrderHistoryRepository foodOrderHistoryRepository;

    @Transactional
    public FoodOrderHistory createFoodOrderHistory(Timestamp moment, Product product, FoodOrderState state, int amount) {
        FoodOrderHistory foodOrderHistory = new FoodOrderHistory(moment, product, state, amount);
        return foodOrderHistory;
    }

    public FoodOrderRepository getFoodOrderRepository() {
        return foodOrderRepository;
    }

    public FoodOrderHistoryRepository getFoodOrderHistoryRepository() {
        return foodOrderHistoryRepository;
    }

    @Transactional
    public List<FoodOrderHistory> findAll() {
        return this.getFoodOrderHistoryRepository().findAll();
    }

    @Transactional
    public void archive(Timestamp moment) {
        List<FoodOrder> foodOrders = this.getFoodOrderRepository().findByDayForArchived(moment);

        for (FoodOrder foodOrder : foodOrders) {
            FoodOrderHistory foodOrderHistory = this.createFoodOrderHistory(foodOrder.getMoment(), foodOrder.getProduct(), foodOrder.getState(), foodOrder.getAmount());
            this.getFoodOrderHistoryRepository().save(foodOrderHistory);

            this.getFoodOrderRepository().setArchived(foodOrder.getMoment(), foodOrder.getProduct(), foodOrder.getState());
        }
    }
}
