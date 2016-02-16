
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author RubenGuillermo
 */
public class BruteForce  {
    
    public static void read(String archivo) {
      
        
    }
    
    public static void main(String[] args)  {
       //File file = new File ("SamplePairs.txt");
        String h = args[0];
        File file = new File("SamplePairs.txt");
        try{
        BufferedReader in = new BufferedReader(new FileReader(args[0]));
        String s;
        String []separar;
        s= in.readLine();
            while(s!=null){
                separar = s.split(",");
                
                for (String CP : separar) {
                    //System.out.println("PlainText and CipherText:: " + Arrays.toString(separar));
                    System.out.println("PlainText and CipherText:: " + CP);
                }
                
                s=in.readLine();
            }
             in.close();
        }
        catch(IOException e){
            System.err.println(e);
        }
    }
}
