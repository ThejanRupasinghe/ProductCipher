/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productcipher;

/**
 *
 * @author thejan
 */
public class CipherLogic {
    
    //to encrypt plain text
    public String encrypt(String plain_text, int key1, int key2, int key3){
       
        //plain text for substitution encryption
        String enc_subs = substitution_enc(plain_text, key1, key2);
        
        //substitution encrypted text for permutation encryption
        String enc_subs_per = permutation_enc(enc_subs, key3);
        
        //returning the encrypted text after both encryptions
        return enc_subs_per;
    }
    
    //to decrypt cipher text
    public String decrypt(String cipher_text, int key1, int key2, int key3){
        
        //cipher text for permutation decryption
        String dec_per = permutation_dec(cipher_text, key3);
        
        //permutation decrypted text for substitution decryption
        String dec_per_subs = substitution_dec(dec_per, key1, key2);
        
        //returning the plain text after both decryptions
        return dec_per_subs;
    }
    
    
    /***** ENCRYPTION METHODS *****/
    
    //does substitution encryption
    private String substitution_enc(String text, int key1, int key2){
        
        StringBuilder enc_text_builder = new StringBuilder();
        
        for(int i=0 ; i<text.length() ; i++) {
            char letter = text.charAt(i);
            if(i%2==0){
                enc_text_builder.append((char) (letter+key1));
            }else{
                enc_text_builder.append((char) (letter+key2));
            }
        }
        
        return enc_text_builder.toString();
    }
    
    //does permutation encryption
    private String permutation_enc(String text, int key){
        
        int rows = text.length()/key;
        if(!(text.length() % key == 0)){
            rows++;
        }
        int columns = key;       
        
        char matrix[][]=new char[rows][columns];
        
        int a=0;
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                if(a<text.length()){
                    matrix[i][j]=text.charAt(a);
                }
                a++;
            }
        }
        
        StringBuilder enc_text_builder = new StringBuilder();
        for(int j=0;j<columns;j++){
            for(int i=0;i<rows;i++){
                    enc_text_builder.append(matrix[i][j]);
            }
        }        
        
        return enc_text_builder.toString();
    }
    
    
    /***** DECRYPTION METHODS *****/
    
    //does substitution decryption
    private String substitution_dec(String text, int key1, int key2){
        
        StringBuilder dec_text_builder = new StringBuilder();
        
        for(int i=0 ; i<text.length() ; i++) {
            char letter = text.charAt(i);
            if(i%2==0){
                dec_text_builder.append((char) (letter-key1));
            }else{
                dec_text_builder.append((char) (letter-key2));
            }
        }
        
        return dec_text_builder.toString();
        
    }
    
    //does permutation decryption
    private String permutation_dec(String text, int key){
        
        int rows = text.length()/key;
        if(!(text.length() % key == 0)){
            rows++;
        }
        int columns = key;       
        
        char matrix[][]=new char[rows][columns];
        
        int a=0;
        for(int j=0;j<columns;j++){
            for(int i=0;i<rows;i++){
                if(a<text.length()){
                    matrix[i][j]=text.charAt(a);
                }
                a++;
            }
        }
               
        StringBuilder enc_text_builder = new StringBuilder();
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                if(matrix[i][j]=='\u0000'){
                    continue;
                }else{
                    enc_text_builder.append(matrix[i][j]);
                }
            }
        }
        
        return enc_text_builder.toString();
    }
    
}
