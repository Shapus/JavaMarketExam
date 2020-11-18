/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import UI.UILogin;
import entities.User;
import UI.UIMethods;
import controllers.UserController;
import utils.Print;
import utils.Scan;
import static app.App.user_cookie;

/**
 *
 * @author pupil
 */
public class Security {
    
//=============================== METHODS    
    private static User user = UserController.guest();
    private String[] taskList = {
                    "Выйти из программы",
                    "Войти",
                    "Зарегистрироваться",
                    };
    private int operation;


//=============================== METHODS    
//run security    
    public void run(){
        try{
            user = user_cookie.get(0);
            return;
        }catch(IndexOutOfBoundsException | NullPointerException e){
            user = UserController.guest();
        }
        
        while(true){
            Print.printList(taskList);
            operation = Scan.getOperation(taskList);
            if(operation != -1){
                Print.alertln(taskList[operation]);
            }
            switch (operation) {
                case 0:
                    if(UIMethods.exit()){
                        user = null;
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
//get user    
    public static User getUser(){
        if(user == UserController.guest()){
            return user;
        }
        if(user == null){
            return null;
        }
        return UserController.get(user.getId());
    }
    
    
}
