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
import jktvr19_ostromogilskii_laptops.App;
import utils.Print;

/**
 *
 * @author pupil
 */
public class MarketManager extends App{
    
//buy product
    public static boolean buy(User user, Product product, int quantity){
        try {
            if(product.getQuantity() <= 0){
                Print.errorln("Продукта нет в наличии");
                return false;
            }
            if(quantity > product.getQuantity()){
                Print.errorln("В наличие есть только "+product.getQuantity()+" экземпляров");
                return false;
            }
            if(quantity < 1){
                Print.errorln("Неверно введено количество");
                return false;
            }
            if(user.getMoney() < product.getPrice()*quantity){
                Print.errorln("Недостаточно средств");
                return false;
            }
            double oldMoney = user.getMoney();
            System.out.println(oldMoney - product.getPrice()*quantity);
            UserManager.get(user.getId()).setMoney(oldMoney - product.getPrice()*quantity);

            ProductManager.get(product).setQuantity(product.getQuantity()-quantity);
            DealManager.add(new Deal(user, product, quantity));
            update();
            return true;
        }catch(IncorrectValueException e){
            Print.errorln(e.toString());
            return false;
        }
    }    
    
//update all
    public static void update(){
        ProductManager.update();
        UserManager.update();
        DealManager.update();
    }
    
//save all
    public static void save(){
        ProductManager.save();
        UserManager.save();
        DealManager.save();
    }
    
//load all
    public static void load(){
        ProductManager.load();
        UserManager.load();
        DealManager.load();
    }
}
