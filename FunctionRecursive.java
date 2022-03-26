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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Clase referente a las funciones con condicionales y recursion
*/
public class FunctionRecursive implements IFunction {
public HashMap<String, Variable> parameters = new HashMap<>() ;//parameros y variables locales
private HashMap<String, String> exits = new HashMap<>();//condiciones de salida
private Enviroment env = Enviroment.getInstance();//ambiente de ejecucion
private final Decoder dec = new Decoder();//decodificador
public String faithjump = "";//salto de fe (recursividad)
public String name;//nombre
public String instructions;//instrucciones
private boolean faith = true; //realizar salto de fe
    public FunctionRecursive (String name,String instructions,String Jump,String[] args){
        this.name = name;
        this.instructions = instructions;
        this.faithjump = Jump;
        for (int i = 0; i < args.length; i++) {
            parameters.put(args[i], null);
        }
    
        /*
        Buscar condiciones
        */
        Pattern recursive = Pattern.compile("[(][ ]*cond[ ]*([(].+[)])[ ]*[)]",Pattern.CASE_INSENSITIVE );
        Matcher matcherr = recursive.matcher(instructions);
        if(matcherr.find()){
            String conditions = matcherr.group(1);
            Pattern cond = Pattern.compile("[ ]*[(][ ]*([(][ ]*(=|>|=>|<|<=|equal)[ ]+[a-zA-Z0-9]+[ ]+[a-zA-Z0-9]+[ ]*[)])[ ]+([(].*?[)])[ ]*[)][ ]*", Pattern.CASE_INSENSITIVE); // search for conditions
            Matcher conditionals = cond.matcher(conditions);
            while(conditionals.find()){
                exits.put(conditionals.group(1) , conditionals.group(3));
            }
        }
       
    
    }
    public FunctionRecursive (String name,String instructions,String Jump,HashMap<String, Variable> args){
        this.name = name;
        this.instructions = instructions;
        this.faithjump = Jump;
        this.parameters = args;
        System.out.println(faithjump);
        /*
        Buscar condiciones
        */
        Pattern recursive = Pattern.compile("[(][ ]*cond[ ]*([(].+[)])[ ]*[)]",Pattern.CASE_INSENSITIVE );
        Matcher matcherr = recursive.matcher(instructions);
        if(matcherr.find()){
            String conditions = matcherr.group(1);
            Pattern cond = Pattern.compile("[ ]*[(][ ]*([(][ ]*(=|>|=>|<|<=|equal)[ ]+[a-zA-Z0-9]+[ ]+[a-zA-Z0-9]+[ ]*[)])[ ]+([(].*?[)])[ ]*[)][ ]*", Pattern.CASE_INSENSITIVE); // search for conditions
            Matcher conditionals = cond.matcher(conditions);
            while(conditionals.find()){
                exits.put(conditionals.group(1) , conditionals.group(3));
            }
        }
       
    
    }
    @Override
    public Object run(String params) {
        if(!this.parameters.isEmpty()){
            String[] parameArray = params.split(" ");
            if(parameArray.length > parameters.size()){//mas parametros dados de los necesitados
                System.out.println("More objects than necesary given");
                return null;
            }else if (parameArray.length < parameters.size()){
                System.out.println("less objects than necesary given");
                return null;
            }else{
            Set<String> keySet = parameters.keySet();
            List<String> keySetA = new ArrayList<>(keySet);
            for (int i = 0; i < keySet.size(); i++) {
                Variable temp = null;
                System.out.println(parameArray[i]);
                temp = new Variable<Integer>(Integer.valueOf(String.valueOf(parameArray[i])),keySetA.get(i));
                parameters.put(keySetA.get(i),temp);
            }
        }
   
    }else{
            String[] parameArray = params.split(" ");
            if(parameArray.length != 0){
                System.out.println("function doesn't use parameters");
            }
        }
    /*
    Revisar condicionales
    */
    
    Object result = null;
    for(String k: exits.keySet()){//recorrer cada condicional
            Object Result = env.functionRunner(k, dec.decode(k), this.parameters);
            if(Result != null){
                if(Result.getClass().equals(String.class)){
                    if(Result.equals("true")){//si se cumple la condicional devolver el resultado de su instruccion
                        this.faith = false;
                        String instruction = exits.get(k);
                        
                        return env.functionRunner(instruction, dec.decode(instruction), this.parameters);
                    }
                }
            }else{
                System.out.println("Error");
                break;
            }
    }
    if(faith){//realizar salto de fe
        result = env.functionRunner(faithjump,dec.decode(faithjump),parameters);
        return result;
    }
    return result;
    
    }
    
}
