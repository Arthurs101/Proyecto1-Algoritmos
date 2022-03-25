
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Clase para la realizacion de operaciones aritmeticas
*/
public class Aritmetic {
    
    private Decoder dec = new Decoder();
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
    public Integer funadd(String expresion, HashMap<String,IFunction> fun,HashMap<String, Variable> parameters,Enviroment env){//metodo para suma de funciones (recursividad)
        Integer result = 0;
        Pattern operators = Pattern.compile("[(][ ]*(([a-zA-Z0-9]+)[ ]*[(](.+?)[)])[ ]*[)]",Pattern.CASE_INSENSITIVE);
        Matcher operatorseek = operators.matcher(expresion);
        String[] op = new String[2];//obetner funciones a operar y verificar existencia
        boolean first = true;
        while(operatorseek.find()){
            if(fun.containsKey(operatorseek.group(2).trim())){
                if(first){
                op[0] = operatorseek.group().trim();
            first = false;
            }else{
                op[1] = operatorseek.group().trim();
            }
            }
        }
        for (int i = 0; i < op.length; i++) {
            Pattern parts = Pattern.compile("[(][ ]*(([a-zA-Z0-9]+)[ ]*[(](.+?)[)])[ ]*[)]",Pattern.CASE_INSENSITIVE);
            Matcher partsseek = parts.matcher(op[i]);
            if(partsseek.find()){
                /*
                Operate parameter
                */
                String test = "(" + partsseek.group(3) + ")";
                FunctionRecursive temp = (FunctionRecursive) fun.get(operatorseek.group(2).trim());
                Object parameterresult = env.functionRunner(test,dec.decode(test),temp.parameters);
                /*
                Execute function
                */
                String paramtemp = String.valueOf(parameterresult);
                Object rtemp = temp.run(paramtemp);
                if(rtemp != null){
                    if(rtemp.getClass().equals(Integer.class)){
                        result += (Integer) rtemp;
                    }
                }
            }
        }
        return null;
    }
}
