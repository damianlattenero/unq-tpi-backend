package ar.edu.unq.tip.marchionnelattenero.controllers;


import ar.edu.unq.tip.marchionnelattenero.controllers.requests.FoodOrderCreationBody;
import ar.edu.unq.tip.marchionnelattenero.controllers.responses.FoodOrderCreationResponse;
import ar.edu.unq.tip.marchionnelattenero.models.FoodOrder;
import ar.edu.unq.tip.marchionnelattenero.services.FoodOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import java.util.List;

@Path("foodOrders")
@Controller("foodOrderController")
public class FoodOrderController {

    @Autowired
    private FoodOrderService foodOrderService;

    @GET
    @Path("all")
    @Produces("application/json")
    public List<FoodOrderCreationResponse> getAll() {
        return FoodOrderCreationResponse.buildMany(this.foodOrderService.findAll());
    }

    @POST
    @Path("create")
    @Consumes("application/json")
    @Produces("application/json")
    public FoodOrderCreationResponse create(FoodOrderCreationBody foodOrderBody) {
        FoodOrder foodOrder = this.foodOrderService.createFoodOrder(foodOrderBody.getProductId(), foodOrderBody.getProductAmount());
        return FoodOrderCreationResponse.build(foodOrder);
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public FoodOrderCreationResponse findFoodOrdersByID(@PathParam("id") Integer id) {
        return FoodOrderCreationResponse.build(foodOrderService.getFoodOrder(id));
    }


}
