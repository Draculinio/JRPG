package elementosRoleros;

import java.util.Random;

public class Dices {
    public static int dice(int min, int max){
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
