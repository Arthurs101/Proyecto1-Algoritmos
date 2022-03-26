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
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Clase Singleton del ambiente de ejecucion
*/
public class Enviroment {
     
     AtomsFactory AF = new AtomsFactory();
     HashMap<String,Variable> vars = new HashMap<>();
     HashMap<String,IFunction> fun = new HashMap<>();
     Aritmetic ALU = new Aritmetic();
     Decoder dec = new Decoder();
     private static Enviroment env;// variable estatica
    private Enviroment(){
    
    }
    public synchronized static Enviroment getInstance() {
        if (env == null) {
            env = new Enviroment();
        }
        return env;
    }
    
    
    public synchronized void excecute(String expresion, String result){//para realizarse en tiempo real
    if(result != null){//ejecutar segun su instruccion
                switch(result){
                    case "END" -> {//terminar
                        System.out.println("You can't end me, kidding have a nice day");
                        System.exit(0);
                    }
                    case "PRINT" ->{//imprimir
                        print(expresion);
                    }
                    
                    case "NEWVAR" ->{//declaracion de variable
                        Variable temp = AF.VariableCreatot(expresion);
                        if(temp != null){
                        vars.put(temp.name, temp);
                            System.out.println("Variable " + temp.name + " created correctly");
                        }
                    }
                    /*
                    A continuacion se presentan operaciones de suma, resta, multiplicaion y division de n numeros
                    */
                    case "ADD" ->{
                    Integer add = ALU.add(expresion, vars);
                    if(add != null){
                        System.out.println(add);
                    }else{
                        System.out.println("Error");
                    }
                    }
                    case "QUIT" ->{
                       Integer add = ALU.quit(expresion, vars);
                       if(add != null){
                        System.out.println(add);
                        }else{
                            System.out.println("Error");
                        }
                    }
                    
                    case "MUL"->{
                        Integer add= ALU.multi(expresion, vars);
                        if(add != null){
                        System.out.println(add);
                        }else{
                            System.out.println("Error");
                        }
                    }
                    case "DIV" ->{
                        Integer add = ALU.div(expresion, vars);
                        if(add != null){
                        System.out.println(add);
                        }else{
                            System.out.println("Error");
                        }
                    }
                    case "QUT" -> {
                        quote(expresion);
                    }
                    /*
                    Tipos de comparacion
                    */
                    case "EVA01" ->{
                        Conditional temp = new Conditional();//Equals
                        String tempR = temp.Equals(expresion, vars);
                        if(tempR != null){
                            System.out.println(tempR);
                        }
                    }
                    case "EVA02"->{//Operadores < >
                        Conditional temp = new Conditional();
                        String tempR = temp.MoreLess(expresion, vars);
                        if(tempR != null){
                            System.out.println(tempR);
                        }
                    }
                    case "EVA03" ->{// operadores =< =>
                        Conditional temp = new Conditional();
                        String tempR = temp.morelessEqual(expresion, vars);
                        if(tempR != null){
                            System.out.println(tempR);
                        }
                    
                    }
                    case "EVA04" ->{ // operador =
                        Conditional temp = new Conditional();
                        String tempR = temp.EqualValue(expresion, vars);
                        if(tempR != null){
                            System.out.println(tempR);
                        }
                    
                    }
                    case "DEFUN" ->{//declaracuib de una funcion
                        System.out.println("Defun");
                        defun(expresion);
                    }
                    case "returnInt" -> {
                        Pattern pattern = Pattern.compile("[0-9]+", Pattern.CASE_INSENSITIVE); //regresar un int entre ()
                        Matcher matcher = pattern.matcher(expresion);
                        if(matcher.find()){
                            System.out.println(matcher.group().trim());
                        }
                    }
                    case "COMPLEX?" ->{ //Revision si es una funcion compleja aritmetica
                    try{
                        Combinadas complex = new Combinadas();
                        System.out.println(complex.evaluate((List) complex.conver(expresion)));
                    }catch(Exception e){
                        System.out.println("Invalid Expresion");
                    }
                    }
                    case "returnVar" ->{//regresar una variable entre ()
                        Pattern pattern = Pattern.compile("[a-zA-Z0-9]+", Pattern.CASE_INSENSITIVE); //
                        Matcher matcher = pattern.matcher(expresion);
                        if(matcher.find()){
                           if(vars.containsKey(matcher.group())){
                               System.out.println(vars.get(matcher.group()).getValue());
                           }
                        }
                        
                    }
                    case "FUN" ->{//ejecutar una funcion
                        Pattern pattern = Pattern.compile("[(][ ]*(([a-zA-Z0-9]+)[ ]*[(](.+?)[)])[ ]*[)]", Pattern.CASE_INSENSITIVE); //
                        Matcher matcher = pattern.matcher(expresion);
                        if(matcher.find()){
                            if(fun.containsKey(matcher.group(2))){
                                IFunction temp = fun.get(matcher.group(2));
                                Object brake = temp.run(matcher.group(3));
                                System.out.println(String.valueOf(brake));
                            }
                        }
                    }
                    case "HELP" ->{
                        System.out.println("""
                                           los strings se escriben entre ' ' : 'hola'
                                           para declarar una variable (setq name value)
                                           para operaciones aritmeticas, soporta una cantidad de n numeros
                                           solo es de escribir la operacion a realizar, ejemplo (+ 5 5 5 5)
                                           se puede realizar comparaciones enre elementos;
                                           (equal x y) devuelve true siempre que sea el mismo valor y mismo tipo de elemento
                                           (= x y) devuelve true si tienen el mismo valor sin importar el tipo
                                           
                                           para las siguientes expresiones:
                                           devuelve true si se cumple la expresion , solo sirve con numeros,
                                           (=< x y) , (=> x y) (> x y) (< y x)
                                           
                                           para aritmeticas complejas, colocar a la izquierda las operaciones entre parentesis, por ejemplo
                                           (+(- 5 2) 4)
                                           
                                           para imprimir: (print x)
                                           para varaibles e integers, se obtiene de vuelta solo con poner (x)
                                  
                                           """);
                    }
                    
                }
            }else{
                System.out.println("Expresion coulnd't be excecuted correctly");
            }
    
    }
    public synchronized Object functionRunner(String expresion, String result,HashMap<String,Variable> varstemp){
        //metodo usado por funciones recursivas, aca se devuelve el valor en vez de ser mostrado directamente, son las mismas funciones del anterior
     switch(result){
                    case "END" -> {
                        System.out.println("You can't end me, kidding have a nice day");
                        System.exit(0);
                    }
                    case "PRINT" ->{
                        print(expresion);
                    }
                    
                    case "NEWVAR" ->{
                        Variable temp = AF.VariableCreatot(expresion);
                        if(temp != null){
                        varstemp.put(temp.name, temp);
                        }else{
                        return null;
                        }
                    }
                    case "ADD" ->{
                    Integer add = ALU.add(expresion, varstemp);
                    if(add != null){
                       return add;
                    }else{
                        return null;
                    }
                    }
                    case "QUIT" ->{
                       Integer add = ALU.quit(expresion, varstemp);
                        if(add != null){
                            return add;
                        }else{
                            return null;
                        }
                    }
                    
                    case "MUL"->{
                        Integer add= ALU.multi(expresion,varstemp);
                         if(add != null){
                            return add;
                        }else{
                            return null;
                        }
                    }
                    case "DIV" ->{
                        Integer add = ALU.div(expresion,varstemp);
                        if(add != null){
                        System.out.println(add);
                        }else{
                            System.out.println("Error");
                        }
                    }
                    case "QUT" -> {
                        quote(expresion);
                    }
                    case "EVA01" ->{
                        Conditional temp = new Conditional();
                        String tempR = temp.Equals(expresion,varstemp);
                        return tempR;
                        
                    }
                    case "EVA02"->{
                        Conditional temp = new Conditional();
                        String tempR = temp.MoreLess(expresion,varstemp);
                        return tempR;
                    }
                    case "EVA03" ->{
                        Conditional temp = new Conditional();
                        String tempR = temp.morelessEqual(expresion,varstemp);
                        return tempR;
                    
                    }
                    case "EVA04" ->{
                        Conditional temp = new Conditional();
                        String tempR = temp.EqualValue(expresion,varstemp);
                        return tempR;
                    
                    }
                    case "DEFUN" ->{
                        System.out.println("Defun");
                        defun(expresion);
                    }
                    /*
                    Operaciones Aritmeticas entre dos funciones suma, resta, multiplicacion y division entre los resultados de dos funciones
                    */
                    case "ADDFUN" -> {
                        return ALU.funadd(expresion, fun, varstemp, env);
                    }
                    case "QUITFUN" -> {
                        return ALU.funquit(expresion, fun, varstemp, env);
                    }
                    case "MULTIFUN" -> {
                        return ALU.funmulti(expresion, fun, varstemp, env);
                    }
                    case"DIVFUN" -> {
                        return ALU.funmulti(expresion, fun, varstemp, env);
                    }
                    case "returnInt" -> {
                        Pattern pattern = Pattern.compile("[0-9]+", Pattern.CASE_INSENSITIVE); //
                        Matcher matcher = pattern.matcher(expresion);
                        if(matcher.find()){
                            return Integer.valueOf(matcher.group());
                        }else{
                        return null;
                        }
                    }
                    case "COMPLEX?" ->{
                    try{
                        Combinadas complex = new Combinadas();
                        return (complex.evaluate((List) complex.conver(expresion)));
                    }catch(Exception e){
                        return null;
                    }
                    }
                    case "FUN" ->{
                       
                        Pattern pattern = Pattern.compile("[(][ ]*(([a-zA-Z0-9]+)[ ]*[(](.+?)[)])[ ]*[)]", Pattern.CASE_INSENSITIVE); //
                        Matcher matcher = pattern.matcher(expresion);
                        if(matcher.find()){
                            if(fun.containsKey(matcher.group(2))){
                                IFunction temp = fun.get(matcher.group(2));
                                Object p = functionRunner(matcher.group(3), dec.decode(matcher.group(2)),varstemp);
                                return temp.run(result);
                            }
                        }else{
                            return null;
                        }
                    }
                    
                }
         return null;
    }
     private synchronized void print(String expresion){
        expresion = expresion.replaceAll("print", "");
        Pattern pattern = Pattern.compile("[0-9]+", Pattern.CASE_INSENSITIVE); //
	Matcher matcher = pattern.matcher(expresion);
        if(matcher.find()){
            System.out.println(matcher.group().trim());
        }
        pattern = Pattern.compile("['][a-z]+[']", Pattern.CASE_INSENSITIVE); //
        matcher = pattern.matcher(expresion);
        if (matcher.find()) { 
                 String temp = matcher.group().trim();
                 temp = temp.replaceAll("'", "");
                 System.out.println(temp);
        }
        
        pattern = Pattern.compile("[ ]+[a-z]+[ ]*", Pattern.CASE_INSENSITIVE); //
        matcher = pattern.matcher(expresion);
        if(matcher.find()){
            if(vars.containsKey(matcher.group().trim())){
                 System.out.println(vars.get(matcher.group().trim()).getValue());
            }else{
                System.out.println((matcher.group().trim()) + "\t is not defined yet");
            }
        }
    }
     
