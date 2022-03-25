
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Clase para las condicionales
*/
public class Conditional {
    /*
    recibe la expresion para comparar
    */
    public String Equals(String expresion ,  HashMap<String,Variable> vars){ // revisar si los objetos son iguales
        /*
        Compare objects
        */
        Pattern pattern = Pattern.compile("^[(][ ]*equal[ ]*([ ]*[a-zA-Z0-9]+[ ]+[a-zA-Z0-9]+[ ]*)[ ]*[)]$", Pattern.CASE_INSENSITIVE); //
        Matcher matcher = pattern.matcher(expresion);
        if(matcher.find()){
            String[] objects = matcher.group(1).split(" "); // obtiene el nombre de los objetos dentro del parentesis (x y)
            //validar que existan en las variables
            if(vars.containsKey(objects[0]) && vars.containsKey(objects[1])){
            //ver que sean del mismo tipo
                if(vars.get(objects[0]).ContentType().equals(vars.get(objects[1]).ContentType())){
                    //compare values
                    if(vars.get(objects[0]).getValue().equals(vars.get(objects[1]).getValue())){
                        return "true";
                    }else{
                        return "false";
                    }
                
                }return "false";
            }else{
                // compare numbers
                pattern = Pattern.compile("^[(][ ]*equal[ ]*([ ]*[0-9]+[ ]+[0-9]+[ ]*)[ ]*[)]$", Pattern.CASE_INSENSITIVE); //
                matcher = pattern.matcher(expresion);
                if(matcher.find()){
                    String[] objects2 = matcher.group(1).split(" "); // obtener numeros
                    int a = Integer.valueOf(objects2[0]);
                    int b = Integer.valueOf(objects2[1]);
                    if(a == b){
                    return "true";
                }else{
                    return "false";
                }
               
            }else{
            System.out.println("cannot compare, one or both operators are not defined yet");
            return null;
            }
        }
        
        }
        pattern = Pattern.compile("^[(][ ]*equal[ ]*([ ]*['][a-zA-Z0-9][']+[ ]+['][a-zA-Z0-9][']+[ ]*)[ ]*[)]$", Pattern.CASE_INSENSITIVE); //
        matcher = pattern.matcher(expresion);
        if(matcher.find()){
            String[] objects = matcher.group(1).split(" "); // obtener numeros
            if(objects[0].equals(objects[1])){
                return "true";
            }else{
                return "false";
            }
        }
        return null;
    }
    
}
