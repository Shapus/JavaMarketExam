/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import app.App;
import exceptions.IncorrectInputException;
import java.util.ArrayList;
import managers.DealManager;
import managers.ProductManager;
import security.Security;
import utils.Print;
import utils.Scan;

/**
 *
 * @author pupil
 */
public class UI {
    
//variables
    private static boolean exit;
    private static int operation;
    public static String[] adminTaskList = {
                        "Выйти из программы",
                        "Выйти из учетной записи",
                        "Добавить продукт",
                        "Список продуктов",
                        "Удалить продукт",
                        "Восстановить продукт",
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
    public static boolean adminInterface(){
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
                    App.user_coocie = new ArrayList();
                    return true;
                case 2:     
                    UIMethods.addProduct();
                    break;
                case 3:   
                    Print.printList(ProductManager.getAll());
                    break;
                case 4:   
                    UIMethods.deleteProduct();
                    break;
                case 5:   
                    UIMethods.restoreProduct();
                    break;
                case 6:   
                    UIMethods.changeProductQuantity();
                    break;
                case 7:
                    Print.printList(DealManager.getLast(1));
                    break;
                case 8:
                    try {
                        int amount = Scan.getInt("Введите количество записей: ");
                        Print.printList(DealManager.getLast(amount));
                    } catch (IncorrectInputException ex) {
                        Print.errorln("Неверный ввод");
                    }
                    break;
                case 9:
                    Print.printList(DealManager.getLast());
                    break;
                default:
                    break;
            }
            System.out.print("\n\n");
        }
        return false;
    }
    
//user interface
    public static boolean userInterface(){
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
                    App.user_coocie = new ArrayList();
                    return true;
                case 2:     
                    Print.printList(ProductManager.getAll());
                    break;
                case 3: 
                    UIMethods.buyProduct();
                    break;
                case 4: 
                    System.out.println("Остаток на счете: "+Security.getUser().getMoney());
                    break;
                default:
                    break;
            }
            System.out.print("\n\n");
        }
        return false;
    }
}
