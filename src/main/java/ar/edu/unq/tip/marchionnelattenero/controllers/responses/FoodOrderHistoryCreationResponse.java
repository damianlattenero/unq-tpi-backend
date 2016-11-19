package ar.edu.unq.tip.marchionnelattenero.controllers.responses;


import ar.edu.unq.tip.marchionnelattenero.models.FoodOrderHistory;
import ar.edu.unq.tip.marchionnelattenero.models.FoodOrderState;
import ar.edu.unq.tip.marchionnelattenero.models.Product;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

public class FoodOrderHistoryCreationResponse {
    private long moment;
    private int productId;
    private String productName;
    private String productDescription;
    private int countOrder;
    private int countCancelOrder;
    private int countCooked;
    private int countCancelCooked;
    //Totals
    private int countDiff;
    private int countTotalCancel;
    private int countTotalStock;

    public FoodOrderHistoryCreationResponse(long moment, Product product, EnumMap<FoodOrderState, Integer> amounts) {
        this.moment = moment;
        this.productId = product.getId();
        this.productName = product.getName();
        this.productDescription = product.getDescription();
        this.countOrder = 0;
        this.countCancelOrder = 0;
        this.countCooked = 0;
        this.countCancelCooked = 0;
        this.countDiff = 0;
        this.countTotalCancel = 0;
        this.countTotalStock = 0;

        amounts.forEach((k, v) -> setCounter(k, v));

        this.calculateTotals();
    }

    public static FoodOrderHistoryCreationResponse separateByState(FoodOrderHistory foodOrderHistory) {
        FoodOrderHistoryCreationResponse f = new FoodOrderHistoryCreationResponse(
                foodOrderHistory.getMoment().getTime(),
                foodOrderHistory.getProduct(),
                foodOrderHistory.getAmounts()
        );
        return f;
    }

    public static List<FoodOrderHistoryCreationResponse> buildMany(List<FoodOrderHistory> applicationRequests) {
        List<FoodOrderHistoryCreationResponse> listSeparatedStates = applicationRequests.stream().map(FoodOrderHistoryCreationResponse::separateByState).collect(Collectors.toList());
        List<FoodOrderHistoryCreationResponse> listAgrupated = new ArrayList<FoodOrderHistoryCreationResponse>();

        boolean isAgrupated;

        for (FoodOrderHistoryCreationResponse foodOrderHistorySeparatedState : listSeparatedStates) {
            isAgrupated = false;
            for (FoodOrderHistoryCreationResponse foodOrderHistoryAgruped : listAgrupated) {
                if (foodOrderHistorySeparatedState.equals(foodOrderHistoryAgruped)) {
                    foodOrderHistoryAgruped.addCounters(foodOrderHistorySeparatedState);
                    isAgrupated = true;
                    break;
                }
            }

            if (!isAgrupated) {
                listAgrupated.add(foodOrderHistorySeparatedState);
            }
        }

        return listAgrupated;
    }

    private void setCounter(FoodOrderState state, Integer amount) {
        switch (state) {
            case ORDER:
                setCountOrder(amount);
                break;
            case CANCELORDER:
                setCountCancelOrder(amount);
                break;
            case COOKED:
                setCountCooked(amount);
                break;
            case CANCELCOOKED:
                setCountCancelCooked(amount);
                break;
            default:
                break;
        }
    }

    private void addCounters(FoodOrderHistoryCreationResponse foodOrderHistory) {
        this.countOrder += foodOrderHistory.getCountOrder();
        this.countCancelOrder += foodOrderHistory.getCountCancelOrder();
        this.countCooked += foodOrderHistory.getCountCooked();
        this.countCancelCooked += foodOrderHistory.getCountCancelCooked();
        this.calculateTotals();
    }

    //Totals
    private void calculateTotals() {
        this.countDiff = this.getDiff();
        this.countTotalCancel = this.getTotalCancel();
        this.countTotalStock = this.getTotalStock();
    }

    private int getDiff() {
        return Math.abs(this.countOrder + this.countCancelOrder) - Math.abs(this.countCooked + this.countCancelCooked);
    }

    private int getTotalCancel() {
        return Math.abs(this.countCancelOrder) + Math.abs(this.countCancelOrder);
    }

    private int getTotalStock() {
        return Math.abs(this.countCooked + this.countCancelCooked) ;
    }

    @Override
    public String toString() {
        return "{\n"
                + "  Moment: '" + this.getMoment() + "'\n"
                + ", ProductId: '" + this.getProductId() + "'\n"
                + ", Product: '" + this.getProductName() + "'\n"
                + ", CountOrder: '" + this.getCountOrder() + "'\n"
                + ", CountCancelOrder: '" + this.getCountCancelOrder() + "'\n"
                + ", CountCooked: '" + this.getCountCooked() + "'\n"
                + ", CountCancelOrder: '" + this.getCountCancelCooked() + "'\n"
                + ", CountDiff: '" + this.getCountDiff() + "'\n"
                + ", CountTotalCancel: '" + this.getCountTotalCancel() + "'\n"
                + ", CountTotalStock: '" + this.getCountTotalStock() + "'\n"
                + "}\n";
    }

    @Override
    public boolean equals(Object obj) {
        FoodOrderHistoryCreationResponse f = (FoodOrderHistoryCreationResponse) obj;
        return (this.moment == f.getMoment()) && (this.productId == f.getProductId());
    }

    public long getMoment() {
        return moment;
    }

    public void setMoment(long moment) {
        this.moment = moment;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getCountOrder() {
        return countOrder;
    }

    public void setCountOrder(int countOrder) {
        this.countOrder = countOrder;
    }

    public int getCountCancelOrder() {
        return countCancelOrder;
    }

    public void setCountCancelOrder(int countCancelOrder) {
        this.countCancelOrder = countCancelOrder;
    }

    public int getCountCooked() {
        return countCooked;
    }

    public void setCountCooked(int countCooked) {
        this.countCooked = countCooked;
    }

    public int getCountCancelCooked() {
        return countCancelCooked;
    }

    public void setCountCancelCooked(int countCancelCooked) {
        this.countCancelCooked = countCancelCooked;
    }

    public int getCountDiff() {
        return countDiff;
    }

    public void setCountDiff(int countDiff) {
        this.countDiff = countDiff;
    }

    public int getCountTotalCancel() {
        return countTotalCancel;
    }

    public void setCountTotalCancel(int countTotalCancel) {
        this.countTotalCancel = countTotalCancel;
    }

    public int getCountTotalStock() {
        return countTotalStock;
    }

    public void setCountTotalStock(int countTotalStock) {
        this.countTotalStock = countTotalStock;
    }
}
