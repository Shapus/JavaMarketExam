/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import UI.UILogin;
import entities.User;
import UI.UIMethods;
import controllers.UserFacade;
import utils.Print;
import utils.Scan;
import static app.App.user_cookie;
import factory.FacadeFactory;

/**
 *
 * @author pupil
 */
public class Security {
    
//=============================== METHODS    
    private static User user = UserFacade.guest();
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
            user = UserFacade.guest();
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
        if(user == UserFacade.guest()){
            return user;
        }
        if(user == null){
            return null;
        }
        return (User)FacadeFactory.getUserFacade().select(user.getId());
    }
    
    
}
