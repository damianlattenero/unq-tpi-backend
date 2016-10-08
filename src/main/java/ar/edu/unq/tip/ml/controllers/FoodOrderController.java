package ar.edu.unq.tip.ml.controllers;


import ar.edu.unq.tip.ml.controllers.requests.FoodOrderCreationBody;
import ar.edu.unq.tip.ml.controllers.responses.FoodOrderCreationResponse;
import ar.edu.unq.tip.ml.controllers.responses.ProductPendingResponse;
import ar.edu.unq.tip.ml.models.FoodOrder;
import ar.edu.unq.tip.ml.services.FoodOrderService;
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
    public ProductPendingResponse create(FoodOrderCreationBody foodOrderBody) {
        FoodOrder foodOrder = this.foodOrderService.createFoodOrder(foodOrderBody.getProductId(), foodOrderBody.getProductAmount());
        return ProductPendingResponse.build(foodOrder.getProduct());
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public FoodOrderCreationResponse findFoodOrdersByID(@PathParam("id") Integer id) {
        return FoodOrderCreationResponse.build(foodOrderService.findById(id));
    }


}