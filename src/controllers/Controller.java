/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author pupil
 * @param <T>
 */
public abstract class Controller<T> {
//=============================== VARIABLES
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static EntityTransaction tx;

    
//=============================== ABSTRACT METHODS    
    //update values  
    protected abstract void update();
    
    
//=============================== METHODS    
    //initialization    
    public static void init(){
        emf = Persistence.createEntityManagerFactory("jktvr19market");
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }

    //begin
    private static void begin(){
        if(!Controller.getTx().isActive()){
            tx.begin();
        }
    }
    
    //insert    
    public static <T> void insert(T entity){
        begin();
        em.persist(entity);
        tx.commit();
    }
    public static void insertAll(List entitiesArray){
        begin();
        for(int i=0; i<entitiesArray.size();i++){         
            em.persist(entitiesArray.get(i));
        }
        tx.commit();
    }
    
    //get
    public static <T> T select(Class<T> className, int id){
        begin();
        T out = em.find(className, id);
        tx.commit();
        return out;
    }
    
    //delete
    public static <T> void delete(Class<T> className, int id){
        begin();
        Object object = em.find(className, id);
        em.remove(object);
        tx.commit();
    }
    
    //query
    public static <T> List<T> namedQuery(String queryName, Class<T> className){
        begin();
        List<T> out = em.createNamedQuery(queryName, className).getResultList();
        tx.commit();
        return out;
    }


//=============================== GETTERS
    public static EntityManagerFactory getEmf() {
        return emf;
    }
    public static EntityManager getEm() {
        return em;
    }
    public static EntityTransaction getTx() {
        return tx;
    }
    
}
