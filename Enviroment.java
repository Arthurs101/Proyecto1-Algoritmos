
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
     private static Enviroment env;// variable estatica
    private Enviroment(){
    
    }
    public synchronized static Enviroment getInstance() {
        if (env == null) {
            env = new Enviroment();
        }
        return env;
    }
    
    
    public synchronized void excecute(String expresion, String result){
    if(result != null){
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
                        vars.put(temp.name, temp);
                            System.out.println("Variable " + temp.name + " created correctly");
                        }
                    }
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
                    case "EVA01" ->{
                        Conditional temp = new Conditional();
                        String tempR = temp.Equals(expresion, vars);
                        if(tempR != null){
                            System.out.println(tempR);
                        }
                    }
                    case "EVA02"->{
                        Conditional temp = new Conditional();
                        String tempR = temp.MoreLess(expresion, vars);
                        if(tempR != null){
                            System.out.println(tempR);
                        }
                    }
                    case "EVA03" ->{
                        Conditional temp = new Conditional();
                        String tempR = temp.morelessEqual(expresion, vars);
                        if(tempR != null){
                            System.out.println(tempR);
                        }
                    
                    }
                    case "EVA04" ->{
                        Conditional temp = new Conditional();
                        String tempR = temp.EqualValue(expresion, vars);
                        if(tempR != null){
                            System.out.println(tempR);
                        }
                    
                    }
                    case "DEFUN" ->{
                        System.out.println("Defun");
                        defun(expresion);
                    }
                    case "ADDFUN" -> {
                    
                    }
                    case "QUITFUN" -> {
                    
                    }
                    case "MULTIFUN" -> {
                    
                    }
                    case"DIVFUN" -> {
                    
                    }
                    case "returnInt" -> {
                        Pattern pattern = Pattern.compile("[0-9]+", Pattern.CASE_INSENSITIVE); //
                        Matcher matcher = pattern.matcher(expresion);
                        if(matcher.find()){
                            System.out.println(matcher.group().trim());
                        }
                    }
                    case "COMPLEX?" ->{
                    try{
                        Combinadas complex = new Combinadas();
                        System.out.println(complex.evaluate((List) complex.conver(expresion)));
                    }catch(Exception e){
                        System.out.println("Invalid Expresion");
                    }
                    }
                    
                }
            }else{
                System.out.println("Expresion coulnd't be excecuted correctly");
            }
    
    }
    public synchronized Object functionRunner(String expresion, String result,HashMap<String,Variable> varstemp){//metodo usado por funciones recursivas
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
                        vars.put(temp.name, temp);
                            System.out.println("Variable " + temp.name + " created correctly");
                        }
                    }
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
                    case "EVA01" ->{
                        Conditional temp = new Conditional();
                        String tempR = temp.Equals(expresion, vars);
                        if(tempR != null){
                            System.out.println(tempR);
                        }
                    }
                    case "EVA02"->{
                        Conditional temp = new Conditional();
                        String tempR = temp.MoreLess(expresion, vars);
                        if(tempR != null){
                            System.out.println(tempR);
                        }
                    }
                    case "EVA03" ->{
                        Conditional temp = new Conditional();
                        String tempR = temp.morelessEqual(expresion, vars);
                        if(tempR != null){
                            System.out.println(tempR);
                        }
                    
                    }
                    case "EVA04" ->{
                        Conditional temp = new Conditional();
                        String tempR = temp.EqualValue(expresion, vars);
                        if(tempR != null){
                            System.out.println(tempR);
                        }
                    
                    }
                    case "DEFUN" ->{
                        System.out.println("Defun");
                        defun(expresion);
                    }
                    case "ADDFUN" -> {
                    
                    }
                    case "QUITFUN" -> {
                    
                    }
                    case "MULTIFUN" -> {
                    
                    }
                    case"DIVFUN" -> {
                    
                    }
                    case "returnInt" -> {
                        Pattern pattern = Pattern.compile("[0-9]+", Pattern.CASE_INSENSITIVE); //
                        Matcher matcher = pattern.matcher(expresion);
                        if(matcher.find()){
                            System.out.println(matcher.group().trim());
                        }
                    }
                    case "COMPLEX?" ->{
                    try{
                        Combinadas complex = new Combinadas();
                        System.out.println(complex.evaluate((List) complex.conver(expresion)));
                    }catch(Exception e){
                        System.out.println("Invalid Expresion");
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
        String body = matcher.group(3);
            System.out.println(body);
        /*
        Verficar si es recursiva
        */
        Pattern recursive = Pattern.compile("[(][ ]*t[ ]*([(].+?[)])[ ]*[)]",Pattern.CASE_INSENSITIVE );
        Matcher matcherr = recursive.matcher(body);
        if(matcherr.find()){
            String faithJump = matcherr.group();
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
