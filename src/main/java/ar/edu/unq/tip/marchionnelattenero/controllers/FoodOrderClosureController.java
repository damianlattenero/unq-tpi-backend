package ar.edu.unq.tip.marchionnelattenero.controllers;


import ar.edu.unq.tip.marchionnelattenero.controllers.requests.FoodOrderClosureBody;
import ar.edu.unq.tip.marchionnelattenero.controllers.responses.FoodOrderClosureCreationResponse;
import ar.edu.unq.tip.marchionnelattenero.controllers.responses.FoodOrderHistoryCreationResponse;
import ar.edu.unq.tip.marchionnelattenero.services.FoodOrderClosureService;
import ar.edu.unq.tip.marchionnelattenero.services.FoodOrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.*;
import java.util.List;

@Path("foodOrdersClosure")
@Controller("foodOrderClosureController")
public class FoodOrderClosureController {

    @Autowired
    private FoodOrderClosureService foodOrderClosureService;

    @Autowired
    private FoodOrderHistoryService foodOrderHistoryService;

    @GET
    @Path("all")
    @Produces("application/json")
    public List<FoodOrderClosureCreationResponse> getAll() {
        return FoodOrderClosureCreationResponse.buildMany(this.foodOrderClosureService.findAll());
    }

    @POST
    @Path("generateClosure")
    @Consumes("application/json")
    @Produces("application/json")
    public List<FoodOrderHistoryCreationResponse> generateClosure(FoodOrderClosureBody foodOrderClosureBody) {
        this.foodOrderClosureService.generateFoodOrderClosure(foodOrderClosureBody.getMomentClosure(), foodOrderClosureBody.getUser());
        return FoodOrderHistoryCreationResponse.buildMany(this.foodOrderHistoryService.findByDay(foodOrderClosureBody.getMomentClosure()));
    }

}
