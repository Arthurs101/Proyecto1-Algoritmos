/*
Universidad del Valle de Guatemala
Algoritmos y Estructura de datos
Catedratico: Moises Alonso
Tercer Semestre 2022
Grupo 1:
Arturo Argueta: 21527
Astrid Glauser: 21299
Abner Garcia: 21285
Gonzalo Santizo: 21504
Seccion 20
Actividad: Proyecto 1 Fase 1
*/
/*
Clase que decodifica la expresion de lisp
*/
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Decoder {
    /*
    Regresa un String correspondiente de la expresion, puede regresar variables y funciones, 
    si es desconocido ,expresion artimetica u otro comando regresa el string
    */
    
    public String decode(String expresion){

        if(evaluate("^[(][ ]*setq[ ]+[a-zA-Z0-9]+[ ]+[0-9]+[ ]*[)]$",expresion) || evaluate("^[(][ ]*setq[ ]+[a-zA-Z0-9]+[ ]+['][a-zA-Z0-9]+['][ ]*[)]$",expresion)){
            return "NEWVAR";
        }
        if(evaluate("^[(][ ]*end[ ]*[)]$",expresion)){
            return "END";
        }
        if(evaluate("^[(][ ]*print[ ]+[a-zA-Z0-9][ ]*[)]$",expresion)|| evaluate("^[(][ ]*print[ ]+[0-9][ ]*[)]$",expresion) ){//print variables or numbers
            return "PRINT";
        }
        if(evaluate("^[(][ ]*print[ ]+['][a-zA-Z0-9]+['][ ]*[)]$",expresion)){//print strings
             return "PRINT";
        }
        if (evaluate("^[(][ ]*[+][ ]+[([a-z]+|[0-9]+)[ ]+([a-z]+|[0-9]+)]+[ ]*[)]$",expresion)){
            return "ADD";
        }
        if (evaluate("^[(][ ]*[-][ ]+[([a-z]+|[0-9]+)[ ]+([a-z]+|[0-9]+)]+[ ]*[)]$",expresion)){
            return "QUIT";
        }
        if (evaluate("^[(][ ]*[*][ ]+[([a-z]+|[0-9]+)[ ]+([a-z]+|[0-9]+)]+[ ]*[)]$",expresion)){
            return "MUL";
        }
        if (evaluate("^[(][ ]*[/][ ]+[([a-z]+|[0-9]+)[ ]+([a-z]+|[0-9]+)]+[ ]*[)]$",expresion)){
            return "DIV";
        }
        if (evaluate("^[(][ ]*('|quote)[ ]*([(].+[)])[ ]*[)]$",expresion)){
            return "QUT";
        }
        if (evaluate("^[(][ ]*equal[ ]*(.+)[ ]*[)]$",expresion)){//evaluate if x equals b
            return "EVA01";
        }
        if(evaluate("^[(][ ]*(<|>)[ ]*(.+)[ ]*[)]$",expresion)){
            return "EVA02";
        }
        if(evaluate("^[(][ ]*(=<|=>)[ ]*(.+)[ ]*[)]$",expresion)){
            return "EVA03";
        }
        if(evaluate("^[(][ ]*[=][ ]*(.+)[ ]*[)]$",expresion)){
            return "EVA04";
        }
        if(evaluate("^[(][ ]*([a-zA-Z0-9])+[ ]*([(].+[)])[ ]*[)]$",expresion)){//ejecutar una funcion 
            return "FUN";
        }
        if(evaluate("^[(][ ]*defun[ ]+([a-zA-Z0-9]+)[ ]*([(].+[)])[ ]*([(].+[)])[ ]*[)]$",expresion)){//ejecutar una funcion 
            return "DEFUN";
        }
        if(evaluate("^[(][+][ ]*[(]([a-zA-Z0-9]+)[ ]+([(].+?[)])[)][ ]+[(]([a-zA-Z0-9]+)[ ]+([(].+?[)])[)][)]",expresion)){ 
            return "ADDFUN";
        }
        if(evaluate("^[(][ ]*[-][ ]+[(][a-z]+[ ]*[(].[)][)][ ]+[(][a-zA-Z0-9]+[ ]*[ ]*[(].[)][)][ ]*[)]$",expresion)){
             return "QUITFUN";
        }
        if(evaluate("^[(][ ]*[*][ ]+[(][a-z]+[ ]*[(].[)][)][ ]+[(][a-zA-Z0-9]+[ ]*[ ]*[(].[)][)][ ]*[)]$",expresion)){
             return "MULTIFUN";
        }
        if(evaluate("^[(][ ]*[/][ ]+[(][a-z]+[ ]*[(].[)][)][ ]+[(][a-zA-Z0-9]+[ ]*[ ]*[(].[)][)][ ]*[)]$",expresion)){
             return "DIVFUN";
        }
        if(evaluate("^[(][ ]*[0-9]+[ ]*[)]$",expresion)){
            return "returnInt";
        }
        if(evaluate("^[(][ ]*[a-zA-Z0-9]+[ ]*[)]$",expresion)){
            return "returnVar";
        }
        if(evaluate("^[(][ ]*([a-zA-Z0-9]+)[ ]*([(]([a-zA-z0-9])[)])[ ]*[)]$",expresion)){
            return "FUN";
        }
        if(evaluate("-h",expresion)){
            return "HELP";
        }
          return "COMPLEX?";//en caso de poder detectar la expresion, revisar si no es una expresion aritmetica compleja
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
