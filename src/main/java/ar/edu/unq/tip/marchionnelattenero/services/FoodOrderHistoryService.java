package ar.edu.unq.tip.marchionnelattenero.services;

import ar.edu.unq.tip.marchionnelattenero.models.FoodOrderHistory;
import ar.edu.unq.tip.marchionnelattenero.models.FoodOrderState;
import ar.edu.unq.tip.marchionnelattenero.models.Product;
import ar.edu.unq.tip.marchionnelattenero.repositories.FoodOrderHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service("foodOrderHistoryService")
public class FoodOrderHistoryService {

    @Autowired
    private FoodOrderHistoryRepository foodOrderHistoryRepository;

    @Transactional
    public FoodOrderHistory createFoodOrderHistory(Date date, Product product, FoodOrderState state, int amount) {
        Timestamp moment = new Timestamp(date.getTime());
        FoodOrderHistory foodOrderHistory = new FoodOrderHistory(moment, product, state, amount);
        return foodOrderHistory;
    }

    public FoodOrderHistoryRepository getFoodOrderHistoryRepository() {
        return foodOrderHistoryRepository;
    }

    @Transactional
    public List<FoodOrderHistory> findAll() {
        return this.getFoodOrderHistoryRepository().findAll();
    }

    public void addToHistory(Date dateClosure, Product product, FoodOrderState state, int amount) {
        List<FoodOrderHistory> foodOrderHistories = this.getFoodOrderHistoryRepository().findBy(dateClosure, product, state);

        if (foodOrderHistories.size()>0)
        {
            for (FoodOrderHistory foodOrderHistory : foodOrderHistories)
            {
                foodOrderHistory.addAmount(amount);
                this.getFoodOrderHistoryRepository().update(foodOrderHistory);
            }
        }
        else
        {
            FoodOrderHistory foodOrderHistory = this.createFoodOrderHistory(dateClosure, product, state, amount);
            this.getFoodOrderHistoryRepository().save(foodOrderHistory);
        }

    }

    public List<FoodOrderHistory> findByDay(long momentClosure) {
        Date date = new Date(momentClosure);
        return this.getFoodOrderHistoryRepository().findByDay(date);
    }

    public List<FoodOrderHistory> findByDayFromTo(long from, long to) {
        Date dateFrom = new Date(from);
        Date dateTo = new Date(to);
        return this.getFoodOrderHistoryRepository().findByDayFromTo(dateFrom, dateTo);
    }
}
