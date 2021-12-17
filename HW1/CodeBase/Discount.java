import java.util.ArrayList;

public class Discount {
    String code;
    float percentOff;
    ArrayList<Item> requiredItems;

    public Discount(String code, float percentageOff, ArrayList<Item> validItems) {
        this.code = code;
        this.percentOff = percentageOff;
        this.requiredItems = validItems;
    }
}
