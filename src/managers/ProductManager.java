/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import IO.FileManager;
import entities.Product;
import entities.User;
import exceptions.IncorrectValueException;
import java.util.ArrayList;
import java.util.List;
import jktvr19_ostromogilskii_laptops.App;
import utils.Print;

/**
 *
 * @author pupil
 */
public class ProductManager extends App{
   
//get products
    public static List<Product> get(){
        return products;
    }
    
//get product
    public static Product get(Product product){
        int index = products.indexOf(product);
        return products.get(index);
    }

//add product
    public static void add(Product product){
        products.add(product);
        update();
    }
    
//delete product
    public static void delete(Product product){
        products.remove(product);
        update();
    }
    
//delete product by index
    public static void delete(int index){
        products.remove(index);
        update();
    }
    
//increase quantity
    public static void increaseQuantity(Product product, int quantity){
        int index = products.indexOf(product);
        int oldQuantity = products.get(index).getQuantity();
        try {
            products.get(index).setQuantity(oldQuantity + quantity);
        } catch (IncorrectValueException e) {
            Print.errorln(e.toString());
        }
        update();
    }
//increase quantity by 1
    public static void increaseQuantity(Product product){
        int index = products.indexOf(product);
        int oldQuantity = products.get(index).getQuantity();
        try {
            products.get(index).setQuantity(oldQuantity + 1);
        } catch (IncorrectValueException e) {
            Print.errorln(e.toString());
        }
        update();
    }
    
//decrease quantity
    public static void decreaseQuantity(Product product, int quantity){
        int index = products.indexOf(product);
        int oldQuantity = products.get(index).getQuantity();
        try {
            products.get(index).setQuantity(oldQuantity - quantity);
        } catch (IncorrectValueException e) {
            Print.errorln(e.toString());
        }
        update();
    }  
//decrease quantity by 1
    public static void decreaseQuantity(Product product){
        int index = products.indexOf(product);
        int oldQuantity = products.get(index).getQuantity();
        try {
            products.get(index).setQuantity(oldQuantity - 1);
        } catch (IncorrectValueException e) {
            Print.errorln(e.toString());
        }
        update();
    }

//load products ArrayList from file
    public static void load(){
        products = storageManager.load(Path.PRODUCTS.getPath());
        if(products == null){
            products = new ArrayList();
        }
    }
    
//save products ArrayList to file
    public static void save(){
        storageManager.save(products, Path.PRODUCTS.getPath());
    }
//update data  
    public static void update(){
        save();
        load();    
    }    
    
}
