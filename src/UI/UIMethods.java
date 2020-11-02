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
            boolean successAddProduct = ProductManager.add(product);
            if(successAddProduct){
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
        try {
            if(ProductManager.get().size() > 0){
                Print.printList(ProductManager.get());
                int index = Scan.getIndex(ProductManager.get(), 1, "Выберите продукт для удаления: ");
                Product product = (Product)ProductManager.get().get(index-1);
                if(ProductManager.delete(product)){
                    System.out.println(product.toString() + " удален");
                }
                else{
                    Print.errorln("Не удалось удалить продукт", " "+product.toString());
                }
            }
        }catch (IncorrectInputException e) {
            Print.errorln(e.toString());
        }
    }
    
//change quantity
    public static void changeProductQuantity(){
        try {
            if(ProductManager.get().size() > 0){
                    Print.printList(ProductManager.get());
                    int product_index = Scan.getIndex(ProductManager.get(), 1, "Выберите продукт: ");
                    Product product = ProductManager.get().get(product_index-1);
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
    public static void buyProduct(int userId){
        try {
            if(ProductManager.get().size() > 0){
                Print.printList(ProductManager.get());
                int index = Scan.getIndex(ProductManager.get(), 1, "Выберите продукт: ");
                Product product = ProductManager.get().get(index-1);
                int quantity = Scan.getInt("Введите количество: ");
                if(MarketManager.buy(UserManager.get(userId), product, quantity)){
                    System.out.println("Вы успешно купили продукт: "+product.getData()+" в количестве "+quantity);
                    System.out.println("Остаток на счете: "+UserManager.get(userId).getMoney());
                }
            }else{
                Print.emptyMessage();
            }
        }catch (IncorrectInputException e) {
            Print.errorln(e.toString());
        }
    }
}
