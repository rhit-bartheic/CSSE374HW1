import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Cart implements Serializable {
    private String customerEmail;
    private State customerState;
    HashMap<Item, Integer> items;
    Total total = new Total();

    public Cart(String email, State state) {
        this.customerEmail = email;
        this.customerState = state;
        this.items = new HashMap<Item, Integer>();
    }

    public HashMap<Item, Integer> getItems() {return items;}

    boolean addItem(Item item) {
        if(item.stock == 0) return false;

        if(items.get(item) == null) {
            items.put(item, 1);
            return true; // once again, this is just simulating what would automatically happen with a database
        } else {
            int quantity = items.get(item);
            items.put(item, quantity + 1);
            return true;
        }
    }

    boolean updateQuantity(Item item, int updatedQuantity) {
        if (updatedQuantity > item.stock || updatedQuantity < 0) return false;
        if(updatedQuantity == 0) { items.remove(item); return false;}
        items.put(item, updatedQuantity);
        return true;
    }

    ArrayList<String> viewItems() {
        ArrayList<String> itemNames = new ArrayList<>();
        for (Entry<Item, Integer> key : items.entrySet()) {
            String name = (String) key.getKey().name;
            int amount = key.getValue();
            for(int i = 0; i < amount; i++) {
                itemNames.add(name);
            }
        }
        return itemNames;
    }

    boolean applyDiscount(String code) {
        if(this.total.checkCoupons(code, customerEmail)) {
            return true;
        }
        return false;
    }

    double calculateTotal(){
        double beforeDisc = this.total.sumOfPrices(customerEmail);
        double beforeTax = this.total.useCoupons(beforeDisc);
        return Math.round((beforeTax * this.total.calcTax(customerState)) * 100) / 100;
    }
}
