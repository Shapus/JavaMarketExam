/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jktvr19_ostromogilskii_laptops;

/**
 *
 * @author pupil
 */
public class JKTVR19_Ostromogilskii_Laptops {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         String flag = "base";
        if(args.length>0){
            flag = args[0];
        }
        App app = new App();
        app.run(flag);
    }
}
