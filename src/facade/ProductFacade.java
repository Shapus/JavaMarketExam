/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

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
public class ProductFacade extends AbstractFacade{
   
    
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
        AbstractFacade.begin();
        product.setDeleted(false);
        AbstractFacade.getTx().commit();
    }
//delete product
    public void setDeleted(Product product){
        AbstractFacade.begin();
        product.setDeleted(true);
        AbstractFacade.getTx().commit();
    }
    
//delete product
    public List<Product> getByDeleted(Boolean isDeleted){
        AbstractFacade.begin();
        Query q = AbstractFacade.getEm().
        createQuery("SELECT p FROM Product p WHERE p.deleted = :isDeleted").
                setParameter("isDeleted", isDeleted);
        return q.getResultList();
    }    
    
//increase quantity
    public void increaseQuantity(Product product, int quantity){
        AbstractFacade.begin();
        try {
            product.setQuantity(product.getQuantity()+quantity);
        } catch (IncorrectValueException ex) {
            Print.errorln("Не удалось изменить количество");
        }
        AbstractFacade.getTx().commit();    
    }
}
