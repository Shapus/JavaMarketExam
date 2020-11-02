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
import java.util.logging.Level;
import java.util.logging.Logger;
import managers.DealManager;
import managers.ProductManager;
import managers.UserManager;
import utils.Print;
import utils.Scan;

/**
 *
 * @author pupil
 */
public class Interface {
    
//variables
    private static int userId;
    private static boolean exit;
    private static int operation;
    public static String[] adminTaskList = {
                        "Выйти из программы",
                        "Выйти из учетной записи",
                        "Добавить продукт",
                        "Список продуктов",
                        "Удалить продукт",
                        "Изменить количество",
                        "Посмотреть последнюю запись",
                        "Посмотреть последние n записей",
                        "Посмотреть все записи"
                        };
    public static String[] userTaskList = {
                        "Выйти из программы",
                        "Выйти из учетной записи",
                        "Список продуктов",
                        "Купить продукт",
                        "Состояние счета"
                        };
    
//administrator interface
    public static boolean adminInterface(int userId){
        Interface.userId = userId;
        exit = false;
        while(!exit){
            Print.printList(adminTaskList);
            operation = Scan.getOperation(adminTaskList);
            if(operation != -1){
                Print.alertln(adminTaskList[operation]);
            }
            switch (operation) {
                case 0:                
                    exit = UIMethods.exit();
                    break;
                case 1:                
                    return true;
                case 2:     
                    UIMethods.addProduct();
                    break;
                case 3:   
                    Print.printList(ProductManager.get());
                    break;
                case 4:   
                    UIMethods.deleteProduct();
                    break;
                case 5:   
                    UIMethods.changeProductQuantity();
                    break;
                case 6:
                    Print.printList(DealManager.getLast());
                    break;
                case 7:
                    try {
                        int amount = Scan.getInt("Введите количество записей: ");
                        Print.printList(DealManager.get(amount));
                    } catch (IncorrectInputException ex) {
                        Print.errorln("Неверный ввод");
                    }
                    break;
                case 8:
                    Print.printList(DealManager.get());
                    break;
                default:
                    break;
            }
            System.out.print("\n\n");
        }
        return false;
    }
    
//user interface
    public static boolean userInterface(int user){
        Interface.userId = user;
        exit = false;
        while(!exit){
            Print.printList(userTaskList);
            operation = Scan.getOperation(userTaskList);
            if(operation != -1){
                Print.alertln(userTaskList[operation]);
            }
            switch (operation) {
                case 0:  
                    exit = UIMethods.exit();
                    break;
                case 1:                
                    return true;
                case 2:     
                    Print.printList(ProductManager.get());
                    break;
                case 3: 
                    UIMethods.buyProduct(userId);
                    break;
                case 4: 
                    System.out.println("Остаток на счете: "+UserManager.get(userId).getMoney());
                    break;
                default:
                    break;
            }
            System.out.print("\n\n");
        }
        return false;
    }
}
