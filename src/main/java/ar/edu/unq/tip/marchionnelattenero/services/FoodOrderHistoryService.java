package ar.edu.unq.tip.marchionnelattenero.services;

import ar.edu.unq.tip.marchionnelattenero.models.FoodOrderHistory;
import ar.edu.unq.tip.marchionnelattenero.models.Product;
import ar.edu.unq.tip.marchionnelattenero.models.utils.DateHelper;
import ar.edu.unq.tip.marchionnelattenero.repositories.FoodOrderHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Transactional
@Service("foodOrderHistoryService")
public class FoodOrderHistoryService {

    @Autowired
    private FoodOrderHistoryRepository foodOrderHistoryRepository;

    @Transactional
    public FoodOrderHistory createFoodOrderHistory(Date date, Product product) {
        Timestamp moment = new Timestamp(date.getTime());
        FoodOrderHistory foodOrderHistory = new FoodOrderHistory(moment, product);
        return foodOrderHistory;
    }

    @Transactional
    public List<FoodOrderHistory> findAll() {
        return this.foodOrderHistoryRepository.findAll();
    }

    @Transactional
    public List<FoodOrderHistory> findByDay(long momentClosure) {
        Date date = new Date(momentClosure);
        return this.foodOrderHistoryRepository.findByDay(date);
    }

    @Transactional
    public List<FoodOrderHistory> findByDayFromTo(long from, long to) {
        Date dateFrom = DateHelper.getDateWithoutTime(from);
        Date dateTo = DateHelper.getDateWithoutTime(to);
        return this.foodOrderHistoryRepository.findByDayFromTo(dateFrom, dateTo);
    }

    @Transactional
    public FoodOrderHistory findOrCreate(Date dateClosure, Product product) {
        FoodOrderHistory foodOrderHistory = this.foodOrderHistoryRepository.findBy(dateClosure, product);

        if (foodOrderHistory == null)
            foodOrderHistory = this.createFoodOrderHistory(dateClosure, product);

        return foodOrderHistory;
    }
}
