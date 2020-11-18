/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import facade.DealFacade;
import facade.ProductFacade;
import facade.UserFacade;

/**
 *
 * @author pupil
 */
public class FacadeFactory {
    
//=============================== VARIABLES
    
//=============================== METHODS   
//get deal facade    
    public static DealFacade getDealFacade(){
        return new DealFacade();
    }
    
//get product facade
    public static ProductFacade getProductFacade(){
        return new ProductFacade();
    }
    
//get user facade
    public static UserFacade getUserFacade(){
        return new UserFacade();
    }
    
}
