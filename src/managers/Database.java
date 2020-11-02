/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import java.io.Serializable;
import java.util.ArrayList;
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
public class Database<T extends Serializable>{
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static EntityTransaction tx;
    
    public static void init(){
        emf = Persistence.createEntityManagerFactory("JKTVR19_Ostromogilskii_Laptops");
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }
    public static <T> void insert(T entity){
        tx.begin();
        em.persist(entity);
        tx.commit();
        em.close();
    }
    public static void insertAll(ArrayList entityArray){
        tx.begin();
        for(int i=0; i<entityArray.size();i++){         
            em.persist(entityArray.get(i));
        }
        tx.commit();
        em.close();
    }
    public static <T> Object select(Class<T> className, int id){
        return em.find(className, id);
    }
    public static <T> void update(Class<T> className, int id){
        Object object = em.find(className, id);
        em.getTransaction().begin();
        //update values code...
        em.getTransaction().commit();
    }
    public static <T> void delete(Class<T> className, int id){
        Object object = em.find(className, id);
        em.getTransaction().begin();
        em.remove(object);
        em.getTransaction().commit();
    }
    public static <T> List<T> query(String queryName, Class<T> className){
        return em.createNamedQuery(queryName, className).getResultList();
    }
}
