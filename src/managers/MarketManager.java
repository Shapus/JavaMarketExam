/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entities.Deal;
import entities.Product;
import entities.User;
import exceptions.IncorrectValueException;
import app.App;
import utils.Print;

/**
 *
 * @author pupil
 */
public class MarketManager extends App{
    
//buy product
    public static Deal buy(User user, Product product, int quantity){
        try {
            if(product.getQuantity() <= 0){
                Print.errorln("Продукта нет в наличии");
                return null;
            }
            if(quantity > product.getQuantity()){
                Print.errorln("В наличие есть только "+product.getQuantity()+" экземпляров");
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
            double oldMoney = user.getMoney();
//update user.money
//update product.quantity
            user.setMoney(user.getMoney());
            Deal deal = new Deal(user, product, quantity);
            DealManager.add(new Deal(user, product, quantity));
            return deal;
        }catch(IncorrectValueException e){
            Print.errorln(e.toString());
            return null;
        }
    }    
}
