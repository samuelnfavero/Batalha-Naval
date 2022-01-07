package model.classes.utils;

import java.util.Random;

public class RandomNumberGenerator {

    public static int randomIntGenerator(int maxNumber){
        Random generator = new Random();
        int randomInt = generator.nextInt(maxNumber) + 1;
        return randomInt;
    }
}
