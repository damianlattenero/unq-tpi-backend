package ar.edu.unq.tip.marchionnelattenero.controllers;


import ar.edu.unq.tip.marchionnelattenero.controllers.requests.FoodOrderCreationBody;
import ar.edu.unq.tip.marchionnelattenero.controllers.responses.FoodOrderCreationResponse;
import ar.edu.unq.tip.marchionnelattenero.controllers.responses.ProductPendingResponse;
import ar.edu.unq.tip.marchionnelattenero.models.FoodOrder;
import ar.edu.unq.tip.marchionnelattenero.models.FoodOrderState;
import ar.edu.unq.tip.marchionnelattenero.services.FoodOrderService;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import java.sql.Timestamp;
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
        FoodOrder foodOrder = this.foodOrderService.createFoodOrder(foodOrderBody.getProductId(), foodOrderBody.getProductAmount(), foodOrderBody.getUser(), foodOrderBody.getState());
        return ProductPendingResponse.build(foodOrder.getProduct());
    }

    @POST
    @Path("order")
    @Consumes("application/json")
    @Produces("application/json")
    public ProductPendingResponse order(FoodOrderCreationBody foodOrderBody) {
        FoodOrder foodOrder = this.foodOrderService.createFoodOrder(foodOrderBody.getProductId(), 1, foodOrderBody.getUser(), FoodOrderState.ORDER.toString());
        return ProductPendingResponse.build(foodOrder.getProduct());
    }

    @POST
    @Path("cancelorder")
    @Consumes("application/json")
    @Produces("application/json")
    public ProductPendingResponse cancelOrder(FoodOrderCreationBody foodOrderBody) {
        FoodOrder foodOrder = this.foodOrderService.createFoodOrder(foodOrderBody.getProductId(), -1, foodOrderBody.getUser(), FoodOrderState.CANCELORDER.toString());
        return ProductPendingResponse.build(foodOrder.getProduct());
    }

    @POST
    @Path("cooked")
    @Consumes("application/json")
    @Produces("application/json")
    public ProductPendingResponse cooked(FoodOrderCreationBody foodOrderBody) {
        FoodOrder foodOrder = this.foodOrderService.createFoodOrder(foodOrderBody.getProductId(), -1, foodOrderBody.getUser(), FoodOrderState.COOKED.toString());
        return ProductPendingResponse.build(foodOrder.getProduct());
    }

    @POST
    @Path("cancelcooked")
    @Consumes("application/json")
    @Produces("application/json")
    public ProductPendingResponse cancelCooked(FoodOrderCreationBody foodOrderBody) {
        FoodOrder foodOrder = this.foodOrderService.createFoodOrder(foodOrderBody.getProductId(), 1, foodOrderBody.getUser(), FoodOrderState.CANCELCOOKED.toString());
        return ProductPendingResponse.build(foodOrder.getProduct());
    }

/*
    @GET
    @Path("{id}")
    @Produces("application/json")
    public FoodOrderCreationResponse findFoodOrdersByID(@PathParam("id") Integer id) {
        return FoodOrderCreationResponse.separateByState(foodOrderService.findById(id));
    }
*/

    @GET
    @Path("{day}")
    @Produces("application/json")
    public List<FoodOrderCreationResponse> findFoodOrdersByID(@PathParam("day") String day) {
        DateTimeFormatter formatter = ISODateTimeFormat.dateTimeParser();
        //DateTimeFormatter.forPattern("yyyy-MM-dd");
        Timestamp timestamp = new Timestamp(DateTime.parse(day, formatter).getMillis());
        return FoodOrderCreationResponse.buildMany(foodOrderService.findByDay(timestamp));
    }

/*
    @GET
    @Path("{days}")
    @Produces("application/json")
    public List<Timestamp>  findFoodOrdersByID(@PathParam("days") Integer count) {
        return (foodOrderService.findAllDays(count));
    }
*/


}
