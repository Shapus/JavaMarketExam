/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import IO.FileManager;
import UI.UI;
import controllers.DealFacade;
import controllers.ProductFacade;
import entities.Deal;
import entities.Product;
import entities.User;
import java.util.ArrayList;
import controllers.UserFacade;
import factory.FacadeFactory;
import security.Security;
import utils.Print;

/**
 *
 * @author pupil
 */
public class App {   
    //security class
    Security security;
    
    //user roles
    public static enum Role{GUEST, USER, ADMIN};
    
    //file paths
    public static enum Path{
        DIRECTORY("data\\"), ACCOUNTS("accounts.txt"), PRODUCTS("products.txt"), DEALS("deals.txt"), USERS("users.txt"), USER_COOCIE("user_coocie.txt");
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
    public static ArrayList<User> user_cookie;
    
    public static String[] taskList;
    private boolean runApp;

    public void run(){
        user_cookie = FileManager.load(Path.USER_COOCIE.getPath());
        security = new Security();
        FacadeFactory.getUserFacade().addAdmin();
        runApp = true;
        while(runApp){
            security.run();
            if(Security.getUser() == null){
                runApp = false;
            }
            else if(Security.getUser().getRole() == Role.GUEST){
                
            }
            else if(Security.getUser().getRole() == Role.USER){
                user_cookie = new ArrayList<User>();
                user_cookie.add(Security.getUser());
                FileManager.save(user_cookie, Path.USER_COOCIE.getPath());
                System.out.print("Вы вошли как ");
                Print.alertln(Security.getUser().getLogin());
                runApp = UI.userInterface();
            }
            else if(Security.getUser().getRole() == Role.ADMIN){
                user_cookie = new ArrayList<User>();
                user_cookie.add(Security.getUser());
                FileManager.save(user_cookie, Path.USER_COOCIE.getPath());
                System.out.print("Вы вошли как ");
                Print.alertln("Администратор");
                runApp = UI.adminInterface();
            }
        }
    }
}
