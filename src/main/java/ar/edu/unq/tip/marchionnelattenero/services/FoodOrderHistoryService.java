package ar.edu.unq.tip.marchionnelattenero.services;

import ar.edu.unq.tip.marchionnelattenero.models.FoodOrderHistory;
import ar.edu.unq.tip.marchionnelattenero.models.FoodOrderState;
import ar.edu.unq.tip.marchionnelattenero.models.Product;
import ar.edu.unq.tip.marchionnelattenero.models.utils.DateHelper;
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

    public FoodOrderHistoryRepository getFoodOrderHistoryRepository() {
        return foodOrderHistoryRepository;
    }

    @Transactional
    public List<FoodOrderHistory> findAll() {
        return this.getFoodOrderHistoryRepository().findAll();
    }

    @Transactional
    public void addToHistory(Date dateClosure, Product product, FoodOrderState state, int amount) {
        FoodOrderHistory foodOrderHistory = this.getFoodOrderHistoryRepository().findBy(dateClosure, product);

        if (foodOrderHistory != null)
        {
            foodOrderHistory.addAmount(state, amount);
            this.getFoodOrderHistoryRepository().update(foodOrderHistory);
        }
        else
        {
            foodOrderHistory = new FoodOrderHistory(dateClosure, product);
            foodOrderHistory.addAmount(state, amount);
            this.getFoodOrderHistoryRepository().save(foodOrderHistory);
        }
    }

    public List<FoodOrderHistory> findByDay(long momentClosure) {
        Date date = new Date(momentClosure);
        return this.getFoodOrderHistoryRepository().findByDay(date);
    }

    public List<FoodOrderHistory> findByDayFromTo(long from, long to) {
        Date dateFrom = DateHelper.getDateWithoutTime(from);
        Date dateTo = DateHelper.getDateWithoutTime(to);
        return this.getFoodOrderHistoryRepository().findByDayFromTo(dateFrom, dateTo);
    }
}
