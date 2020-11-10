/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;

import java.util.List;


/**
 *
 * @author pupil
 */
public interface StorageManagerInterface {
    public List load(String path);
    public void save(List list, String path);
}
