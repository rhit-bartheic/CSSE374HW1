import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Total implements Serializable{
    private Tax tax = new Tax();
    ArrayList<Discount> appliedCodes = new ArrayList<>();
    
    public Total() {
    }

    public double sumOfPrices(String customerEmail) {
        double sumOfPrices = 0; 
        
        for (Item key : Main.searchForCart(customerEmail).items.keySet()) {
            double price = (double)  key.price;
            sumOfPrices = sumOfPrices + price;
        }

        return  sumOfPrices;
    }

    double useCoupons(double total) {
        if(appliedCodes.size() == 0) return total;
        float highestCoupon = appliedCodes.get(0).percentOff;
        for(int i = 0; i < appliedCodes.size(); i++) {
            if(highestCoupon < appliedCodes.get(i).percentOff) {
                highestCoupon = appliedCodes.get(i).percentOff;
            }
        }
        return total * (1- highestCoupon);
    }

    boolean checkCoupons(String code, String email) {
        if(Main.discountCodes.get(code) != null) {
            Discount temp = Main.discountCodes.get(code);
            ArrayList<Item> check = temp.requiredItems;
            for(Item key : Main.searchForCart(email).items.keySet()) {
                check.remove(key);
            }
            if(check.size() == 0) {
                appliedCodes.add(Main.discountCodes.get(code));
                return true;
            }
        }
        return false;
    }

    float calcTax(State state) {
        return tax.getTax(state);
    }

}
