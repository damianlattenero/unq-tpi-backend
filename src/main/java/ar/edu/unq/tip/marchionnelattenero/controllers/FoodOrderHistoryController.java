package ar.edu.unq.tip.marchionnelattenero.controllers;


import ar.edu.unq.tip.marchionnelattenero.controllers.responses.FoodOrderHistoryCreationResponse;
import ar.edu.unq.tip.marchionnelattenero.repositories.FoodOrderHistoryRepository;
import ar.edu.unq.tip.marchionnelattenero.services.FoodOrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

@Path("foodOrdersHistory")
@Controller("foodOrderHistoryController")
public class FoodOrderHistoryController {

    @Autowired
    private FoodOrderHistoryRepository foodOrderHistoryRepository;

    @GET
    @Path("all")
    @Produces("application/json")
    public List<FoodOrderHistoryCreationResponse> getAll() {
        return FoodOrderHistoryCreationResponse.buildMany(this.foodOrderHistoryRepository.findAll());
    }

}
