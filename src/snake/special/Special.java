package snake.special;

import java.security.SecureRandom;
import java.util.Random;

public class Special {
    private static final SecureRandom secureRandom = new SecureRandom();
    public static Random getRandom() {
        return secureRandom;
    }
}
