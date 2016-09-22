package ar.edu.unq.tip.marchionnelattenero.controllers;


import ar.edu.unq.tip.marchionnelattenero.controllers.requests.FoodOrderCreationBody;
import ar.edu.unq.tip.marchionnelattenero.controllers.responses.FoodOrderCreationResponse;
import ar.edu.unq.tip.marchionnelattenero.factories.FoodOrderFactory;
import ar.edu.unq.tip.marchionnelattenero.models.FoodOrder;
import ar.edu.unq.tip.marchionnelattenero.models.Product;
import ar.edu.unq.tip.marchionnelattenero.repositories.FoodOrderRepository;
import ar.edu.unq.tip.marchionnelattenero.repositories.ProductRepository;
import ar.edu.unq.tip.marchionnelattenero.services.FoodOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import java.util.List;

@Path("foodOrders")
@Controller("foodOrderController")
public class FoodOrderController {

    private FoodOrderService foodOrderService;
    private FoodOrderFactory foodOrderFactory;
    @Autowired
    private ProductRepository productRepository;
    //TODO create FoodOrderAdding
    @Autowired
    private FoodOrderRepository foodOrderRepository;

    @GET
    @Path("all")
    @Produces("application/json")
    public List<FoodOrder> getAll() {
        return this.getFoodOrderService().findAll();
    }

    @POST
    @Path("create")
    @Consumes("application/json")
    @Produces("application/json")
    public FoodOrderCreationResponse create(FoodOrderCreationBody foodOrderBody) {
        Product p = productRepository.findById(foodOrderBody.getIdProduct());
        FoodOrder foodOrder = this.foodOrderService.createFoodOrder(p, foodOrderBody.getAmount());
        this.foodOrderRepository.save(foodOrder);
        return FoodOrderCreationResponse.build(foodOrder);
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public FoodOrder findFoodOrdersByID(@PathParam("id") Integer id) {
        FoodOrder foodOrderFound = this.getFoodOrderFactory().getFoodOrderById(id);
        return foodOrderFound;
    }

    @Autowired
    public void setFoodOrderService(FoodOrderService foodOrderService) {
        this.foodOrderService = foodOrderService;
    }

    @Autowired
    public void setFoodOrderFactory(FoodOrderFactory foodOrderFactory) {
        this.foodOrderFactory = foodOrderFactory;
    }

    public FoodOrderFactory getFoodOrderFactory() {
        return foodOrderFactory;
    }

    public FoodOrderService getFoodOrderService() {
        return foodOrderService;
    }

}
