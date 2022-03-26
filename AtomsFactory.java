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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Clase encargada en la cracion de variables
*/
public class AtomsFactory {
    public Variable VariableCreatot(String expresion) {
        /*
                Hallar el nombre y el valor
            */
            Pattern pattern = Pattern.compile("[ ]+[a-zA-Z0-9]+[ ]+", Pattern.CASE_INSENSITIVE); //
	    Matcher matcher = pattern.matcher(expresion);
	    String varName = "";
            if (matcher.find()) {
	         varName = matcher.group();
                 varName = varName.replaceAll(" ", "");
	    
            /*
            Revisar si es valor numerico
            */
            pattern = Pattern.compile("[ ]+[0-9]+[ ]*", Pattern.CASE_INSENSITIVE); //
	     matcher = pattern.matcher(expresion);
	     if (matcher.find()) { 
                 /*
                 Si es numerico regresar la variable
                 */
	    	 return new Variable<Integer>(Integer.parseInt(matcher.group().trim()), varName);
                }
             
             pattern = Pattern.compile("[ ]+['][a-zA-Z0-9]+['][ ]*", Pattern.CASE_INSENSITIVE); //
	     matcher = pattern.matcher(expresion);
             if (matcher.find()) { 
                 String temp = matcher.group().trim();
                 temp = temp.replaceAll("'", "");
                 return new Variable<String>(temp, varName); 
                }
                
            }else{
                System.out.println("Invalid sintaxis for variable declaration");
                return null;
            }
            return null;
    }
}
