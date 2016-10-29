package ar.edu.unq.tip.marchionnelattenero.services;

import ar.edu.unq.tip.marchionnelattenero.factories.FoodOrderFactory;
import ar.edu.unq.tip.marchionnelattenero.models.FoodOrder;
import ar.edu.unq.tip.marchionnelattenero.models.Product;
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

    @Autowired
    private FoodOrderFactory foodOrderFactory;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public FoodOrder createFoodOrder(int idProduct, int amount, String user) {
        Product p = productRepository.findById(idProduct);
        //TODO: Ir a buscar el Usuarios a la Base
        FoodOrder foodOrder = new FoodOrder(p, amount, user);
        this.foodOrderRepository.save(foodOrder);
        return foodOrder;
    }

    @Transactional
    public List<FoodOrder> findAll() {
        return this.getFoodOrderRepository().findAll();
    }

    @Transactional
    public void deleteFoodOrder(FoodOrder foodOrder) {
        foodOrderRepository.delete(foodOrder);
    }

    public FoodOrderRepository getFoodOrderRepository() {
        return foodOrderRepository;
    }

    public FoodOrder findById(Integer id) {
        return this.foodOrderRepository.findById(id);
    }

    public List<FoodOrder> findByDay(Timestamp day) {
        return this.foodOrderRepository.findByDay(day);
    }

    public List<Timestamp> findAllDays(int count) {
        return this.foodOrderRepository.findAllDays(count);
    }
}
