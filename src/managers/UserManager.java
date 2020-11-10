/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import IO.FileManager;
import entities.User;
import exceptions.IncorrectValueException;
import java.util.ArrayList;
import java.util.List;
import jktvr19_ostromogilskii_laptops.App;
import utils.Print;

/**
 *
 * @author pupil
 */
public class UserManager extends App{
    
//variables
    private static User guest;
    
//get users
    public static List<User> get(){
        return users;
    }
    
//get user
    public static User get(long id){
        for(User u : users){
            if(u.getId() == id){
                return users.get(users.indexOf(u));
            }
        }
        return null;
    }

//add admin
    public static void addAdmin() {
        try {
            users.add(new User("admin", "123123", Role.ADMIN));
        } catch (IncorrectValueException e) {
            Print.errorln("Не удалось добавить администратора");
        }
        update();
    }
    
//add user
    public static void add(User user){
        users.add(user);
        update();
    }
    
//delete user
    public static void delete(User user){
        users.remove(user);
        update();
    }
    
//delete user by index
    public static void delete(int index){
        users.remove(index);
        update();
    }
   
//get guest user
    public static long guest(){
        if(guest == null){
            guest = new User();
        }
        return guest.getId();
    }
    
//check if user exists
    public static boolean check(long id){
        for(User u : users){
            if(u.getId() == id){
                return true;
            }
        }
        return false;
    }
    
//load users ArrayList from file
    public static void load(){
        users = storageManager.load(Path.USERS.getPath());
        if(users == null){
            users = new ArrayList();
        }
    }
    
//save users ArrayList to file
    public static void save(){
        storageManager.save(users, Path.USERS.getPath());
    }
//update data   
    public static void update(){
        save();
        load();    
    }      
}
