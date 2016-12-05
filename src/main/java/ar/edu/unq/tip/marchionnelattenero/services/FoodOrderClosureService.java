package ar.edu.unq.tip.marchionnelattenero.services;

import ar.edu.unq.tip.marchionnelattenero.models.FoodOrder;
import ar.edu.unq.tip.marchionnelattenero.models.FoodOrderClosure;
import ar.edu.unq.tip.marchionnelattenero.models.UserModel;
import ar.edu.unq.tip.marchionnelattenero.repositories.FoodOrderClosureRepository;
import ar.edu.unq.tip.marchionnelattenero.repositories.FoodOrderRepository;
import ar.edu.unq.tip.marchionnelattenero.models.utils.DateHelper;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service("foodOrderClosureService")
public class FoodOrderClosureService {
    @Autowired
    private FoodOrderClosureRepository foodOrderClosureRepository;

    @Autowired
    private FoodOrderHistoryService foodOrderHistoryService;

    @Autowired
    private FoodOrderRepository foodOrderRepository;

    @Transactional
    private int archiveFoodOrders(Date dateClosure) {
        List<FoodOrder> foodOrders = this.getFoodOrderRepository().findByDayForArchived(dateClosure);

        System.err.println("foodOrders: " + foodOrders);
        if (foodOrders.size() > 0) {
            for (FoodOrder foodOrder : foodOrders) {
                this.getFoodOrderHistoryService().addToHistory(dateClosure, foodOrder.getProduct(), foodOrder.getState(), foodOrder.getAmount());
                foodOrder.setArchived();
                this.getFoodOrderRepository().update(foodOrder);
            }
        }
        return foodOrders.size();
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
            int cantHistories = this.archiveFoodOrders(dateClosure);
            System.err.println("cantHistories: " + cantHistories + ".");

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

    public FoodOrderClosure findById(Integer id) {
        return this.getFoodOrderClosureRepository().findById(id);
    }

}
