/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab12.Data;

/**
 *
 * @author Michel Eissa
 */
public class Product {
    private String description;
    private float price;
    private String id;
    private int count;
    float total;

    public Product(String description, float price, String id) {
        this.description = description;
        this.price = price;
        this.id = id;
    }
    
    
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    public void decreaseCount(){
        if(count > 0)
            this.count --;
    }
    public void increaseCount(){
        this.count++;
    }
}
