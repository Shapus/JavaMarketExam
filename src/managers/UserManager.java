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
    public static ArrayList<User> get(){
        return users;
    }
    
//get user
    public static User get(int id){
        return (User)Database.select(User.class, id);
    }

//add admin
    public static void addAdmin() {
        try {
            users.add(new User("admin", "123123", Role.ADMIN));
        } catch (IncorrectValueException e) {
            Print.errorln("Не удалось добавить администратора");
        }
    }
    
//add user
    public static void add(User user){
        Database.insert(user);
    }
    
//delete user
    public static void delete(User user){
        Database.delete(User.class, user.getId());
    }
    
//delete user by index
    public static void delete(int id){
        Database.delete(User.class, id);
    }
   
//get guest user
    public static int guest(){
        if(guest == null){
            guest = new User();
        }
        return guest.getId();
    }
    
    
//load users ArrayList from file
    public static List<User> load(){
        return Database.query("loadUsers", User.class);
    }
    
//save users ArrayList to file
    public static boolean save(){
        return FileManager.saveToFile(users, Path.USERS.getPath());
    }
//update data   
    public static boolean update(){
        boolean out;
        out = save();
        if(out){
            load();    
        }
        else{
            Print.errorln("Не удалось обновить данные");
        }
        return out;
    }      
}
