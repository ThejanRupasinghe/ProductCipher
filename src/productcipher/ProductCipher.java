/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productcipher;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

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
        
        System.out.print("Enter text file name (ending with .txt): ");
        
        try {
            
            String file_name = br.readLine();
            
            FileHandler fh = new FileHandler();
            
            String text = fh.fileReader(file_name);
//            String text = fh.fileReader("/home/thejan/NetBeansProjects/ProductCipher/src/productcipher/"+file_name);

            pickFile();

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

                System.out.print("Enter Key 3 (<= length of the text/2): ");
                int key3 = Integer.parseInt(br.readLine());
                
                /* only takes keys greater than or equal half a length of the text
                   because of the permutation matrix construction */
                if(!(key3<=text.length()/2)){
                    System.out.println("Invalid Key 3.");
                    System.exit(0);
                }

                CipherLogic cl = new CipherLogic();

                if(opt==1){

                    String enc_text = cl.encrypt(text, key1, key2, key3);
                    
                    System.out.println("\nPlain Text:\n"+text+"\n");
                    System.out.println("Encrypted Text:\n"+enc_text+"\n");

                    fh.fileWriter(enc_text,"encrypted_"+file_name);

                }else if(opt==2){

                    String dec_text = cl.decrypt(text, key1, key2, key3);
                    
                    System.out.println("\nEncrypted Text:\n"+text+"\n");
                    System.out.println("Decrypted Text:\n"+dec_text+"\n");

                    fh.fileWriter(dec_text, "decrypted_"+file_name);
                }
                
            }   
         
        } catch (IOException ex) {
            System.out.println("IO Exception");
        } catch (NumberFormatException ex){
            System.out.println("Invalid Input");
        }
          
    }
    
    public static File pickFile(){
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Select the Text File to be Encrypted");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt", "text");
        chooser.setFileFilter(filter);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
          return chooser.getSelectedFile();
        } else {
          return null;
        }    
    }
    
}
