/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.User;
import exceptions.IncorrectValueException;
import app.App;
import app.App.Role;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import utils.Print;

/**
 *
 * @author pupil
 */
public class UserController{
    
//=============================== VARIABLES
    private static User guest;

    
//=============================== METHODS    
//get user
    public static User get(int id){
        return Controller.select(User.class, id);
    }

//get user
    public static User get(String login, String password){
        Query q = Controller.getEm().
        createQuery("SELECT u FROM User u WHERE u.login=:login AND u.password=:password").setParameter("login", login).setParameter("password", password);
        User user = (User)q.getSingleResult();
        return user;
    }    
    
    
//add admin
    public static void addAdmin() {
        try {
            Query q = Controller.getEm().
            createQuery("SELECT u FROM User u WHERE u.role=:role").setParameter("role", Role.ADMIN);
            q.getSingleResult();
            
        }catch(NoResultException e){
            try{
                User admin = new User("admin", "123123", Role.ADMIN);
                Controller.insert(admin);
            }catch (IncorrectValueException ex) {
                Print.errorln("Не удалось создать администратора");
            } 
            
        }
    }
    
//add user
    public static void add(User user){
        Controller.insert(user);
    }
    
//delete user
    public static void delete(int id){
        Controller.delete(User.class, id);
    }
   
//get guest user
    public static User guest(){
        if(guest == null){
            guest = new User(Role.GUEST);
        }
        return guest;
    }    
}
