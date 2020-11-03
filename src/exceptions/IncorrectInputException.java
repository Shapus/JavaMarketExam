/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author pupil
 */
public class IncorrectInputException extends Exception{
    String error;
    public IncorrectInputException(String error) {
        this.error = error;
    }
    
    @Override
    public String toString(){
        return(this.error);
    }  
}
