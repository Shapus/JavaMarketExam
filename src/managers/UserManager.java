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
    }
    
//add user
    public static boolean add(User user){
        users.add(user);
        return update();
    }
    
//delete user
    public static boolean delete(User user){
        users.remove(user);
        return update();
    }
    
//delete user by index
    public static boolean delete(int index){
        users.remove(index);
        return update();
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
        users = FileManager.loadFromFile(Path.USERS.getPath());
        if(users == null){
            users = new ArrayList();
        }
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
