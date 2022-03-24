
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
public class AtomsFactory {
    public Variable VariableCreatot(String expresion) {
        /*
                Hallar el nombre y el valor
            */
            Pattern pattern = Pattern.compile("[ ]+[a-z]+[ ]+", Pattern.CASE_INSENSITIVE); //
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
             
             pattern = Pattern.compile("[ ]+['][a-z]+['][ ]*", Pattern.CASE_INSENSITIVE); //
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
