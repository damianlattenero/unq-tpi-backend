package beans.aspects.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class OrderStatePending extends OrderState {

    public OrderStatePending(FoodOrder foodOrder) {
        super(foodOrder);
    }

    @Override
    public boolean isPending() {
        return true;
    }

    @Override
    public boolean isCooked() {
        return false;
    }

    @Override
    public boolean isCanceled() {
        return false;
    }

    @Override
    public void cook() {
        this.foodOrder.setState(new OrderStateCooked(this.foodOrder));
    }

    @Override
    public void cancel() {
        this.foodOrder.setState(new OrderStateCanceled(this.foodOrder));
    }
}
