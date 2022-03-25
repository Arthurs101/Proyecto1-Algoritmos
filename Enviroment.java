
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Clase Singleton del ambiente de ejecucion
*/
public class Enviroment {
     
     AtomsFactory AF = new AtomsFactory();
     HashMap<String,Variable> vars = new HashMap<>();
     Aritmetic ALU = new Aritmetic();

    private static Enviroment env;// variable estatica
    private Enviroment(){
    
    }
    public synchronized static Enviroment getInstance() {
        if (env == null) {
            env = new Enviroment();
        }
        return env;
    }
    
    public synchronized void excecute(String expresion, String result){
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
                    case "ADD" ->{
                        ALU.add(expresion, vars);
                    }
                    case "QUIT" ->{
                        ALU.quit(expresion, vars);
                    }
                    case "MUL"->{
                        ALU.multi(expresion, vars);
                    }
                    case "DIV" ->{
                        ALU.div(expresion, vars);
                    }
                    case "QUT" -> {
                        quote(expresion);
                    }
                    case "EVA01" ->{
                        Conditional temp = new Conditional();
                        String tempR = temp.Equals(expresion, vars);
                        if(tempR != null){
                            System.out.println(tempR);
                        }
                        
                    }
                    
                }
            }else{
                System.out.println("Expresion coulnd't be excecuted correctly");
            }
    
    }
     private synchronized void print(String expresion){
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
     
     private synchronized void quote(String expresion){
        Pattern pattern = Pattern.compile("^[(][ ]*('|quote)[ ]*([(].+[)])[ ]*[)]$", Pattern.CASE_INSENSITIVE); //
         Matcher matcher = pattern.matcher(expresion);
        while(matcher.find()){
            System.out.println(matcher.group(2));
        }
     
     }
    
}
