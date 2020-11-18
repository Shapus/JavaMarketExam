/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

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
public class ProductController{
   
//=============================== METHODS    
//get product
    public static Product get(int id){
        return Controller.select(Product.class, id);
    }

//get all
    public static List<Product> getAll(){
        Controller.begin();
        Query q = Controller.getEm().
        createQuery("SELECT p FROM Product p WHERE p.deleted = false");
        return q.getResultList();
    }
//get all
    public static List<Product> getAll(boolean deleted){
        Controller.begin();
        Query q = Controller.getEm().
        createQuery("SELECT p FROM Product p WHERE p.deleted = :deleted").
                setParameter("deleted", deleted);
        return q.getResultList();
    }    
    
//add product
    public static void add(Product product){
        Controller.insert(product);
    }
    
//delete product
    public static void delete(Product product){
        Controller.begin();
        product.setDeleted(true);
        Controller.getTx().commit();
    }
//restore product
    public static void restore(Product product){
        Controller.begin();
        product.setDeleted(false);
        Controller.getTx().commit();
    }
    
//increase quantity
    public static void increaseQuantity(Product product, int quantity){
        Controller.begin();
        try {
            product.setQuantity(product.getQuantity()+quantity);
        } catch (IncorrectValueException ex) {
            Print.errorln("Не удалось изменить количество");
        }
        Controller.getTx().commit();
       
    }
}
