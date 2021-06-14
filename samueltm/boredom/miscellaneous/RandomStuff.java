package samueltm.boredom.miscellaneous;

import java.util.concurrent.ThreadLocalRandom;

public class RandomStuff {

    public static int[] generateRandomIntegersBetween(int desiredAmount, int lowerLimit, int upperLimit) {
        if (desiredAmount > 0) {
            final int[] result = new int[desiredAmount];
            int addedNumbers = 0;
            final int startAmountAvailableNumbers = upperLimit - lowerLimit + 1;
            if (lowerLimit < upperLimit && startAmountAvailableNumbers >= desiredAmount) {
                for (int i = lowerLimit; i <= upperLimit; i++) {
                    if (addedNumbers == desiredAmount) {
                        break;
                    } else {
                        double probability = 1.0;
                        final int availableNumbers = upperLimit - i + 1;
                        final int remainingNeeded = desiredAmount - addedNumbers;
                        if (availableNumbers > remainingNeeded) {
                            probability = (double) remainingNeeded / availableNumbers;
                        }
                        if (ThreadLocalRandom.current().nextDouble() <= probability) {
                            result[addedNumbers] = i;
                            addedNumbers++;
                        }
                    }
                }
            }
            return result;
        } else {
            return new int[0];
        }
    }


}
