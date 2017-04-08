/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productcipher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author thejan
 */
public class ProductCipher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.print("Enter text file name (end with .txt): ");
        
        try {
            
            String file_name = br.readLine();
            
            FileHandler fh = new FileHandler();
            
//            String text = fh.fileReader(file_name);
            String text = fh.fileReader("/home/thejan/NetBeansProjects/ProductCipher/src/productcipher/"+file_name);

            if(text!=null){
                
                System.out.print("Enter Operation (1.Encrypt / 2.Decrypt): ");
                int opt = Integer.parseInt(br.readLine());
                if (!(opt==1 || opt==2)){
                    System.out.println("Invalid Operation");
                    System.exit(0);
                }

                System.out.print("Enter Key 1: ");
                int key1 = Integer.parseInt(br.readLine());

                System.out.print("Enter Key 2: ");
                int key2 = Integer.parseInt(br.readLine());

                System.out.print("Enter Key 3: ");
                int key3 = Integer.parseInt(br.readLine());
                if(!(key3<=text.length()/2)){
                    System.out.println("Invalid Key 3.");
                    System.exit(0);
                }

                System.out.println(text);

                CipherLogic cl = new CipherLogic();

                if(opt==1){

                    String enc_text = cl.encrypt(text, key1, key2, key3);
                    System.out.println("Encrypted Text: "+enc_text);

                    fh.fileWriter(enc_text,"encrypted_"+file_name);

                }else if(opt==2){

                    String dec_text = cl.decrypt(text, key1, key2, key3);
                    System.out.println("Decrypted Text: "+dec_text);

                    fh.fileWriter(dec_text, "decrypted_"+file_name);
                }

        //            String dec_text = cl.decrypt(enc_text, key1, key2, key3);

        //            System.out.println(dec_text);
            }   
         
        } catch (IOException ex) {
            System.out.println("IO Exception");
        } catch (NumberFormatException ex){
            System.out.println("Invalid Input");
        }
        
        
    }
    
}
