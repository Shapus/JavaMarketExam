/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author pupil
 * @param <T>
 */
public abstract class AbstractFacade<T> {
//=============================== VARIABLES
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static EntityTransaction tx;
    private static Class className;

    
//=============================== ABSTRACT METHODS    
    //update values  
    protected abstract void update();
    //get className
    protected abstract Class<T> getClassName();
    
    
//=============================== METHODS    
    //initialization    
    public static void init(){
        emf = Persistence.createEntityManagerFactory("jktvr19market");
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }

    //begin
    protected static void begin(){
        if(!AbstractFacade.getTx().isActive()){
            tx.begin();
        }
    }
    
    //insert    
    public void insert(T entity){
        begin();
        em.persist(entity);
        tx.commit();
    }
    public void insertAll(List<T> entitiesArray){
        begin();
        for(int i=0; i<entitiesArray.size();i++){         
            em.persist(entitiesArray.get(i));
        }
        tx.commit();
    }
    
    //get
    public T select(int id){
        begin();
        T out = em.find(getClassName(), id);
        tx.commit();
        return out;
    }
    public List<T> selectAll(){
        begin();
        AbstractFacade.begin();
        Query q = getEm().
        createQuery("Select t from " + getClassName().getSimpleName() + " t");
        return q.getResultList();
    }
    
    //delete
    public void delete(int id){
        begin();
        Object object = em.find(getClassName(), id);
        em.remove(object);
        tx.commit();
    }
    
    //query
    public List<T> namedQuery(String queryName){
        begin();
        List<T> out = em.createNamedQuery(queryName, getClassName()).getResultList();
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
