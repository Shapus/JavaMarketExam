/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import factory.ConnectSingleton;
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
    private static Class className;

    
//=============================== ABSTRACT METHODS    
    //update values  
    protected abstract void update();
    //get className
    protected abstract Class<T> getClassName();
    
    
//=============================== METHODS    
    
    //insert    
    public void insert(T entity){
        ConnectSingleton.begin();
        ConnectSingleton.getEm().persist(entity);
        ConnectSingleton.getTx().commit();
    }
    public void insertAll(List<T> entitiesArray){
        ConnectSingleton.begin();
        for(int i=0; i<entitiesArray.size();i++){         
            ConnectSingleton.getEm().persist(entitiesArray.get(i));
        }
        ConnectSingleton.getTx().commit();
    }
    
    //get
    public T select(int id){
        ConnectSingleton.begin();
        T out = ConnectSingleton.getEm().find(getClassName(), id);
        ConnectSingleton.getTx().commit();
        return out;
    }
    public List<T> selectAll(){
        ConnectSingleton.begin();

        Query q = ConnectSingleton.getEm().
        createQuery("Select t from " + getClassName().getSimpleName() + " t");
        return q.getResultList();
    }
    
    //delete
    public void delete(int id){
        ConnectSingleton.begin();
        Object object = ConnectSingleton.getEm().find(getClassName(), id);
        ConnectSingleton.getEm().remove(object);
        ConnectSingleton.getTx().commit();
    }
    
    //query
    public List<T> namedQuery(String queryName){
        ConnectSingleton.begin();
        List<T> out = ConnectSingleton.getEm().createNamedQuery(queryName, getClassName()).getResultList();
        ConnectSingleton.getTx().commit();
        return out;
    }   
}
