package ar.edu.unq.tip.marchionnelattenero.services;

import ar.edu.unq.tip.marchionnelattenero.models.*;
import ar.edu.unq.tip.marchionnelattenero.models.utils.DateHelper;
import ar.edu.unq.tip.marchionnelattenero.repositories.FoodOrderClosureRepository;
import ar.edu.unq.tip.marchionnelattenero.repositories.FoodOrderRepository;
import javafx.util.Pair;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service("foodOrderClosureService")
public class FoodOrderClosureService {
    @Autowired
    private FoodOrderClosureRepository foodOrderClosureRepository;

    @Autowired
    private FoodOrderHistoryService foodOrderHistoryService;

    @Autowired
    private FoodOrderRepository foodOrderRepository;

    @Transactional
    private Collection<FoodOrderHistory> archiveFoodOrders(Date dateClosure, boolean generateClosure) {
        List<FoodOrder> foodOrders = this.getFoodOrderRepository().findByDayForArchived(dateClosure);

        Map<Pair<Date, Product>, FoodOrderHistory> foodOrderHistories = new HashMap<>();
        FoodOrderHistory foodOrderHistory;

        System.out.println("foodOrders: " + foodOrders);
        if (foodOrders.size() > 0) {

            for (FoodOrder foodOrder : foodOrders) {
                Pair<Date, Product> key = new Pair<>(dateClosure, foodOrder.getProduct());

                if (generateClosure) {
                    //Repository
                    foodOrderHistory = this.getFoodOrderHistoryService().findOrCreate(dateClosure, foodOrder.getProduct());
                }
                else {
                    //Memory
                    foodOrderHistory = foodOrderHistories.getOrDefault(key, new FoodOrderHistory(dateClosure, foodOrder.getProduct()));
                }

                foodOrderHistory.addAmount(foodOrder.getState(), foodOrder.getAmount());


                if (generateClosure) {
                    //Repository
                    this.getFoodOrderHistoryService().getFoodOrderHistoryRepository().saveOrUpdate(foodOrderHistory);
                    foodOrder.setArchived();
                    this.getFoodOrderRepository().update(foodOrder);
                }
                else
                    //Memory
                    foodOrderHistories.put(key, foodOrderHistory);
            }
        }

        System.out.println("Cant. FoodOrder: " + foodOrders.size() + " agrupadas en:" + foodOrderHistories.size() + ".");
        return foodOrderHistories.values();
    }

    @Transactional
    public List<FoodOrderHistory> showFoodOrderClosure(long from, long to) {
        Date dateFrom = DateHelper.getDateWithoutTime(from);
        Date dateTo = DateHelper.getDateWithoutTime(to);

        System.err.println("DayFrom: '" + dateFrom + "'");
        System.err.println("DayTo: '" + dateTo + "'");

        List<FoodOrderHistory> foodOrderHistories = new ArrayList<>();

        Date dateClosure = dateFrom;
        while (dateClosure.compareTo(dateTo) <= 0) {
            System.err.println("DayClosure: '" + dateClosure + "'");
            foodOrderHistories.addAll(this.archiveFoodOrders(dateClosure, false));
            dateClosure = DateHelper.getTomorrowDate(dateClosure);
        }

        return foodOrderHistories;
    }

    @Transactional
    public long generateFoodOrderClosure(UserModel user) {
        long dateClosure = DateTime.now().getMillis();
        this.generateFoodOrderClosure(user, dateClosure, dateClosure);
        return dateClosure;
    }

    @Transactional
    public void generateFoodOrderClosure(UserModel user, long from, long to) {
        Date dateFrom = DateHelper.getDateWithoutTime(from);
        Date dateTo = DateHelper.getDateWithoutTime(to);

        System.err.println("DayFrom: '" + dateFrom + "'");
        System.err.println("DayTo: '" + dateTo + "'");

        Date dateClosure = dateFrom;
        while (dateClosure.compareTo(dateTo) <= 0) {
            System.err.println("DayClosure: '" + dateClosure + "'");
            this.archiveFoodOrders(dateClosure, true);

            FoodOrderClosure foodOrderClosure = new FoodOrderClosure(user, dateClosure);
            this.getFoodOrderClosureRepository().save(foodOrderClosure);

            dateClosure = DateHelper.getTomorrowDate(dateClosure);
        }
    }

    public FoodOrderClosureRepository getFoodOrderClosureRepository() {
        return foodOrderClosureRepository;
    }

    public FoodOrderHistoryService getFoodOrderHistoryService() {
        return foodOrderHistoryService;
    }

    public FoodOrderRepository getFoodOrderRepository() {
        return foodOrderRepository;
    }

    @Transactional
    public List<FoodOrderClosure> findAll() {
        return this.getFoodOrderClosureRepository().findAll();
    }

}
