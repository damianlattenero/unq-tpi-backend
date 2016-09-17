package beans.model;

import beans.exception.InvalidTransitionException;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class OrderStateCooked extends OrderState{

    public OrderStateCooked(FoodOrder foodOrder) {
        super(foodOrder);
    }

    @Override
    public boolean isPending() {
        return false;
    }

    @Override
    public boolean isCooked() {
        return true;
    }

    @Override
    public boolean isCanceled() {
        return false;
    }

    @Override
    public void cook() {
        throw new InvalidTransitionException("Can't pass from Cooked to Cook.");
    }

    //TODO:Think about the posibility for cancel a Cooked FoodOrder
    @Override
    public void cancel() {
        throw new InvalidTransitionException("Can't pass from Cooked to Cancel.");
    }
}
