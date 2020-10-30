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
import jktvr19_ostromogilskii_laptops.App;
import utils.Print;

/**
 *
 * @author pupil
 */
public class ProductManager extends App{
   
//get products
    public static ArrayList<Product> get(){
        return products;
    }
    
//get product
    public static Product get(Product product){
        int index = products.indexOf(product);
        return products.get(index);
    }

//add product
    public static boolean add(Product product){
        products.add(product);
        return update();
    }
    
//delete product
    public static boolean delete(Product product){
        products.remove(product);
        return update();
    }
    
//delete product by index
    public static boolean delete(int index){
        products.remove(index);
        return update();
    }
    
//increase quantity
    public static boolean increaseQuantity(Product product, int quantity){
        int index = products.indexOf(product);
        int oldQuantity = products.get(index).getQuantity();
        try {
            products.get(index).setQuantity(oldQuantity + quantity);
        } catch (IncorrectValueException e) {
            Print.errorln(e.toString());
        }
        return update();
    }
//increase quantity by 1
    public static boolean increaseQuantity(Product product){
        int index = products.indexOf(product);
        int oldQuantity = products.get(index).getQuantity();
        try {
            products.get(index).setQuantity(oldQuantity + 1);
        } catch (IncorrectValueException e) {
            Print.errorln(e.toString());
        }
        return update();
    }
    
//decrease quantity
    public static boolean decreaseQuantity(Product product, int quantity){
        int index = products.indexOf(product);
        int oldQuantity = products.get(index).getQuantity();
        try {
            products.get(index).setQuantity(oldQuantity - quantity);
        } catch (IncorrectValueException e) {
            Print.errorln(e.toString());
        }
        return update();
    }  
//decrease quantity by 1
    public static boolean decreaseQuantity(Product product){
        int index = products.indexOf(product);
        int oldQuantity = products.get(index).getQuantity();
        try {
            products.get(index).setQuantity(oldQuantity - 1);
        } catch (IncorrectValueException e) {
            Print.errorln(e.toString());
        }
        return update();
    }

//load products ArrayList from file
    public static void load(){
        products = FileManager.loadFromFile(Path.PRODUCTS.getPath());
        if(products == null){
            products = new ArrayList();
        }
    }
    
//save products ArrayList to file
    public static boolean save(){
        return FileManager.saveToFile(products, Path.PRODUCTS.getPath());
    }
//update data  
    public static boolean update(){
        boolean out;
        out = save();
        if(out){
            load();    
        }
        return out;
    }    
    
}
