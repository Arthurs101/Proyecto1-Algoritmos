
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
    static AtomsFactory AF = new AtomsFactory();
    static HashMap<String,Variable> vars = new HashMap<>();

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
            if(result != null){
                switch(result){
                    case "END" -> {
                        System.out.println("You can't end me, kidding have a nice day");
                        System.exit(0);
                    }
                    case "PRINT" ->{
                        print(expresion);
                    }
                    
                    case "NEWVAR" ->{
                        Variable temp = AF.VariableCreatot(expresion);
                        if(temp != null){
                        vars.put(temp.name, temp);
                            System.out.println("Variable " + temp.name + " created correctly");
                        }
                    }
                    
                }
            }else{
                System.out.println("Expresion coulnd't be excecuted correctly");
            }
        }
    }
    private static void print(String expresion){
        expresion = expresion.replaceAll("print", "");
        Pattern pattern = Pattern.compile("[0-9]", Pattern.CASE_INSENSITIVE); //
	Matcher matcher = pattern.matcher(expresion);
        if(matcher.find()){
            System.out.println(matcher.group().trim());
        }
        pattern = Pattern.compile("['][a-z]+[']", Pattern.CASE_INSENSITIVE); //
        matcher = pattern.matcher(expresion);
        if (matcher.find()) { 
                 String temp = matcher.group().trim();
                 temp = temp.replaceAll("'", "");
                 System.out.println(temp);
        }
        
        pattern = Pattern.compile("[ ]+[a-z]+[ ]*", Pattern.CASE_INSENSITIVE); //
        matcher = pattern.matcher(expresion);
        if(matcher.find()){
            if(vars.containsKey(matcher.group().trim())){
                 System.out.println(vars.get(matcher.group().trim()).getValue());
            }else{
                System.out.println((matcher.group().trim()) + "\t is not defined yet");
            }
        }
        
    
    
    }
}
