package ar.edu.unq.tip.marchionnelattenero.services;

import ar.edu.unq.tip.marchionnelattenero.models.FoodOrder;
import ar.edu.unq.tip.marchionnelattenero.models.FoodOrderClosure;
import ar.edu.unq.tip.marchionnelattenero.models.FoodOrderHistory;
import ar.edu.unq.tip.marchionnelattenero.models.UserModel;
import ar.edu.unq.tip.marchionnelattenero.repositories.FoodOrderClosureRepository;
import ar.edu.unq.tip.marchionnelattenero.repositories.FoodOrderRepository;
import ar.edu.unq.tip.marchionnelattenero.models.utils.DateHelper;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("foodOrderClosureService")
public class FoodOrderClosureService {
    @Autowired
    private FoodOrderClosureRepository foodOrderClosureRepository;

    @Autowired
    private FoodOrderHistoryService foodOrderHistoryService;

    @Autowired
    private FoodOrderRepository foodOrderRepository;

    @Transactional
    public Set<FoodOrderHistory> showClosure(long from, long to) {
        Date dateFrom = DateHelper.getDateWithoutTime(from);
        Date dateTo = DateHelper.getDateWithoutTime(to);

        System.err.println("DayFrom: '" + dateFrom + "'");
        System.err.println("DayTo: '" + dateTo + "'");

        Set<FoodOrderHistory> foodOrderHistories = new HashSet<FoodOrderHistory>();

        Date dateClosure = dateFrom;
        while (dateClosure.compareTo(dateTo) <= 0) {
            foodOrderHistories.addAll(this.archiveFoodOrdersShow(dateClosure));
            dateClosure = DateHelper.getTomorrowDate(dateClosure);
        }

        return foodOrderHistories;
    }

    @Transactional
    private Set<FoodOrderHistory> archiveFoodOrdersShow(Date dateClosure) {
        Set<FoodOrderHistory> foodOrderHistories = new HashSet<FoodOrderHistory>();

        List<FoodOrder> foodOrders = this.getFoodOrderRepository().findByDayForArchived(dateClosure);
        System.err.println("foodOrders: " + foodOrders);
        if (foodOrders.size() > 0) {
            FoodOrderHistory foodOrderHistory;
            for (FoodOrder foodOrder : foodOrders) {

                foodOrderHistory = this.getFoodOrderHistoryService().addToHistory(dateClosure, foodOrder);
                foodOrderHistories.add(foodOrderHistory);
            }
        }
        else
            System.err.println("Don't exists 'foodOrders' for archived.");

        return foodOrderHistories;
    }

    @Transactional
    private int archiveFoodOrders(Date dateClosure) {
        List<FoodOrder> foodOrders = this.getFoodOrderRepository().findByDayForArchived(dateClosure);

        System.err.println("foodOrders: " + foodOrders);
        if (foodOrders.size() > 0) {
            for (FoodOrder foodOrder : foodOrders) {

                FoodOrderHistory foodOrderHistory = this.getFoodOrderHistoryService().addToHistory(dateClosure, foodOrder);

                this.getFoodOrderHistoryService().saveorupdate(foodOrderHistory);
                foodOrder.setArchived();
                this.getFoodOrderRepository().update(foodOrder);
            }
        }
        else
            System.err.println("Don't exists 'foodOrders' for archived.");

        return foodOrders.size();
    }

    @Transactional
    public long generateClosure(UserModel user) {
        long dateClosure = DateTime.now().getMillis();
        this.generateClosure(user, dateClosure, dateClosure);
        return dateClosure;
    }

    @Transactional
    public void generateClosure(UserModel user, long from, long to) {
        Date dateFrom = DateHelper.getDateWithoutTime(from);
        Date dateTo = DateHelper.getDateWithoutTime(to);

        System.err.println("DayFrom: '" + dateFrom + "'");
        System.err.println("DayTo: '" + dateTo + "'");

        Date dateClosure = dateFrom;
        while (dateClosure.compareTo(dateTo) <= 0) {
            System.err.println("DayClosure: '" + dateClosure + "'");
            int cantHistories = this.archiveFoodOrders(dateClosure);
            System.err.println("cantHistories: " + cantHistories + ".");
            dateClosure = DateHelper.getTomorrowDate(dateClosure);
        }

        FoodOrderClosure foodOrderClosure = new FoodOrderClosure(user, dateClosure);
        this.getFoodOrderClosureRepository().save(foodOrderClosure);
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
