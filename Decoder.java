/*
Clase que decodifica la expresion de lisp
*/
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Decoder {
    /*
    Regresa un objeto correspondiente de la expresion, puede regresar variables y funciones, 
    si es desconocido ,expresion artimetica u otro comando regresa el string
    */
    
    public String decode(String expresion){
    
        /*revisar si es una asigncion de variable
        */
        if(evaluate("^[(][ ]*setq[ ]+[a-z]+[ ]+[0-9]+[ ]*[)]$",expresion) || evaluate("^[(][ ]*setq[ ]+[a-z]+[ ]+['][a-z]+['][ ]*[)]$",expresion)){
            return "NEWVAR";
        }
        if(evaluate("^[(][ ]*end[ ]*[)]$",expresion)){
            return "END";
        }
        if(evaluate("^[(][ ]*print[ ]+[a-z][ ]*[)]$",expresion)|| evaluate("^[(][ ]*print[ ]+[0-9][ ]*[)]$",expresion) ){
            return "PRINT";
        }
        if(evaluate("^[(][ ]*print[ ]+['][a-z]+['][ ]*[)]$",expresion)){
             return "PRINT";
        }
          return null;
    }
    

    
    
    /*
    Metodo para evaluar la expresion deseada
    */
    private static boolean evaluate(String regex, String expresion) {
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(expresion);
	    return matcher.find();
	}
   
}