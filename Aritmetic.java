
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Clase para la realizacion de operaciones aritmeticas
*/
public class Aritmetic {
    public Integer add(String expresion, HashMap<String,Variable> var){
    Pattern pattern = Pattern.compile("([a-z]+|[0-9]+)", Pattern.CASE_INSENSITIVE); //
    Matcher matcher = pattern.matcher(expresion);
    Integer total = 0;
    boolean print = true;
    while (matcher.find()) {
            if(var.containsKey(matcher.group().trim())){
                if(var.get(matcher.group().trim()).ContentType().equals(Integer.class)){//ver que sea un numero
                total += (Integer) var.get(matcher.group().trim()).getValue();
                }else{
                    System.out.println("Incopatible operators");
                    print = false;
                    break;
                }
            }else{
                try{
                total += Integer.parseInt(matcher.group().trim());
                } catch(NumberFormatException ei){
                    System.out.println( matcher.group().trim() + " is not defined");
                    print = false;
                    break;
                }
            }
        
    }   if(print){
        return total;
        }
        return null;
    }
    
    
    public Integer quit(String expresion, HashMap<String,Variable> var){
    Pattern pattern = Pattern.compile("([a-z]+|[0-9]+)", Pattern.CASE_INSENSITIVE); //
    Matcher matcher = pattern.matcher(expresion);
    /*
    Usar el primer match como el valor inicial
    */
    Integer total = 0;
    boolean skip = false;//en caso de no haber un match inicial se salta realizar la operacion
    if(matcher.find()){
        if(var.containsKey(matcher.group().trim())){
            if(var.get(matcher.group().trim()).ContentType().equals(Integer.class)){//ver que sea un numero
            total = (Integer) var.get(matcher.group().trim()).getValue();
            }else{
                System.out.println("Incopatible operators");
                skip = true;
            }
        }else{
            try{
            total = Integer.parseInt(matcher.group().trim());
            } catch(NumberFormatException ei){
                System.out.println( matcher.group().trim() + " is not defined");
                skip = true;
            }
        }
    }
       
    if(!skip){
        boolean print = true;
        while (matcher.find()) {
                if(var.containsKey(matcher.group().trim())){
                    if(var.get(matcher.group().trim()).ContentType().equals(Integer.class)){//ver que sea un numero
                    total -= (Integer) var.get(matcher.group().trim()).getValue();
                    }else{
                        System.out.println("Incopatible operators");
                        print = false;
                        break;
                    }
                }else{
                    try{
                    total -= Integer.parseInt(matcher.group().trim());
                    } catch(NumberFormatException ei){
                        System.out.println( matcher.group().trim() + " is not defined");
                        print = false;
                        break;
                    }
                }

        }   if(print){
            return total;
            }
        }
        return null;
    }
    
    
    
    public Integer multi(String expresion, HashMap<String,Variable> var){
    Pattern pattern = Pattern.compile("([a-z]+|[0-9]+)", Pattern.CASE_INSENSITIVE); //
    Matcher matcher = pattern.matcher(expresion);
    Integer total = 1; //se coloca como 1 puesto que no interfiere con el resultado de multiplicar los numeros
    boolean print = true;
    while (matcher.find()) {
            if(var.containsKey(matcher.group().trim())){
                if(var.get(matcher.group().trim()).ContentType().equals(Integer.class)){//ver que sea un numero
                total *= (Integer) var.get(matcher.group().trim()).getValue();
                }else{
                    System.out.println("Incopatible operators");
                    print = false;
                    break;
                }
            }else{
                try{
                total *= Integer.parseInt(matcher.group().trim());
                } catch(NumberFormatException ei){
                    System.out.println( matcher.group().trim() + " is not defined");
                    print = false;
                    break;
                }
            }
        
    }   if(print){
        return total;
        }
        return null;
    }
    
    
    public Integer div(String expresion, HashMap<String,Variable> var){
    Pattern pattern = Pattern.compile("([a-z]+|[0-9]+)", Pattern.CASE_INSENSITIVE); //
    Matcher matcher = pattern.matcher(expresion);
    /*
    Usar el primer match como el valor inicial
    */
    Integer total = 0;
    boolean skip = false;//en caso de no haber un match inicial se salta realizar la operacion
    if(matcher.find()){
        if(var.containsKey(matcher.group().trim())){
            if(var.get(matcher.group().trim()).ContentType().equals(Integer.class)){//ver que sea un numero
            total = (Integer) var.get(matcher.group().trim()).getValue();
            }else{
                System.out.println("Incopatible operators");
                skip = true;
            }
        }else{
            try{
            total = Integer.parseInt(matcher.group().trim());
            } catch(NumberFormatException ei){
                System.out.println( matcher.group().trim() + " is not defined");
                skip = true;
            }
        }
    }
    
    if(!skip){
    boolean print = true;
        while (matcher.find()) {
            try{
                if(var.containsKey(matcher.group().trim())){
                    if(var.get(matcher.group().trim()).ContentType().equals(Integer.class)){//ver que sea un numero
                    total /= (Integer) var.get(matcher.group().trim()).getValue();
                    }else{
                        System.out.println("Incopatible operators");
                        print = false;
                        break;
                    }
                }else{
                    try{
                    total /= Integer.parseInt(matcher.group().trim());
                    } catch(NumberFormatException ei){
                        System.out.println( matcher.group().trim() + " is not defined");
                        print = false;
                        break;
                    }
                }
            }catch (ArithmeticException e) {
                // Exception handler
                System.out.println(
                    "Divided by zero operation");
                print = false;
                break;

            }

        }
            if(print){
            return total;
            }
        }
        return null;
    }
    
}
