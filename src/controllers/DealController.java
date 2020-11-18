/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Deal;
import entities.Product;
import entities.User;
import exceptions.IncorrectValueException;
import java.util.List;
import javax.persistence.Query;
import security.Security;
import utils.Print;

/**
 *
 * @author pupil
 */
public class DealController extends Controller{
    
//=============================== OVERRIDDEN METHODS   
    @Override
    protected void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }  
    
    @Override
    protected Class getClassName() {
        return Deal.class;
    }
    
//=============================== METHODS   
    
//get last deal
    public List<Deal> getLast(){
        Controller.begin();
        Query q = Controller.getEm().
        createQuery("SELECT d FROM Deal d ORDER BY d.date DESC");
        return q.getResultList();
    }
    
//get last *n* deals
    public List<Deal> getLast(int amount){
        Controller.begin();
        Query q = Controller.getEm().
        createQuery("SELECT d FROM Deal d ORDER BY d.date DESC", Integer.class).
        setMaxResults(amount);
        return q.getResultList();
    }    
    
//buy product   
    public Deal buy(User user, Product product, int quantity){
        if(product.getQuantity() <= 0){
            Print.errorln("Продукта нет в наличии");
            return null;
        }
        if(quantity > product.getQuantity()){
            Print.errorln("В наличие есть только "+product.getQuantity()+" экземпляра(ов)");
            return null;
        }
        if(quantity < 1){
            Print.errorln("Неверно введено количество");
            return null;
        }
        if(user.getMoney() < product.getPrice()*quantity){
            Print.errorln("Недостаточно средств");
            return null;
        }

        //update      
        Controller.begin();
        double oldMoney = user.getMoney(); 
        try {
            user.setMoney(user.getMoney()-product.getPrice()*quantity);
        } catch (IncorrectValueException ex) {
            Print.errorln("Не удалось снять деньги со счета");
            return null;
        }
        try {
            product.setQuantity(product.getQuantity()-quantity);
        } catch (IncorrectValueException ex) {
            Print.errorln("Не удалось изменить количество товара");
            try {
                user.setMoney(oldMoney);
            } catch (IncorrectValueException ex_1) {}
            return null;
        }
        Controller.getTx().commit();
        
        Deal deal = new Deal(Security.getUser(), product, quantity);
        System.out.println(deal);
        insert(new Deal(user, product, quantity));
        return deal;
    }
}