     private synchronized void quote(String expresion){
        Pattern pattern = Pattern.compile("^[(][ ]*('|quote)[ ]*([(].+[)])[ ]*[)]$", Pattern.CASE_INSENSITIVE); //
         Matcher matcher = pattern.matcher(expresion);
        while(matcher.find()){
            System.out.println(matcher.group(2));
        }
     
     }
     private synchronized void defun(String a){
     Pattern pattern = Pattern.compile("^[(][ ]*defun[ ]+([a-zA-Z0-9]+)[ ]*[(](.+?)[)][ ]*[(](.+?)[)][ ]*[)]$",Pattern.CASE_INSENSITIVE );
        Matcher matcher = pattern.matcher(a);
        String name = "";
        if(matcher.find()){
            System.out.println("YES Declaring");
        /*
        Obtener Nombre
        */
        name = matcher.group(1);
        /*
        Obtener Parametros
        */
        String paramtemp = matcher.group(2);
        String[] params = paramtemp.split(" ");
            System.out.println(params.toString());
        /*
        Obtener Instrucciones
        */
        String body= "(" + matcher.group(3) + ")";
            System.out.println(body);
        /*
        Verficar si es recursiva
        */
        Pattern recursive = Pattern.compile("[(][ ]*t[ ]*([(].+[)])[ ]*[)]",Pattern.CASE_INSENSITIVE );
        Matcher matcherr = recursive.matcher(body);
        if(matcherr.find()){
            String faithJump = matcherr.group(1);
            body = body.replaceAll("[(][ ]*t[ ]*([(].+?[)])[ ]*[)]", "");
            try{
                FunctionRecursive functionRecursive = new FunctionRecursive(name,body,faithJump,params);
                fun.put(name, functionRecursive);
        }catch(Exception e){
            System.out.println("Function can't be declared");
        }
        }
     }
    
    }
}
