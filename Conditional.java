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
        Pattern pattern = Pattern.compile("^[(][ ]*equal[ ]+([ ]*[a-zA-Z0-9]+[ ]+[a-zA-Z0-9]+[ ]*)[ ]*[)]$", Pattern.CASE_INSENSITIVE); //
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
                pattern = Pattern.compile("^[(][ ]*equal[ ]+([ ]*[0-9]+[ ]+[0-9]+[ ]*)[ ]*[)]$", Pattern.CASE_INSENSITIVE); //
                matcher = pattern.matcher(expresion);
                if(matcher.find()){
                    String[] objects2 = matcher.group(1).split(" "); // obtener numeros a comparar
                    int a = Integer.valueOf(objects2[0]);
                    int b = Integer.valueOf(objects2[1]);
                    if(a == b){
                    return "true";
                }else{
                    return "false";
                }
               
            }else{//en caso no se halla una coincidencia de valores al descubrir que son variables
            System.out.println("cannot compare, one or both operators are not defined yet");
            return null;
            }
        }
        
        }
        pattern = Pattern.compile("^[(][ ]*equal[ ]+([ ]*['][a-zA-Z0-9][']+[ ]+['][a-zA-Z0-9][']+[ ]*)[ ]*[)]$", Pattern.CASE_INSENSITIVE); //
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
    
    
    /*
    Metodo para la comparacion > < , no admite Strings
    */
    public String MoreLess(String expresion ,  HashMap<String,Variable> vars){
         /*
        No se pueede operar con Strings
        */
        /*
        Comprar Mayores
        */
        Pattern pattern = Pattern.compile("^[(][ ]*[>][ ]+([ ]*[a-zA-Z0-9]+[ ]+[a-zA-Z0-9]+[ ]*)[ ]*[)]$", Pattern.CASE_INSENSITIVE); //
        Matcher matcher = pattern.matcher(expresion);
        if(matcher.find()){
        String objects = matcher.group(1); // obtiene el nombre de los objetos dentro del parentesis (x y)
        Pattern string = Pattern.compile ("['][a-zA-Z0-9]+[']", Pattern.CASE_INSENSITIVE);
        Matcher stringm = string.matcher(objects);
        if(stringm.find()){
            System.out.println("Strings not allowed");
            return null;
        }
        else{
        Pattern patterntemp = Pattern.compile ("[a-zA-Z0-9]+", Pattern.CASE_INSENSITIVE);
        Matcher matchertemp = patterntemp.matcher(objects);
        int[] object = new int[2];
        int index = 0;
        while(matchertemp.find()){
        String tempObject = matchertemp.group().trim();
        if(vars.containsKey(tempObject)){
            if(vars.get(tempObject).ContentType().equals(Integer.class)){
                if(index == 0){
                    object[0] = (Integer) vars.get(tempObject).getValue();
                    index++;
                }else{
                    object[1] = (Integer) vars.get(tempObject).getValue();
                }
            }else{
                System.out.println("String not supported for this opertion");
                return null;
            }
        }else{//ver si es un numero
                Pattern patterntemp2 = Pattern.compile ("[0-9]+", Pattern.CASE_INSENSITIVE);
                Matcher matchertemp2 = patterntemp2.matcher(tempObject);
                if(matchertemp2.find()){
                    if(index == 0){
                        object[0] = Integer.valueOf(tempObject);
                        index++;
                    }else{
                        object[1] = Integer.valueOf(tempObject);
                    }

                }else{
                    System.out.println(tempObject + " is not defined");
                    return null;
                }
            }
        }
        if(object[0] > object[1]){
            return "true";
        }else{
            return "false";
            }
        }
    } else{
    /*
        Comparar Menor
    */
    Pattern pattern2 = Pattern.compile("^[(][ ]*[<][ ]+([ ]*[a-zA-Z0-9]+[ ]+[a-zA-Z0-9]+[ ]*)[ ]*[)]$", Pattern.CASE_INSENSITIVE); //
    Matcher matcher2 = pattern2.matcher(expresion);
    if(matcher2.find()){
    String objects = matcher2.group(1); // obtiene el nombre de los objetos dentro del parentesis (x y)
    Pattern string = Pattern.compile ("['][a-zA-Z0-9]+[']", Pattern.CASE_INSENSITIVE);
    Matcher stringm = string.matcher(objects);
    if(stringm.find()){
        System.out.println("Strings not allowed");
        return null;
    }
    else{
    Pattern patterntemp = Pattern.compile ("[a-zA-Z0-9]+", Pattern.CASE_INSENSITIVE);
    Matcher matchertemp = patterntemp.matcher(objects);
    int[] object = new int[2];
    int index = 0;
    while(matchertemp.find()){
    String tempObject = matchertemp.group().trim();
    if(vars.containsKey(tempObject)){
        if(vars.get(tempObject).ContentType().equals(Integer.class)){
            if(index == 0){
                object[0] = (Integer) vars.get(tempObject).getValue();
                index++;
            }else{
                object[1] = (Integer) vars.get(tempObject).getValue();
            }
        }else{
            System.out.println("String not supported for this opertion");
            return null;
        }
    }else{//ver si es un numero
            Pattern patterntemp2 = Pattern.compile ("[0-9]+", Pattern.CASE_INSENSITIVE);
            Matcher matchertemp2 = patterntemp2.matcher(tempObject);
            if(matchertemp2.find()){
                if(index == 0){
                    object[0] = Integer.valueOf(tempObject);
                    index++;
                }else{
                    object[1] = Integer.valueOf(tempObject);
                }

            }else{
                System.out.println(tempObject + " is not defined");
                return null;
            }
        }
    }
    if(object[0] < object[1]){
        return "true";
    }else{
        return "false";
        }
    }
    }else{
        System.out.println("Invalid declaration");
        return null;
    }
        
    }
    }
    public String morelessEqual(String expresion ,  HashMap<String,Variable> vars){ 
        /*
        No se pueede operar con Strings
        */
        /*
        Comprar Mayores o iguales
        */
        Pattern pattern = Pattern.compile("^[(][ ]*=>[ ]+([ ]*[a-zA-Z0-9]+[ ]+[a-zA-Z0-9]+[ ]*)[ ]*[)]$", Pattern.CASE_INSENSITIVE); //
        Matcher matcher = pattern.matcher(expresion);
        if(matcher.find()){
        String objects = matcher.group(1); // obtiene el nombre de los objetos dentro del parentesis (x y)
        Pattern string = Pattern.compile ("['][a-zA-Z0-9]+[']", Pattern.CASE_INSENSITIVE);
        Matcher stringm = string.matcher(objects);
        if(stringm.find()){
            System.out.println("Strings not allowed");
            return null;
        }
        else{
        Pattern patterntemp = Pattern.compile ("[a-zA-Z0-9]+", Pattern.CASE_INSENSITIVE);
        Matcher matchertemp = patterntemp.matcher(objects);
        int[] object = new int[2];
        int index = 0;
        while(matchertemp.find()){
        String tempObject = matchertemp.group().trim();
        if(vars.containsKey(tempObject)){
            if(vars.get(tempObject).ContentType().equals(Integer.class)){
                if(index == 0){
                    object[0] = (Integer) vars.get(tempObject).getValue();
                    index++;
                }else{
                    object[1] = (Integer) vars.get(tempObject).getValue();
                }
            }else{
                System.out.println("String not supported for this opertion");
                return null;
            }
        }else{//ver si es un numero
                Pattern patterntemp2 = Pattern.compile ("[0-9]+", Pattern.CASE_INSENSITIVE);
                Matcher matchertemp2 = patterntemp2.matcher(tempObject);
                if(matchertemp2.find()){
                    if(index == 0){
                        object[0] = Integer.valueOf(tempObject);
                        index++;
                    }else{
                        object[1] = Integer.valueOf(tempObject);
                    }

                }else{
                    System.out.println(tempObject + " is not defined");
                    return null;
                }
            }
        }
        if(object[0] >= object[1]){
            return "true";
        }else{
            return "false";
            }
        }
    } else{
    /*
        Comparar Menor o igual
    */
    Pattern pattern2 = Pattern.compile("^[(][ ]*=<[ ]+([ ]*[a-zA-Z0-9]+[ ]+[a-zA-Z0-9]+[ ]*)[ ]*[)]$", Pattern.CASE_INSENSITIVE); //
    Matcher matcher2 = pattern2.matcher(expresion);
    if(matcher2.find()){
    String objects = matcher2.group(1); // obtiene el nombre de los objetos dentro del parentesis (x y)
    Pattern string = Pattern.compile ("['][a-zA-Z0-9]+[']", Pattern.CASE_INSENSITIVE);
    Matcher stringm = string.matcher(objects);
    if(stringm.find()){
        System.out.println("Strings not allowed");
        return null;
    }
    else{
    Pattern patterntemp = Pattern.compile ("[a-zA-Z0-9]+", Pattern.CASE_INSENSITIVE);
    Matcher matchertemp = patterntemp.matcher(objects);
    int[] object = new int[2];
    int index = 0;
    while(matchertemp.find()){
    String tempObject = matchertemp.group().trim();
    if(vars.containsKey(tempObject)){
        if(vars.get(tempObject).ContentType().equals(Integer.class)){
            if(index == 0){
                object[0] = (Integer) vars.get(tempObject).getValue();
                index++;
            }else{
                object[1] = (Integer) vars.get(tempObject).getValue();
            }
        }else{
            System.out.println("String not supported for this opertion");
            return null;
        }
    }else{//ver si es un numero
            Pattern patterntemp2 = Pattern.compile ("[0-9]+", Pattern.CASE_INSENSITIVE);
            Matcher matchertemp2 = patterntemp2.matcher(tempObject);
            if(matchertemp2.find()){
                if(index == 0){
                    object[0] = Integer.valueOf(tempObject);
                    index++;
                }else{
                    object[1] = Integer.valueOf(tempObject);
                }

            }else{
                System.out.println(tempObject + " is not defined");
                return null;
            }
        }
    }
    if(object[0] <= object[1]){
        return "true";
    }else{
        return "false";
        }
    }
    }else{
        System.out.println("Invalid declaration");
        return null;
    }
        
    }
    
    }
    
    public String EqualValue(String expresion, HashMap<String,Variable> vars ){ //comparacion de valor entre 2 objetos
        Pattern pattern = Pattern.compile("^[(][ ]*[=][ ]+([ ]*.+[ ]+.+[ ]*)[ ]*[)]$", Pattern.CASE_INSENSITIVE); //
        Matcher matcher = pattern.matcher(expresion);
        if(matcher.find()){
            Object[] elemts = new Object[2]; //almacenar los elementos
            int index = 0;
            String objects = matcher.group(1); // obtiene el nombre de los objetos dentro del parentesis (x y)
            Pattern element = Pattern.compile ("(['][a-zA-Z0-9]+[']|[a-zA-Z0-9]+)", Pattern.CASE_INSENSITIVE); //buscar los elementos
            Matcher elementm = element.matcher(objects);
            while(elementm.find()){
                String Objectc =  elementm.group().trim();
                Objectc = Objectc.replaceAll(" ", "");
                Pattern string = Pattern.compile("['][a-zA-Z0-9]+[']",Pattern.CASE_INSENSITIVE);//ver si es string
                Matcher stringm = string.matcher(Objectc);
                if(stringm.find()){
                    System.out.println("True");
                    if(index == 0){
                        String temp = (String) stringm.group().trim();
                        elemts[0] = temp.replaceAll("'", "");
                        index++;
                    }else{
                        String temp = (String) stringm.group().trim();
                        elemts[1] = temp.replaceAll("'", "");
                    }
                }else{
                Pattern patterntemp = Pattern.compile ("[a-zA-Z0-9]+", Pattern.CASE_INSENSITIVE);
                Matcher matchertemp = patterntemp.matcher(Objectc);
                if(matchertemp.find()){//ver si es una varible
                    
                    if(vars.containsKey(matchertemp.group().trim())){
                        if(index == 0){
                            elemts[0] = vars.get(matchertemp.group().trim()).getValue();
                            index++;
                        }else{
                            elemts[1] = vars.get(matchertemp.group().trim()).getValue();
                        }
                    }
                else{//ver si es un numero
                    Pattern patterntemp2 = Pattern.compile ("[0-9]+", Pattern.CASE_INSENSITIVE);
                    Matcher matchertemp2 = patterntemp2.matcher(Objectc);
                    if(matchertemp2.find()){
                        if(index == 0){
                            elemts[0] = Integer.valueOf(Objectc);
                            index++;
                        }else{
                            elemts[1] = Integer.valueOf(Objectc);
                        }

                    }else{
                        System.out.println(elementm.group() + " is not defined");
                        return null;
                    }
                }
            }
            }
            }
            if(elemts[0].getClass().equals(elemts[1].getClass())){//compare directly if same class
                if(!(String.valueOf(elemts[0]) == null ? String.valueOf(elemts[1]) == null : String.valueOf(elemts[0]).equals(String.valueOf(elemts[1])))){
                    return "false";
                }else{//if not the same class compare string values
                    return "true";
                }
            }
        }
        return null;
    }

}

    

    

