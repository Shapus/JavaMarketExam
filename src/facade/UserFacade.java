/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entities.User;
import exceptions.IncorrectValueException;
import app.App;
import app.App.Role;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import utils.Print;

/**
 *
 * @author pupil
 */
public class UserFacade extends AbstractFacade{
    
//=============================== VARIABLES
    private static User guest;

 
//=============================== ABSTRACT METHODS     
    @Override
    protected void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected Class getClassName() {
        return User.class;
    }
    
    
//=============================== METHODS    

//get user
    public User check(String login, String password){
        Query q = AbstractFacade.getEm().
        createQuery("SELECT u FROM User u WHERE u.login=:login AND u.password=:password").setParameter("login", login).setParameter("password", password);
        User user = (User)q.getSingleResult();
        return user;
    }      
    
//add admin
    public void addAdmin() {
        try {
            Query q = AbstractFacade.getEm().
            createQuery("SELECT u FROM User u WHERE u.role=:role").setParameter("role", Role.ADMIN);
            q.getSingleResult();
            
        }catch(NoResultException e){
            try{
                User admin = new User("admin", "123123", Role.ADMIN);
                insert(admin);
            }catch (IncorrectValueException ex) {
                Print.errorln("Не удалось создать администратора");
            } 
            
        }
    }
   
//get guest user
    public static User guest(){
        if(guest == null){
            guest = new User(Role.GUEST);
        }
        return guest;
    }    
}
