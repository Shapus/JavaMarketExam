/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import UI.UILogin;
import entities.User;
import UI.UIMethods;
import managers.UserManager;
import utils.Print;
import utils.Scan;

/**
 *
 * @author pupil
 */
public class Security {
    private long userId;
    private String[] taskList = {
                    "Выйти из программы",
                    "Войти",
                    "Зарегистрироваться",
                    };
    private int operation;
    public Long run(){
        userId = UserManager.guest();
        while(true){
            Print.printList(taskList);
            operation = Scan.getOperation(taskList);
            if(operation != -1){
                Print.alertln(taskList[operation]);
            }
            switch (operation) {
                case 0:
                    if(UIMethods.exit()){
                        return null;
                    } 
                    break;
                case 1:
                    userId = UILogin.login();
                    return userId;
                case 2:
                    UILogin.registration();
                    break;
            }
        }
    }
    
}
