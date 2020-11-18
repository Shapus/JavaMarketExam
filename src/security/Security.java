/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security;

import UI.UILogin;
import entities.User;
import UI.UIMethods;
import app.App;
import app.App.Role;
import facade.UserFacade;
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
        }catch(IndexOutOfBoundsException | NullPointerException e){
            user = UserFacade.guest();
        }
        while(user.getRole() == Role.GUEST){
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
        if(user == UserFacade.guest() || user == null){
            return user;
        }
        
        User out;
        try{
            out = (User)FacadeFactory.getUserFacade().check(user.getLogin(), user.getPassword());
        }catch(NullPointerException e){
            out = UserFacade.guest();
        }
        return out;
    }
    
    
}
