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
import app.App;
import javax.persistence.Query;
import utils.Print;

/**
 *
 * @author pupil
 */
public class ProductManager extends App{
   
    
//get product
    public static Product get(int id){
        return Database.select(Product.class, id);
    }

//get all
    public static ArrayList<Product> getAll(){
        Database.getTx().begin();
        Query q = Database.getEm().
        createQuery("SELECT u FROM User u");
        return (ArrayList<Product>)q.getResultList();
    }    
    
//add product
    public static void add(Product product){
        Database.insert(product);
    }
    
//delete product by id
    public static void delete(int id){
        Database.delete(Product.class, id);
    }
    
//increase quantity
    public static void increaseQuantity(Product product, int quantity){
        
    }
//increase quantity by 1
    public static void increaseQuantity(Product product){
        
    }
    
//decrease quantity
    public static void decreaseQuantity(Product product, int quantity){
       
    }  
//decrease quantity by 1
    public static void decreaseQuantity(Product product){
        
    }
}
