import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    static HashMap<String, Cart> allCarts = new HashMap<String, Cart>();
    static HashMap<String, Discount> discountCodes = new HashMap<>();
    public static void main(String[] args) {
    }

    static Cart searchForCart(String email) {
        return allCarts.get(email);
    }

    ArrayList<String> handleViewItems(String email) {
        return(allCarts.get(email).viewItems());

    }

    double handleGetTotal(String email) {
        return searchForCart(email).calculateTotal();
    }

    boolean handleApplyDiscount(String email, String code) {
        return searchForCart(email).applyDiscount(code);
    }

    boolean handleAddToCart(Item item, String email, State state) {
        //this simulates when we would normally search through a database to easily find customers carts in our API
        Cart customerCart = allCarts.get(email);
        if(customerCart != null) {
            customerCart.addItem(item);
            return updateDB(customerCart, email);
        } else {
            customerCart = new Cart(email, state);
            allCarts.put(email, customerCart);
            customerCart.addItem(item);
            return updateDB(customerCart, email);
        }
    }

    boolean handleUpdateQuantity(Item item, String email, int updatedQuantity) {
        Cart customerCart = allCarts.get(email);
        updateDB(customerCart, email);
        return customerCart.updateQuantity(item, updatedQuantity);
    }

    public boolean updateDB(Cart cart, String customerEmail) {
        allCarts.put(customerEmail, cart);
        // code for writing and reading from a ser file from https://beginnersbook.com/2013/12/how-to-serialize-hashmap-in-java/
        try {
            FileOutputStream fos =
            new FileOutputStream("cartDB.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(allCarts);
            oos.close();
            fos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean readFromDB() {
        HashMap<String, Cart> map = null;
        try {
            FileInputStream fis = new FileInputStream("cartDB.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            map = (HashMap<String, Cart>) ois.readObject();
            ois.close();
            fis.close();
            saveToAll(map);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        
    }

    private void saveToAll(HashMap<String, Cart> map) {
        allCarts.clear();
        for (Map.Entry<String, Cart> key : map.entrySet()) {
            String email = (String) key.getKey();
            Cart cart = (Cart) key.getValue();
            allCarts.put(email, cart);
        }
    }


}
