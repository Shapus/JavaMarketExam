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
import app.App;
import security.Security;
import utils.Print;

/**
 *
 * @author pupil
 */
public class MarketController{
    
//=============================== METHODS    
//buy product
    public static Deal buy(User user, Product product, int quantity){
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
            } catch (IncorrectValueException ex1) {}
            return null;
        }
        Controller.getTx().commit();
        
        Deal deal = new Deal(Security.getUser(), product, quantity);
        System.out.println(deal);
        DealController.add(new Deal(user, product, quantity));
        return deal;
    }    
}