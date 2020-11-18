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
public class ProductController extends Controller{
   
    
//=============================== ABSTRACT METHODS 
    @Override
    protected void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Class getClassName() {
        return Product.class;
    }    
    
    
//=============================== METHODS    
    
    
//restore product
    public void restore(Product product){
        Controller.begin();
        product.setDeleted(false);
        Controller.getTx().commit();
    }
//delete product
    public void setDeleted(Product product){
        Controller.begin();
        product.setDeleted(true);
        Controller.getTx().commit();
    }
    
//delete product
    public List<Product> getByDeleted(Boolean isDeleted){
        Controller.begin();
        Query q = Controller.getEm().
        createQuery("SELECT p FROM Product p WHERE p.deleted = :isDeleted").
                setParameter("isDeleted", isDeleted);
        return q.getResultList();
    }    
    
//increase quantity
    public void increaseQuantity(Product product, int quantity){
        Controller.begin();
        try {
            product.setQuantity(product.getQuantity()+quantity);
        } catch (IncorrectValueException ex) {
            Print.errorln("Не удалось изменить количество");
        }
        Controller.getTx().commit();    
    }
}
