package ar.edu.unq.tip.marchionnelattenero.controllers;


import ar.edu.unq.tip.marchionnelattenero.controllers.requests.FoodOrderClosureBody;
import ar.edu.unq.tip.marchionnelattenero.controllers.responses.FoodOrderClosureCreationResponse;
import ar.edu.unq.tip.marchionnelattenero.controllers.responses.FoodOrderHistoryCreationResponse;
import ar.edu.unq.tip.marchionnelattenero.models.UserModel;
import ar.edu.unq.tip.marchionnelattenero.repositories.UserTokenRepository;
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
    private UserTokenRepository userTokenRepository;
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

    //TODO: Mostrar los datos que deberia archivar
/*
    @POST
    @Path("showClosure")
    @Consumes("application/json")
    @Produces("application/json")
    public List<FoodOrderHistoryCreationResponse> showClosure(FoodOrderClosureBody foodOrderClosureBody) {
        System.err.println("FoodOrderClosureBody: '" + foodOrderClosureBody.toString() + "'");
        this.foodOrderClosureService.generateFoodOrderClosure(foodOrderClosureBody.getUser(), foodOrderClosureBody.getFrom(),foodOrderClosureBody.getTo());
        return FoodOrderHistoryCreationResponse.buildMany(this.foodOrderHistoryService.findByDayFromTo(foodOrderClosureBody.getFrom(), foodOrderClosureBody.getTo()));
    }
*/

    @POST
    @Path("generateClosure")
    @Consumes("application/json")
    @Produces("application/json")
    public List<FoodOrderHistoryCreationResponse> generateClosure(@QueryParam("token") String token, FoodOrderClosureBody foodOrderClosureBody) {
        System.err.println("FoodOrderClosureBody: '" + foodOrderClosureBody.toString() + "'");
        UserModel user = this.userTokenRepository.findByUserToken(token).getUserModel();
        this.foodOrderClosureService.generateFoodOrderClosure(user, foodOrderClosureBody.getFrom(),foodOrderClosureBody.getTo());
        return FoodOrderHistoryCreationResponse.buildMany(this.foodOrderHistoryService.findByDayFromTo(foodOrderClosureBody.getFrom(), foodOrderClosureBody.getTo()));
    }

    @POST
    @Path("generateClosureToday")
    @Consumes("application/json")
    @Produces("application/json")
    public List<FoodOrderHistoryCreationResponse> generateClosureToday(@QueryParam("token") String token) {
        UserModel user = this.userTokenRepository.findByUserToken(token).getUserModel();
        long dateClosure = this.foodOrderClosureService.generateFoodOrderClosure(user);
        return FoodOrderHistoryCreationResponse.buildMany(this.foodOrderHistoryService.findByDay(dateClosure));
    }

}
