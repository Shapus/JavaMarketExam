/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entities.Deal;
import app.App;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author pupil
 */
public class DealController extends App{
    
    
//get deal
    public static Deal get(int id){
        return Database.select(Deal.class, id);
    }

//add deal
    public static void add(Deal deal){
        Database.insert(deal);
    }
    
//get last deal
    public static List<Deal> getLast(){
        Database.begin();
        Query q = Database.getEm().
        createQuery("SELECT d FROM Deal d ORDER BY d.date DESC");
        return q.getResultList();
    }
    
//get last *n* deals
    public static List<Deal> getLast(int amount){
        Database.begin();
        Query q = Database.getEm().
        createQuery("SELECT d FROM Deal d ORDER BY d.date DESC", Integer.class).
        setMaxResults(amount);
        return q.getResultList();
    }     
}
