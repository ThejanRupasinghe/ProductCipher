/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productcipher;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author thejan
 */
public class FileHandler {
    
    public String fileReader(String file_name){
        
        String text;
        
        try (BufferedReader br = new BufferedReader(new FileReader(file_name))) {
            
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            text = sb.toString();
            
            br.close();
            
        } catch(FileNotFoundException e) {
            System.out.println("File not found");
            return null;
        } catch(IOException e){
            System.out.println("IO Exception");
            return null;
        }
        
        return text;
    }
    
    public boolean fileWriter(String text, String fileName){
        
        String file_name = "/home/thejan/NetBeansProjects/ProductCipher/src/productcipher/"+fileName;

//        String file_name = fileName;
        
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file_name))){
            
            bw.write(text);
            
            return true;
            
        } catch(FileNotFoundException e) {
            System.out.println("File not found");
            return false;
        } catch(IOException e){
            System.out.println("IO Exception");
            return false;
        }
        
    }
    
}
