/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author pupil
 */
public class ConnectSingleton {
    
//=============================== VARIABLES
    private static ConnectSingleton instance;
    private static EntityManager em;
 
    
//=============================== CONSTRUCTORS    
    private ConnectSingleton(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jktvr19market");
        em = emf.createEntityManager();
    }

//=============================== METHODS
    //get ConnectSingleton
    public static ConnectSingleton getInstance(){
        if(instance == null){
            instance = new ConnectSingleton();
        }
        return instance;
    }
    
    //begin
    public static void begin(){
        if(!ConnectSingleton.getTx().isActive()){
            em.getTransaction().begin();
        }
    }
    
//=============================== GETTERS
    public static EntityManager getEm() {
        return em;
    }
    public static EntityTransaction getTx() {
        return em.getTransaction();
    }
}
