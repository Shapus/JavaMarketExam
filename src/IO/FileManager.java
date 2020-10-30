/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;

import java.io.File;
import utils.Print;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author pupil
 */
public class FileManager {
    
    //save arrayList to file
    public static boolean saveToFile(ArrayList list, String path){
        boolean successSave = false;            //return statement
        FileOutputStream fileOut = null;        //file stream
        ObjectOutputStream objectOut;    //object stream
        
        //create directory if it doesn't exist
        File dir = new File("data");
        if (!dir.exists()) {
            boolean result = false;
            try{
                dir.mkdir();
                result = true;
            } 
            catch(SecurityException se){
                Print.errorln("Не удалось создать папку \"data\"");
            }        
        }
        
        //create file if it doesn't exist
        File f = new File(path);
        if(!f.exists()){
            try {
                f.createNewFile();
            } catch (IOException ex) {
                Print.errorln("Не удалось создать файл", " "+path);
            }
        }
        
        //try to save data to file
        try {
            fileOut = new FileOutputStream(path);
            objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(list);
            objectOut.flush();
            successSave = true;
        } catch (FileNotFoundException ex) {
            Print.errorln("Файл не найден");
        } catch (IOException ex) {
            Print.errorln("Ошибка ввода/вывода при сохранении:", ex.toString());
        } finally {
            try {
                fileOut.close();
            } catch (IOException ex) {
                Print.errorln("Ошибка закрытия файла:", ex.toString());
            } 
        }
        return successSave;
    }
    
    //load arrayList from file
    public static ArrayList loadFromFile(String path){
        ArrayList out = new ArrayList();    //return statement
        FileInputStream fileIn;      //load fram path
        ObjectInputStream objectIn;  //input file stream
        
        //try to load file
        try {
            fileIn = new FileInputStream(path);
            objectIn = new ObjectInputStream(fileIn);
            out = (ArrayList) objectIn.readObject();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
            Print.errorln("Ошибка ввода/вывода при чтении:", ex.toString());
        } catch (ClassNotFoundException ex) {
            Print.errorln("Ошибка чтения файла:", ex.toString());
        }
        return out;
    }
}
