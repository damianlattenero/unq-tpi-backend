package ar.edu.unq.tip.marchionnelattenero.controllers.responses;


import ar.edu.unq.tip.marchionnelattenero.models.FoodOrder;

public class FoodOrderCreationResponse {

    //TODO PENSAR BIEN QUE RESPONDE, POR AHORA LA CANTIDAD DE PRODS  PENDIENTES
    private int productsPending;

    public FoodOrderCreationResponse(int productsPending) {

        this.setProductsPending(productsPending);

    }


    public static FoodOrderCreationResponse build(FoodOrder foodOrder) {
        //TODO: ACTUALIZAR LA CANTIDAD DEL MAPA
        return new FoodOrderCreationResponse(foodOrder.getAmount());
    }

    public int getProductsPending() {
        return productsPending;
    }

    public void setProductsPending(int productsPending) {
        this.productsPending = productsPending;
    }

}
