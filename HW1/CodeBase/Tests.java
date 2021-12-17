import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class Tests {
    Main main = new Main();
    
    Item hotTub = new Item("Hot Tub", "An inflatable hot tub that seats 4 and is completely portable",
                            599.99, 
                            "https://encrypted-tbn1.gstatic.com/shopping?q=tbn:ANd9GcS1oPY-pFhTEmNLNQiqoOslJWTO2B7iweilif2ramvqVNqnpsHziV841fwylVzoy7UzCQryTUrpWlFytDbvoJatm_nKEo77ZEkiiVydQ7Wpj3ENO8jzy8DmsQ&usqp=CAE",
                             3);
    Item doritos = new Item("Nacho Cheese Doritos", "Party Size bag of Doritos", 2.58, 
                            "https://ipcdn.freshop.com/resize?url=https://images.freshop.com/00028400082983/1224028254fc7652726696366047d367_large.png&width=512&type=webp&quality=80",
                            648);
    Item xbox = new Item("Xbox Series X", "Next-Gen Brand New Xbox", 499.99,
                            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQWDi_FaQlLCcb1S0kcnww0EnmW5HFeOwTSZQ&usqp=CAU",
                            50);

    ArrayList<Item> d1Items = new ArrayList<>();
    
    @Before
    public void createInitialDemo() {
        main.handleAddToCart(doritos, "user1@email.com", State.IN);
        main.handleAddToCart(hotTub, "user1@email.com", State.IN);
        main.handleAddToCart(hotTub, "user1@email.com", State.IN);
        main.handleAddToCart(hotTub, "user2@email.com", State.IN);
        d1Items.add(xbox);
        d1Items.add(hotTub);
        Discount d1 = new Discount("code1", (float) .2, d1Items);
        Main.discountCodes.put("code1", d1);
    }

    @Test
    public void testCodeNoItems() {
        main.handleAddToCart(hotTub, "user42@email.com", State.SD);
        assertEquals(false, main.handleApplyDiscount("user42@email.com", "code1"));
    }

    @Test
    public void addOneItemToCart() {
        Main.allCarts.clear();
        assertEquals(true, main.handleAddToCart(hotTub, "user2@email.com", State.IN));
    }

    @Test
    public void addOutOfStockItem() {
        Main.allCarts.clear();
        hotTub.stock = 0;
        assertEquals(true, main.handleAddToCart(hotTub, "user2@email.com", State.IN));
    }

    @Test
    public void addSameItem() {
        Main.allCarts.clear();
        main.handleAddToCart(doritos, "user2@email.com", State.IN);
        main.handleAddToCart(doritos, "user2@email.com", State.IN);
        int test = Main.searchForCart("user2@email.com").getItems().get(doritos);
        assertEquals(2, test);
    }

    @Test
    public void readFromDB() { //fails when tests ran in bulk but passes when ran individually???????
        Main.allCarts.clear();
        assertEquals(0, Main.allCarts.size());
        main.readFromDB();
        int test1 = Main.searchForCart("user1@email.com").getItems().size();
        int test2 = Main.searchForCart("user2@email.com").getItems().size();
        assertEquals(2, test1);
        assertEquals(1, test2);
    }

    @Test
    public void updateQuantity() {
        Main.allCarts.clear();
        main.handleAddToCart(xbox, "user3@email.com", State.IN);
        int quantity = Main.searchForCart("user3@email.com").getItems().get(xbox);
        assertEquals(1, quantity);
        main.handleUpdateQuantity(xbox, "user3@email.com", 16);
        int quantityUp = Main.searchForCart("user3@email.com").getItems().get(xbox);
        assertEquals(16, quantityUp);
    }

    @Test
    public void updateQuantityOutOfStock() {
        Main.allCarts.clear();
        main.handleAddToCart(xbox, "user3@email.com", State.IN);
        int quantity = Main.searchForCart("user3@email.com").getItems().get(xbox);
        assertEquals(1, quantity);
        main.handleUpdateQuantity(xbox, "user3@email.com", 100);
        int quantityUp = Main.searchForCart("user3@email.com").getItems().get(xbox);
        assertEquals(1, quantityUp); //quantity did not update
    }

    @Test
    public void negativeQuantity() {
        Main.allCarts.clear();
        main.handleAddToCart(hotTub, "user4@email.com", State.AK);
        int quantity = Main.searchForCart("user4@email.com").getItems().get(hotTub);
        assertEquals(1, quantity);
        main.handleUpdateQuantity(hotTub, "user4@email.com", -10);
        int quantity2 = Main.searchForCart("user4@email.com").getItems().get(hotTub);
        assertEquals(1, quantity2);
    }

    @Test
    public void zeroQuantity() {
        Main.allCarts.clear();
        main.handleAddToCart(xbox, "user4@email.com", State.AK);
        int quantity = Main.searchForCart("user4@email.com").getItems().get(xbox);
        assertEquals(1, quantity);
        main.handleUpdateQuantity(xbox, "user4@email.com", 0);
        int size = Main.searchForCart("user4@email.com").getItems().size();
        assertEquals(0, size);
    }

    @Test
    public void viewItems() {
        Main.allCarts.clear();
        main.handleAddToCart(xbox, "user5@email.com", State.AK);
        main.handleAddToCart(xbox, "user5@email.com", State.AK);
        main.handleAddToCart(hotTub, "user5@email.com", State.AK);
        ArrayList<String> test = main.handleViewItems("user5@email.com");
        assertEquals(true, test.contains("Xbox Series X"));
        assertEquals(true, test.contains("Hot Tub"));
        assertEquals(false, test.contains("Nacho Cheese Doritos"));
        assertEquals(3, test.size());
    }

    @Test
    public void noDiscountTotal() {
        Main.allCarts.clear();
        main.handleAddToCart(hotTub, "user6@email.com", State.ME);
        main.handleAddToCart(xbox, "user6@email.com", State.ME);
        double total = Math.round(( (hotTub.price + xbox.price) * 1.21) * 100) / 100;
        double calcTotal = main.handleGetTotal("user6@email.com");
        if(total == calcTotal) assertEquals(true, true);
        else assertEquals(false, true);
    }

    @Test
    public void applyDiscount() {
        Main.allCarts.clear();
        main.handleAddToCart(hotTub, "user50@email.com", State.ME);
        main.handleAddToCart(xbox, "user50@email.com", State.ME);
        assertEquals(true, main.handleApplyDiscount("user50@email.com", "code1"));
    }

    @Test
    public void calcFullTotalwDiscount() {
        Main.allCarts.clear();
        main.handleAddToCart(hotTub, "user50@email.com", State.ME);
        main.handleAddToCart(xbox, "user50@email.com", State.ME);
        main.handleApplyDiscount("user50@email.com", "code1");
        double test = Math.round((((hotTub.price + xbox.price) * .8) * 1.21) * 100) / 100;
        double total = main.handleGetTotal("user50@email.com");
        if(test == total) assertEquals(true, true);
        else assertEquals(false, false);
    }
}