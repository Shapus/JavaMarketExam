/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;

import entities.Deal;
import entities.Product;
import entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import jktvr19_ostromogilskii_laptops.App;
import jktvr19_ostromogilskii_laptops.App.Path;
import managers.Database;

/**
 *
 * @author pupil
 */
public class DatabaseManager implements StorageManagerInterface{
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jktvr19javamarket");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();
    
    @Override
    public void save(List list, String path){
        if(!tx.isActive()){
            tx.begin();
        }
        for(Object o : list){
            em.persist(o);
        }
        tx.commit();
    }

    @Override
    public List load(String path){
        if(!tx.isActive()){
            tx.begin();
        }
        if(path.equals(Path.DEALS.getPath())){
            Query q = em.
            createQuery("SELECT d FROM Deal d ORDER BY d.date DESC");
            return (List<Deal>)q.getResultList();
        }
        else if(path.equals(Path.USERS.getPath())){
            Query q = em.
            createQuery("SELECT u FROM User u");
            return (List<User>)q.getResultList();
        }
        else if(path.equals(Path.PRODUCTS.getPath())){
            Query q = em.
            createQuery("SELECT p FROM Product p");
            return (List<Product>)q.getResultList();
        }
        return new ArrayList();
    }
}
