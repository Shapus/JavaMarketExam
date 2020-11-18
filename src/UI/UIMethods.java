/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import entities.Product;
import exceptions.IncorrectInputException;
import exceptions.IncorrectValueException;
import java.util.List;
import entities.Deal;
import entities.User;
import facade.FacadeFactory;
import security.Security;
import utils.Print;
import utils.Scan;

/**
 *
 * @author pupil
 */
public class UIMethods {

//=============================== METHODS
//exit
    public static boolean exit(){
        boolean exit = false;
        String exitStr = Scan.getString("Выйти из программы? y/n ").toLowerCase();
        if(exitStr.equals("y")){
            exit = true;
        }
        return exit;
    }
    
    
//add product
    public static void addProduct(){
        try{
            Product product = new Product();
            String name = Scan.getString("Введите название продукта: ");
            product.setName(name);
            double price = Scan.getDouble("Введите стоимоть продукта: ");
            product.setPrice(price);
            int quantity = Scan.getInt("Введите количество: ");
            product.setQuantity(quantity);
            FacadeFactory.getProductFacade().insert(product);
            if(FacadeFactory.getProductFacade().select(product.getId()) != null){
                System.out.println(product.toString() + " добавлен");
            }
            else{
                Print.errorln("Не удалось добавить продукт");
            }
        }catch(IncorrectValueException e){
            Print.errorln(e.toString());
        }catch(IncorrectInputException e){
            Print.errorln(e.toString());
        }
    }
    
//delete product
    public static void deleteProduct(){
        List<Product> products = FacadeFactory.getProductFacade().getByDeleted(false);
        try {
            if(products.size() > 0){
                Print.printList(products);
                int index = Scan.getIndex(products, 1, "Выберите продукт для удаления: ");
                Product product = products.get(index-1);
                FacadeFactory.getProductFacade().setDeleted(product);
                Product p = (Product) FacadeFactory.getProductFacade().select(product.getId());
                if(p.isDeleted()){
                    System.out.println(product.toString() + " удален");
                }
                else{
                    Print.errorln("Не удалось удалить продукт", " "+product.toString());
                }
            }else{
                Print.emptyMessage();
            }
        }catch (IncorrectInputException e) {
            Print.errorln(e.toString());
        }
    }
    
//restore product
    public static void restoreProduct(){
        List<Product> products = FacadeFactory.getProductFacade().getByDeleted(true);
        try {
            if(products.size() > 0){
                Print.printList(products);
                int index = Scan.getIndex(products, 1, "Выберите продукт для восстановления: ");
                Product product = products.get(index-1);
                FacadeFactory.getProductFacade().restore(product);
                Product p = (Product) FacadeFactory.getProductFacade().select(product.getId());
                if(!p.isDeleted()){
                    System.out.println(product.toString() + " восстановлен");
                }
                else{
                    Print.errorln("Не удалось восстановить продукт", " "+product.toString());
                }
            }else{
                Print.emptyMessage();
            }
        }catch (IncorrectInputException e) {
            Print.errorln(e.toString());
        }
    }
    
//change quantity
    public static void changeProductQuantity(){
        List<Product> products = FacadeFactory.getProductFacade().getByDeleted(false);
        try {
            if(products.size() > 0){
                Print.printList(products);
                int product_index = Scan.getIndex(products, 1, "Выберите продукт: ");
                Product product = products.get(product_index-1);
                int quantity = Scan.getInt("Введите количество: ");
                FacadeFactory.getProductFacade().increaseQuantity(product, quantity);       
                System.out.println("Текущее количество: "+product.getQuantity());
            }else{
                Print.emptyMessage();
            }
        }catch (IncorrectInputException e) {
            Print.errorln(e.toString());
        }
    }

//buy product by user
    public static void buyProduct(){
        List<Product> products = FacadeFactory.getProductFacade().getByDeleted(false);
        try {
            if(products.size() > 0){
                Print.printList(products);
                int index = Scan.getIndex(products, 1, "Выберите продукт: ");
                Product product = products.get(index-1);
                int quantity = Scan.getInt("Введите количество: ");
                User u = (User) FacadeFactory.getUserFacade().select(Security.getUser().getId());
                Deal deal = FacadeFactory.getDealFacade().buy(u, product, quantity);
                if(deal != null){
                    System.out.println("Вы успешно купили продукт: "+product.getData()+" в количестве "+quantity);
                    System.out.println("Остаток на счете: "+Security.getUser().getMoney());
                }
            }else{
                Print.emptyMessage();
            }
        }catch (IncorrectInputException e) {
            Print.errorln(e.toString());
        }
    }
}
