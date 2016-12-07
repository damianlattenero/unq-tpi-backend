package ar.edu.unq.tip.marchionnelattenero.controllers;


import ar.edu.unq.tip.marchionnelattenero.controllers.requests.FoodOrderCreationBody;
import ar.edu.unq.tip.marchionnelattenero.controllers.responses.FoodOrderCreationResponse;
import ar.edu.unq.tip.marchionnelattenero.controllers.responses.ProductPendingResponse;
import ar.edu.unq.tip.marchionnelattenero.models.FoodOrder;
import ar.edu.unq.tip.marchionnelattenero.models.UserModel;
import ar.edu.unq.tip.marchionnelattenero.models.caches.Cache;
import ar.edu.unq.tip.marchionnelattenero.repositories.UserTokenRepository;
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
/*
    @POST
    @Path("create")
    @Consumes("application/json")
    @Produces("application/json")
    public ProductPendingResponse create(@QueryParam("token") String token, FoodOrderCreationBody foodOrderBody) {
        UserModel user = this.userTokenRepository.findByUserToken(token).getUserModel();
        System.out.println("User by Token: " + user.getNickname());
        System.out.println("foodOrderBody.User: " + foodOrderBody.getUser());
        FoodOrder foodOrder = this.foodOrderService.createFoodOrder(foodOrderBody.getProductId(), foodOrderBody.getProductAmount(), user, foodOrderBody.getState());
        return ProductPendingResponse.build(foodOrder.getProduct());
    }*/

    @POST
    @Path("order")
    @Consumes("application/json")
    @Produces("application/json")
    public ProductPendingResponse order(@QueryParam("token") String token, FoodOrderCreationBody foodOrderBody) {
        UserModel user = Cache.getInstance().getUserByToken(token);
//        UserModel user = this.userTokenRepository.findByUserToken(token).getUserModel();
        FoodOrder foodOrder = this.foodOrderService.order(foodOrderBody.getProductId(), user, 1);
        return ProductPendingResponse.build(foodOrder.getProduct());
    }

    @POST
    @Path("cancelorder")
    @Consumes("application/json")
    @Produces("application/json")
    public ProductPendingResponse cancelOrder(@QueryParam("token") String token, FoodOrderCreationBody foodOrderBody) {
        UserModel user = Cache.getInstance().getUserByToken(token);
//        UserModel user = this.userTokenRepository.findByUserToken(token).getUserModel();
        FoodOrder foodOrder = this.foodOrderService.cancelOrder(foodOrderBody.getProductId(), user, -1);
        return ProductPendingResponse.build(foodOrder.getProduct());
    }

    @POST
    @Path("cooked")
    @Consumes("application/json")
    @Produces("application/json")
    public ProductPendingResponse cooked(@QueryParam("token") String token, FoodOrderCreationBody foodOrderBody) {
        UserModel user = Cache.getInstance().getUserByToken(token);
//        UserModel user = this.userTokenRepository.findByUserToken(token).getUserModel();
        FoodOrder foodOrder = this.foodOrderService.coocked(foodOrderBody.getProductId(), user, -1);
        return ProductPendingResponse.build(foodOrder.getProduct());
    }

    @POST
    @Path("cancelcooked")
    @Consumes("application/json")
    @Produces("application/json")
    public ProductPendingResponse cancelCooked(@QueryParam("token") String token, FoodOrderCreationBody foodOrderBody) {
        UserModel user = Cache.getInstance().getUserByToken(token);
//        UserModel user = this.userTokenRepository.findByUserToken(token).getUserModel();
        FoodOrder foodOrder = this.foodOrderService.cancelCoocked(foodOrderBody.getProductId(), user, 1);
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
