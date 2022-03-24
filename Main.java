
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author inmar
 */
public class Main {
    static Scanner me = new Scanner(System.in);
    static Decoder decode = new Decoder();
    static Enviroment env = Enviroment.getInstance();
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    System.out.println("""
                         ___    __           __       
                         / _ |  / /    ___   / /  ___ _
                        / __ | / /__  / _ \\ / _ \\/ _ `/
                       /_/ |_|/____/ / .__//_//_/\\_,_/ 
                                    /_/                """);
    System.out.println("Lisp Interpreter");
    System.out.println("System ready");
    System.out.println("To exit type (end)");

    while(true){
        String expresion = me.nextLine();
        String result = decode.decode(expresion);
        env.excecute(expresion, result);
            
    }
    
        
    
    
    }
}
