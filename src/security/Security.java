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
    private int userId;
    private String[] taskList = {
                    "Выйти из программы",
                    "Войти",
                    "Зарегистрироваться",
                    };
    private int operation;
    public Integer run(){
        userId = -1;
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
                    if(UserManager.get(userId) != null){
                        return userId;
                    }else{
                        Print.errorln("Неверно введен логин и/или пароль");
                    }
                    break;
                case 2:
                    UILogin.registration();
                    break;
            }
        }
    }
    
}
