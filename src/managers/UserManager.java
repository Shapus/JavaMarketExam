/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entities.User;
import exceptions.IncorrectValueException;
import app.App;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import utils.Print;

/**
 *
 * @author pupil
 */
public class UserManager extends App{
    
//variables
    private static User guest;

    
//get user
    public static User get(int id){
        return Database.select(User.class, id);
    }

//get user
    public static User get(String login, String password){
        Query q = Database.getEm().
        createQuery("SELECT u FROM User u WHERE u.login=:login AND u.password=:password").setParameter("login", login).setParameter("password", password);
        User user = (User)q.getSingleResult();
        return user;
    }    
    
    
//add admin
    public static void addAdmin() {
        try {
            Query q = Database.getEm().
            createQuery("SELECT u FROM User u WHERE u.role=:role").setParameter("role", Role.ADMIN);
            q.getSingleResult();
            
        }catch(NoResultException e){
            try{
                User admin = new User("admin", "123123", Role.ADMIN);
                Database.insert(admin);
            }catch (IncorrectValueException ex) {
                Print.errorln("Не удалось создать администратора");
            } 
            
        }
    }
    
//add user
    public static void add(User user){
        Database.insert(user);
    }
    
//delete user
    public static void delete(int id){
        Database.delete(User.class, id);
    }
   
//get guest user
    public static User guest(){
        if(guest == null){
            guest = new User(Role.GUEST);
        }
        return guest;
    }    
}
