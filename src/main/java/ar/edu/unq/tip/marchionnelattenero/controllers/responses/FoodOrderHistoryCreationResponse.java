package ar.edu.unq.tip.marchionnelattenero.controllers.responses;


import ar.edu.unq.tip.marchionnelattenero.models.FoodOrderHistory;

import java.util.ArrayList;
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

    public FoodOrderHistoryCreationResponse(long moment, int productId, String name, String description) {
        this.moment = moment;
        this.productId = productId;
        this.productName = name;
        this.productDescription = description;
        this.countOrder = 0;
        this.countCancelOrder= 0;
        this.countCooked = 0;
        this.countCancelCooked = 0;
        this.countDiff = 0;
        this.countTotalCancel = 0;
        this.countTotalStock = 0;
    }

    public FoodOrderHistoryCreationResponse(long moment, int productId, String productName, String productDescription, int countOrder, int countCancelOrder, int countCooked, int countCancelCooked) {
        this(moment, productId,  productName, productDescription);
        this.countOrder = countOrder;
        this.countCancelOrder = countCancelOrder;
        this.countCooked = countCooked;
        this.countCancelCooked = countCancelCooked;
        this.calculateTotals();
    }

    private synchronized static FoodOrderHistoryCreationResponse build(FoodOrderHistory foodOrderHistory) {
        return new FoodOrderHistoryCreationResponse(
                foodOrderHistory.getMoment().getTime(),
                foodOrderHistory.getProduct().getId(),
                foodOrderHistory.getProduct().getName(),
                foodOrderHistory.getProduct().getDescription(),
                foodOrderHistory.getCountOrder(),
                foodOrderHistory.getCountCancelOrder(),
                foodOrderHistory.getCountCooked(),
                foodOrderHistory.getCountCancelCooked()
        );
    }

    public synchronized static List<FoodOrderHistoryCreationResponse> buildMany(List<FoodOrderHistory> all) {
        return all.stream().map(FoodOrderHistoryCreationResponse::build).collect(Collectors.toList());
    }

    //Totals
    private synchronized void calculateTotals() {
        this.countDiff = this.getDiff();
        this.countTotalCancel = this.getTotalCancel();
        this.countTotalStock = this.getTotalStock();
    }

    private synchronized int getDiff() {
        return Math.abs(this.countOrder + this.countCancelOrder) - Math.abs(this.countCooked + this.countCancelCooked);
    }

    private synchronized int getTotalCancel() {
        return Math.abs(this.countCancelOrder) + Math.abs(this.countCancelOrder);
    }

    private synchronized int getTotalStock() {
        return Math.abs(this.countCooked + this.countCancelCooked) ;
    }

    @Override
    public synchronized String toString() {
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
    public synchronized boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FoodOrderHistoryCreationResponse that = (FoodOrderHistoryCreationResponse) o;

        if (moment != that.moment) return false;
        return productId == that.productId;

    }

    @Override
    public synchronized int hashCode() {
        int result = (int) (moment ^ (moment >>> 32));
        result = 31 * result + productId;
        return result;
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
