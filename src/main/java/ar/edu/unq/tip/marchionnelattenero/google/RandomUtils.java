package ar.edu.unq.tip.marchionnelattenero.google;

import java.util.List;
import java.util.Random;

public class RandomUtils<T> {

    public T getRandom(List<T> list) {
        Random random = new Random();
        int index = random.nextInt(list.size());
        System.out.println("\nIndex :" + index);
        return list.get(index);

    }
}
