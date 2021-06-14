package samueltm.boredom.miscellaneous;

public class Formatting {

    public static String valueOf(double number) {
        String numberString;
        if (number == (int) number) {
            numberString = "" + (int) number;
        } else {
            numberString = "" + number;
        }
        return numberString;
    }
}
