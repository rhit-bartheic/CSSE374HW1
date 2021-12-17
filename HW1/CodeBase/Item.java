import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Item implements Serializable{
    int stock;
    String name;
    String description;
    double price;
    private BufferedImage picture;

    public Item(String name, String description, double price, String url, int stock) {
        this.name = name;
        this.stock = stock;
        this.description = description;
        this.price = price;
        URL picUrl = null;
        try {
            picUrl = new URL(url);
        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        }
        try {
           this.picture = ImageIO.read(picUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
