/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import exceptions.IncorrectInputException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author pupil
 */
public class Scan{
    
    //scanner
    public static Scanner scanner = new Scanner(System.in);
    
    //get integer
    public static int getInt(String str) throws IncorrectInputException{
        try{
            System.out.print(str);
            String iStr = scanner.nextLine();
            int i = Integer.parseInt(iStr);
            return i;
        }catch(NumberFormatException e){
            throw new IncorrectInputException("Значение введено неверно");
        }
    }
    public static int getInt() throws IncorrectInputException{
        try{
            String iStr = scanner.nextLine();
            int i = Integer.parseInt(iStr);
            return i;
        }catch(NumberFormatException e){
            throw new IncorrectInputException("Значение введено неверно");
        }
    }
    
    
    //get string
    public static String getString(String str){
        System.out.print(str);
        String outStr = scanner.nextLine();
        return outStr;
    }
    public static String getString(){
        String outStr = scanner.nextLine();
        return outStr;
    }
    
    //get double
    public static double getDouble(String str) throws IncorrectInputException{
        try{
            System.out.print(str);
            String iStr = scanner.nextLine();
            double d = Double.parseDouble(iStr);
            return d;
        }catch(NumberFormatException e){
            throw new IncorrectInputException("Значение введено неверно");
        }
    }
    public static double getDouble() throws IncorrectInputException{
        try{
            String iStr = scanner.nextLine();
            double d = Double.parseDouble(iStr);
            return d;
        }catch(NumberFormatException e){
            throw new IncorrectInputException("Значение введено неверно");
        }
    }
    
    //get List/String[] index. If index above list size or less zero, throw NumberFormatException
    //Should rewrite with generics
    public static<E> int getIndex(List<E> list, int shift, String str) throws IncorrectInputException{
        int index = getInt(str);
        if(index > list.size()-1+shift || index < shift) throw new IncorrectInputException("Неверный индекс");
        return index;
    }
    public static int getIndex(String[] list, int shift, String str) throws IncorrectInputException{
        int index = getInt(str);
        if(index > list.length-1+shift || index < shift) throw new IncorrectInputException("Неверный индекс");
        return index;
    }
    
    //choose operation from task list
    public static int getOperation(String[] list){
        int operation;
        try{
            operation = Scan.getIndex(list, 0, "Введите номер операции: ");
            return operation;
        }catch(IncorrectInputException e){
            Print.errorln(e.toString());
            return -1;
        }
        
    }
}
