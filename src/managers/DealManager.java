/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import IO.FileManager;
import entities.Deal;
import java.util.ArrayList;
import app.App;

/**
 *
 * @author pupil
 */
public class DealManager extends App{
    
    
//get deal
    public static Deal get(int id){
        return Database.select(Deal.class, id);
    }

//add deal
    public static void add(Deal deal){
        Database.insert(deal);
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
    public static ArrayList<Deal> getLast(int amount){
        ArrayList<Deal> out = new ArrayList();
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
}
