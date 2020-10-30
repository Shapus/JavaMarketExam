/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jktvr19_ostromogilskii_laptops;

import UI.Interface;
import entities.Deal;
import entities.Product;
import entities.User;
import java.util.ArrayList;
import managers.MarketManager;
import managers.UserManager;
import security.Security;

/**
 *
 * @author pupil
 */
public class App {   
    //security class
    Security security;
    Long userId;
    
    //user roles
    public static enum Role{GUEST, USER, ADMIN};
    
    //file paths
    public static enum Path{
        DIRECTORY("data\\"), ACCOUNTS("accounts.txt"), PRODUCTS("products.txt"), DEALS("deals.txt"), USERS("users.txt");
        String path;
        Path(String str){
            path = str;
        }
        public String get(){
            return path;
        }
        public String getPath(){
            return DIRECTORY.get()+path;
        }
    };
    
    public static String DIRECTORY_PATH = "data\\";
    public static String ACCOUNTS_FILE_PATH = "accounts.txt";
    public static String PRODUCTS_FILE_PATH = "products.txt";
    public static String DEALS_FILE_PATH = "deals.txt";
    public static String USERS_FILE_PATH = "users.txt";
    
    //console colors
    public static final String RESET = "\u001B[0m";
    public static final String BLUE = "\u001B[34m";
    public static final String WHITE = "\u001B[37m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String GREEN_BACKGROUND = "\u001B[42m";
    public static final String BLUE_BACKGROUND = "\u001B[44m";
    public static final String RED_BACKGROUND = "\u001B[41m";
    public static final String WHITE_BACKGROUND = "\u001B[47m";
    
    
    //market data
    protected static ArrayList<User> users;
    protected static ArrayList<Product> products;
    protected static ArrayList<Deal> deals;
    
    public static String[] taskList;
    private boolean runApp;

    public void run(){
        MarketManager.load();
        boolean existAdmin = false;
        int i = 0;
        while(!existAdmin && i<users.size()){
            if(users.get(i).getRole() == Role.ADMIN){
                existAdmin = true;
                break;
            }
            i++;
        }
        if(!existAdmin){
            UserManager.addAdmin();
        }
        security = new Security();
        runApp = true;
        while(runApp){           
            userId = security.run();
            if(userId == null){
                runApp = false;
            }
            else if(UserManager.get(userId).getRole() == Role.GUEST){
                
            }
            else if(UserManager.get(userId).getRole() == Role.USER){
                runApp = Interface.userInterface(userId);
            }
            else if(UserManager.get(userId).getRole() == Role.ADMIN){
                runApp = Interface.adminInterface(userId);
            }
        }
        MarketManager.save();
    }
}
