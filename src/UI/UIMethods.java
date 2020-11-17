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
import managers.MarketController;
import managers.ProductController;
import managers.UserController;
import security.Security;
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
            ProductController.add(product);
            if(ProductController.get(product.getId()) != null){
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
        List<Product> products = ProductController.getAll();
        try {
            if(products.size() > 0){
                Print.printList(products);
                int index = Scan.getIndex(products, 1, "Выберите продукт для удаления: ");
                Product product = products.get(index-1);
                ProductController.delete(product);
                if(ProductController.get(product.getId()).isDeleted()){
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
        List<Product> products = ProductController.getAll(true);
        try {
            if(products.size() > 0){
                Print.printList(products);
                int index = Scan.getIndex(products, 1, "Выберите продукт для восстановления: ");
                Product product = products.get(index-1);
                ProductController.restore(product);
                if(!ProductController.get(product.getId()).isDeleted()){
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
        List<Product> products = ProductController.getAll();
        try {
            if(products.size() > 0){
                    Print.printList(products);
                    int product_index = Scan.getIndex(products, 1, "Выберите продукт: ");
                    Product product = products.get(product_index-1);
                    int quantity = Scan.getInt("Введите количество: ");
                    ProductController.increaseQuantity(product, quantity);       
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
        List<Product> products = ProductController.getAll();
        try {
            if(products.size() > 0){
                Print.printList(products);
                int index = Scan.getIndex(products, 1, "Выберите продукт: ");
                Product product = products.get(index-1);
                int quantity = Scan.getInt("Введите количество: ");
                if(MarketController.buy(UserController.get(Security.getUser().getId()), product, quantity) != null){
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
