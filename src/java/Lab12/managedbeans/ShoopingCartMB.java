/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab12.managedbeans;

import Lab12.Data.Product;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Michel Eissa
 */
@ManagedBean(name = "Cart")
@SessionScoped
public class ShoopingCartMB {

    private List<Product> products;
    private List<Product> purchasedProducts;
    float totalPrice;
    

    /**
     * Creates a new instance of ShoopingCartMB
     */
    public ShoopingCartMB() {
        this.products = new ArrayList<Product>();
        this.products.add(new Product("The winner takes it all", 12.95f, "A32581"));
        this.products.add(new Product("Yellow submarine", 11.35f, "A33278"));
        this.purchasedProducts = new ArrayList<Product>();
    }

    public float getTotalPrice() {
        return totalPrice;
    }
    
    public List<Product> getProducts() {
        return products;
    }

    

    public List<Product> getPurchasedProducts() {
        return purchasedProducts;
    }

    public void setPurchasedProduct(List<Product> purchasedProducts) {
        this.purchasedProducts = purchasedProducts;
    }

    public String addToCart() {
        String id = getParam("id");
        Iterator<Product> ita = products.iterator();
        while (ita.hasNext()) {
            Product prod = ita.next();
            if (prod.getId().equals(id)) {
                    if(!purchasedProducts.contains(prod)){
                    prod.increaseCount();
                    prod.setTotal(prod.getPrice() * prod.getCount());
                    purchasedProducts.add(prod);
                    totalPrice += prod.getPrice();
                }
                else{
                    //totalPrice -= prod.getPrice();
                    purchasedProducts.remove(prod);
                    prod.increaseCount();
                    prod.setTotal(prod.getPrice() * prod.getCount());
                    purchasedProducts.add(prod);  
                    totalPrice += prod.getPrice();
                }
            }
        }
        
        return "All";
    }
    //synchronized 
    public String doRemoveFromCart(){
        String purchasedId = getParam("purchasedId");
        Iterator<Product> ita = purchasedProducts.iterator();
        Product prod = null;
        while (ita.hasNext()) {
            prod = ita.next();
            if (prod.getId().equals(purchasedId)) {
                if(purchasedProducts.contains(prod)){
                    //purchasedProducts.remove(prod);
                    totalPrice -= prod.getPrice();
                }
            }
        }
        if(prod != null){
            prod.decreaseCount();
            prod.setTotal(prod.getPrice() * prod.getCount());
            purchasedProducts.remove(prod);
            if(prod.getCount() > 0)
                purchasedProducts.add(prod);
        }
        return null;
    }
    private String getParam(String param){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get(param);
    }
}
