/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import entities.User;
import exceptions.IncorrectValueException;
import java.util.logging.Level;
import java.util.logging.Logger;
import app.App.Role;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import managers.Database;
import managers.UserManager;
import utils.Print;
import utils.Scan;

/**
 *
 * @author pupil
 */
public class UILogin {
//log in
    public static User login(){
        String login;
        String password;
        User user = UserManager.guest();
            
        Print.alert("Имя пользователя:", " ");
        login = Scan.getString();
        Print.alert("Пароль:", " ");
        password = Scan.getString();
        try{
        user = UserManager.get(login, password);
        }catch(NoResultException e){
            Print.errorln("Неверно введен логин и/или пароль");
            return UserManager.guest();
        }
        return user;
    }
    
//registeration
    public static void registration(){
        try {
            String login;
            String password;
            String confirmPassword;
            User user = new User();;
            user.setRole(Role.USER);
            user.setMoney(10000);
            
            //get user name
            Print.alert("Введите имя пользователя:", " ");
            login = Scan.getString();

            user.setLogin(login);
            
            //get password
            Print.alert("Введите пароль:", " ");
            password = Scan.getString();
            user.setPassword(password);
            
            //confirm password
            Print.alert("Подтвердите пароль:", " ");
            confirmPassword = Scan.getString();
            if(!password.equals(confirmPassword)){
                Print.errorln("Пароли не совпадают");
                return;
            }
            
            //success registration
            Database.insert(user);
            System.out.println("Пользователь зарегистрирован");
        }catch (IncorrectValueException e) {
            Print.errorln(e.toString());
        }
    }
}
