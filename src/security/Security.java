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
    private static User user = UserManager.guest();
    private String[] taskList = {
                    "Выйти из программы",
                    "Войти",
                    "Зарегистрироваться",
                    };
    private int operation;
    
    public void run(){
        user = UserManager.guest();
        while(true){
            Print.printList(taskList);
            operation = Scan.getOperation(taskList);
            if(operation != -1){
                Print.alertln(taskList[operation]);
            }
            switch (operation) {
                case 0:
                    if(UIMethods.exit()){
                        return;
                    } 
                    break;
                case 1:
                    user = UILogin.login();
                    return;
                case 2:
                    UILogin.registration();
                    break;
            }
        }
    }
    
    public static User getUser(){
        if(user == UserManager.guest()){
            return user;
        }
        return UserManager.get(user.getId());
    }
    
    
}
