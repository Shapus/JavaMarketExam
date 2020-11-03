/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import entities.Product;
import entities.User;
import exceptions.IncorrectInputException;
import exceptions.IncorrectValueException;
import java.util.ArrayList;
import managers.MarketManager;
import managers.ProductManager;
import managers.UserManager;
import utils.Print;
import utils.Scan;

/**
 *
 * @author pupil
 */
public class UIMethods {

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
            ProductManager.add(product);
            if(ProductManager.get(product.getId()) != null){
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
        ArrayList<Product> products = ProductManager.getAll();
        try {
            if(products.size() > 0){
                Print.printList(ProductManager.getAll());
                int index = Scan.getIndex(products, 1, "Выберите продукт для удаления: ");
                Product product = products.get(index-1);
                ProductManager.delete(product.getId());
                if(ProductManager.get(product.getId()) == null){
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
    
//change quantity
    public static void changeProductQuantity(){
        ArrayList<Product> products = ProductManager.getAll();
        try {
            if(products.size() > 0){
                    Print.printList(products);
                    int product_index = Scan.getIndex(products, 1, "Выберите продукт: ");
                    Product product = products.get(product_index-1);
                    int quantity = Scan.getInt("Введите количество: ");
                    ProductManager.increaseQuantity(product, quantity);       
                    System.out.println("Текущее количество: "+product.getQuantity());
            }else{
                Print.emptyMessage();
            }
        }catch (IncorrectInputException e) {
            Print.errorln(e.toString());
        }
    }

//buy product by user
    public static void buyProduct(User user){
        ArrayList<Product> products = ProductManager.getAll();
        try {
            if(products.size() > 0){
                Print.printList(products);
                int index = Scan.getIndex(products, 1, "Выберите продукт: ");
                Product product = products.get(index-1);
                int quantity = Scan.getInt("Введите количество: ");
                if(MarketManager.buy(UserManager.get(user.getId()), product, quantity) != null){
                    System.out.println("Вы успешно купили продукт: "+product.getData()+" в количестве "+quantity);
                    System.out.println("Остаток на счете: "+UserManager.get(user.getId()).getMoney());
                }
            }else{
                Print.emptyMessage();
            }
        }catch (IncorrectInputException e) {
            Print.errorln(e.toString());
        }
    }
}
