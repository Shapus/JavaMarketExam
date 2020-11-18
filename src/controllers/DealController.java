/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Deal;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author pupil
 */
public class DealController extends Controller{

//=============================== OVERRIDDEN METHODS   
    @Override
    protected void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }    
    
//=============================== METHODS   
//get deal
    public static Deal get(int id){
        return Controller.select(Deal.class, id);
    }

//add deal
    public static void add(Deal deal){
        Controller.insert(deal);
    }
    
//get last deal
    public static List<Deal> getLast(){
        Controller.begin();
        Query q = Controller.getEm().
        createQuery("SELECT d FROM Deal d ORDER BY d.date DESC");
        return q.getResultList();
    }
    
//get last *n* deals
    public static List<Deal> getLast(int amount){
        Controller.begin();
        Query q = Controller.getEm().
        createQuery("SELECT d FROM Deal d ORDER BY d.date DESC", Integer.class).
        setMaxResults(amount);
        return q.getResultList();
    }     
}
