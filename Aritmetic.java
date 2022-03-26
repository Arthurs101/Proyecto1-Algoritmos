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
        Pattern operators = Pattern.compile("^[(][+][ ]*[(]([a-zA-Z0-9]+)[ ]+([(].+?[)])[)][ ]+[(]([a-zA-Z0-9]+)[ ]+([(].+?[)])[)][)]",Pattern.CASE_INSENSITIVE);
        Matcher operatorseek = operators.matcher(expresion);
        if(operatorseek.find()){
            if(fun.containsKey(operatorseek.group(1).trim())){//si existe la primera funcion
                //operar parametro
                Object presult = env.functionRunner(operatorseek.group(2).trim(), dec.decode(operatorseek.group(2).trim()), parameters);
                if(presult == null){
                    return null;
                }else{
                    //relizar la operacion de la funcion
                    IFunction temp = fun.get(operatorseek.group(1).trim());
                    Object Fresult = temp.run(String.valueOf(presult));
                    if(Fresult == null){
                        return null;
                    }else{
                        try{//intentar sumar
                            result += Integer.valueOf(String.valueOf(Fresult));
                        }catch(Exception e){
                            return null;
                        }
                    }
                        
                }    
            }
            else{
            return null;
            }
            if(fun.containsKey(operatorseek.group(3).trim())){//si existe la primera funcion
                //operar parametro
                Object presult = env.functionRunner(operatorseek.group(4).trim(), dec.decode(operatorseek.group(4).trim()), parameters);
                if(presult == null){
                    return null;
                }else{
                    //relizar la operacion de la funcion
                    IFunction temp = fun.get(operatorseek.group(3).trim());
                    Object Fresult = temp.run(String.valueOf(presult));
                    if(Fresult == null){
                        return null;
                    }else{
                        try{//intentar sumar
                            result += Integer.valueOf(String.valueOf(Fresult));
                        }catch(Exception e){
                            return null;
                        }
                    }
                        
                }    
            }
            else{
            return null;
            }
            System.out.println("Result of some recursivity");
            System.out.println(result);
            return result;
        }
        return null;
        }
    
    public Integer funquit(String expresion, HashMap<String,IFunction> fun,HashMap<String, Variable> parameters,Enviroment env){//metodo para suma de funciones (recursividad)
        Integer result = 0;
        Pattern operators = Pattern.compile("^[(][-][ ]*[(]([a-zA-Z0-9]+)[ ]+([(].+?[)])[)][ ]+[(]([a-zA-Z0-9]+)[ ]+([(].+?[)])[)][)]",Pattern.CASE_INSENSITIVE);
        Matcher operatorseek = operators.matcher(expresion);
        if(operatorseek.find()){
            if(fun.containsKey(operatorseek.group(1).trim())){//si existe la primera funcion
                //operar parametro
                Object presult = env.functionRunner(operatorseek.group(2).trim(), dec.decode(operatorseek.group(2).trim()), parameters);
                if(presult == null){
                    return null;
                }else{
                    //relizar la operacion de la funcion
                    IFunction temp = fun.get(operatorseek.group(1).trim());
                    Object Fresult = temp.run(String.valueOf(presult));
                    if(Fresult == null){
                        return null;
                    }else{
                        try{//intentar colocarlo como el minuendo
                            result = Integer.valueOf(String.valueOf(Fresult));
                        }catch(Exception e){
                            return null;
                        }
                    }
                        
                }    
            }
            else{
            return null;
            }
            if(fun.containsKey(operatorseek.group(3).trim())){//si existe la primera funcion
                //operar parametro
                Object presult = env.functionRunner(operatorseek.group(4).trim(), dec.decode(operatorseek.group(4).trim()), parameters);
                if(presult == null){
                    return null;
                }else{
                    //relizar la operacion de la funcion
                    IFunction temp = fun.get(operatorseek.group(3).trim());
                    Object Fresult = temp.run(String.valueOf(presult));
                    if(Fresult == null){
                        return null;
                    }else{
                        try{//intentar sumar
                            result += Integer.valueOf(String.valueOf(Fresult));
                        }catch(Exception e){
                            return null;
                        }
                    }
                        
                }    
            }
            else{
            return null;
            }
            System.out.println("Result of some recursivity");
            System.out.println(result);
            return result;
        }
        return null;
        }
    public Integer funmulti(String expresion, HashMap<String,IFunction> fun,HashMap<String, Variable> parameters,Enviroment env){//metodo para suma de funciones (recursividad)
        Integer result = 0;
        Pattern operators = Pattern.compile("^[(][*][ ]*[(]([a-zA-Z0-9]+)[ ]+([(].+?[)])[)][ ]+[(]([a-zA-Z0-9]+)[ ]+([(].+?[)])[)][)]",Pattern.CASE_INSENSITIVE);
        Matcher operatorseek = operators.matcher(expresion);
        if(operatorseek.find()){
            if(fun.containsKey(operatorseek.group(1).trim())){//si existe la primera funcion
                //operar parametro
                Object presult = env.functionRunner(operatorseek.group(2).trim(), dec.decode(operatorseek.group(2).trim()), parameters);
                if(presult == null){
                    return null;
                }else{
                    //relizar la operacion de la funcion
                    IFunction temp = fun.get(operatorseek.group(1).trim());
                    Object Fresult = temp.run(String.valueOf(presult));
                    if(Fresult == null){
                        return null;
                    }else{
                        try{//intentar colocarlo como el termino inicial
                            result = Integer.valueOf(String.valueOf(Fresult));
                        }catch(Exception e){
                            return null;
                        }
                    }
                        
                }    
            }
            else{
            return null;
            }
            if(fun.containsKey(operatorseek.group(3).trim())){//si existe la primera funcion
                //operar parametro
                Object presult = env.functionRunner(operatorseek.group(4).trim(), dec.decode(operatorseek.group(4).trim()), parameters);
                if(presult == null){
                    return null;
                }else{
                    //relizar la operacion de la funcion
                    IFunction temp = fun.get(operatorseek.group(3).trim());
                    Object Fresult = temp.run(String.valueOf(presult));
                    if(Fresult == null){
                        return null;
                    }else{
                        try{//intentar multiplicar
                            result *= Integer.valueOf(String.valueOf(Fresult));
                        }catch(Exception e){
                            return null;
                        }
                    }
                        
                }    
            }
            else{
            return null;
            }
            System.out.println("Result of some recursivity");
            System.out.println(result);
            return result;
        }
        return null;
        }
        public Integer fundiv(String expresion, HashMap<String,IFunction> fun,HashMap<String, Variable> parameters,Enviroment env){//metodo para suma de funciones (recursividad)
        Integer result = 0;
        Pattern operators = Pattern.compile("^[(][*][ ]*[(]([a-zA-Z0-9]+)[ ]+([(].+?[)])[)][ ]+[(]([a-zA-Z0-9]+)[ ]+([(].+?[)])[)][)]",Pattern.CASE_INSENSITIVE);
        Matcher operatorseek = operators.matcher(expresion);
        if(operatorseek.find()){
            if(fun.containsKey(operatorseek.group(1).trim())){//si existe la primera funcion
                //operar parametro
                Object presult = env.functionRunner(operatorseek.group(2).trim(), dec.decode(operatorseek.group(2).trim()), parameters);
                if(presult == null){
                    return null;
                }else{
                    //relizar la operacion de la funcion
                    IFunction temp = fun.get(operatorseek.group(1).trim());
                    Object Fresult = temp.run(String.valueOf(presult));
                    if(Fresult == null){
                        return null;
                    }else{
                        try{//intentar colocarlo como el dividendo
                            result = Integer.valueOf(String.valueOf(Fresult));
                        }catch(Exception e){
                            return null;
                        }
                    }
                        
                }    
            }
            else{
            return null;
            }
            if(fun.containsKey(operatorseek.group(3).trim())){//si existe la primera funcion
                //operar parametro
                Object presult = env.functionRunner(operatorseek.group(4).trim(), dec.decode(operatorseek.group(4).trim()), parameters);
                if(presult == null){
                    return null;
                }else{
                    //relizar la operacion de la funcion
                    IFunction temp = fun.get(operatorseek.group(3).trim());
                    Object Fresult = temp.run(String.valueOf(presult));
                    if(Fresult == null){
                        return null;
                    }else{
                        try{//intentar dividir
                            result /= Integer.valueOf(String.valueOf(Fresult));
                        }catch(Exception e){
                            return null;
                        }
                    }
                        
                }    
            }
            else{
            return null;
            }
            System.out.println("Result of some recursivity");
            System.out.println(result);
            return result;
        }
        return null;
        }
}

