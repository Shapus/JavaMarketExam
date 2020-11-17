/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entities.Product;
import app.App;
import exceptions.IncorrectValueException;
import java.util.List;
import javax.persistence.Query;
import utils.Print;

/**
 *
 * @author pupil
 */
public class ProductController extends App{
   
    
//get product
    public static Product get(int id){
        return Database.select(Product.class, id);
    }

//get all
    public static List<Product> getAll(){
        Database.begin();
        Query q = Database.getEm().
        createQuery("SELECT p FROM Product p WHERE p.deleted = false");
        return q.getResultList();
    }
//get all
    public static List<Product> getAll(boolean deleted){
        Database.begin();
        Query q = Database.getEm().
        createQuery("SELECT p FROM Product p WHERE p.deleted = :deleted").
                setParameter("deleted", deleted);
        return q.getResultList();
    }    
    
//add product
    public static void add(Product product){
        Database.insert(product);
    }
    
//delete product
    public static void delete(Product product){
        Database.begin();
        product.setDeleted(true);
        Database.getTx().commit();
    }
//restore product
    public static void restore(Product product){
        Database.begin();
        product.setDeleted(false);
        Database.getTx().commit();
    }
    
//increase quantity
    public static void increaseQuantity(Product product, int quantity){
        Database.begin();
        try {
            product.setQuantity(product.getQuantity()+quantity);
        } catch (IncorrectValueException ex) {
            Print.errorln("Не удалось изменить количество");
        }
        Database.getTx().commit();
       
    }
}
