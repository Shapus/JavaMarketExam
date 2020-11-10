/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entities.Deal;
import java.util.ArrayList;
import java.util.List;
import jktvr19_ostromogilskii_laptops.App;

/**
 *
 * @author pupil
 */
public class DealManager extends App{
    
//get deals
    public static List<Deal> get(){
        return deals;
    }
    
//get deal
    public static Deal get(Deal deal){
        int index = deals.indexOf(deal);
        return deals.get(index);
    }

//add deal
    public static void add(Deal deal){
        deals.add(deal);
        update();
    }
    
//get last deal
    public static ArrayList<Deal> getLast(){
        int size = deals.size();
        if(size > 0){
            ArrayList<Deal> out = new ArrayList();
            out.add(deals.get(size-1));
            return out;
        }
        return new ArrayList();
    }
    
//get last *n* deals
    public static List<Deal> get(int amount){
        List<Deal> out = new ArrayList();
        int size = deals.size();
        if(size > 0){
            if(size > amount){
                for(int i=size-amount;i<size;i++){
                    out.add(deals.get(i));
                }    
            }
            else {
                for(int i=0;i<size;i++){
                    out.add(deals.get(i));
                }
            }
            return out;     
        }
        return new ArrayList();
    }    
    
//load users ArrayList from file
    public static void load(){
        deals = storageManager.load(App.Path.DEALS.getPath());
        if(deals == null){
            deals = new ArrayList();
        }
    }
    
//save users ArrayList to file
    public static void save(){
        storageManager.save(deals, App.Path.DEALS.getPath());
    }
    
//update data   
    public static void update(){
        save();
        load();    
    }   
    
}
