package androidApi;

import java.util.Random;

/**
 * Created by cbadea on 6/8/2018.
 */
public class randomGenerator {

   public randomGenerator(){}

    public int random() {
        int leftLimit = 1;
        int rightLimit = 10;
        return leftLimit + (int) (new Random().nextFloat() * (rightLimit - leftLimit));
    }
}
