package ar.edu.unq.tip.marchionnelattenero.services;

import ar.edu.unq.tip.marchionnelattenero.models.FoodOrder;
import ar.edu.unq.tip.marchionnelattenero.models.FoodOrderHistory;
import ar.edu.unq.tip.marchionnelattenero.models.utils.DateHelper;
import ar.edu.unq.tip.marchionnelattenero.repositories.FoodOrderHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public FoodOrderHistory addToHistory(Date dateClosure, FoodOrder foodOrder) {
        System.out.println("Buscando... dia: " + dateClosure.toString());
        System.out.println("FoodOrder Like " + foodOrder.toString());
        FoodOrderHistory foodOrderHistory = this.getFoodOrderHistoryRepository().findBy(dateClosure, foodOrder.getProduct(), foodOrder.getState());

        if (foodOrderHistory == null) {
            foodOrderHistory = new FoodOrderHistory(dateClosure, foodOrder.getProduct());
//            foodOrderHistory.setAmounts();
        }
        else
            System.out.println("Se encontro FoodOrderHistory.");
        System.out.println("FoodOrderHistory es null: " + (foodOrderHistory == null));
        //TODO: Revisar porque no se encuentra el mapa???
        System.out.println("Mapa es null: " + (foodOrderHistory.getAmounts() == null));

        foodOrderHistory.addAmount(foodOrder.getState(), foodOrder.getAmount());

        return foodOrderHistory;
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

    public void saveorupdate(FoodOrderHistory foodOrderHistory) {
        this.getFoodOrderHistoryRepository().saveorupdate(foodOrderHistory);
    }
}
