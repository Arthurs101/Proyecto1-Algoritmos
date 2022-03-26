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
import java.util.Scanner;

public class Main {
    static Scanner me = new Scanner(System.in);
    static Decoder decode = new Decoder();
    static Enviroment env = Enviroment.getInstance();
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    System.out.println("""
                         ___    __           __       
                         / _ |  / /    ___   / /  ___ _
                        / __ | / /__  / _ \\ / _ \\/ _ `/
                       /_/ |_|/____/ / .__//_//_/\\_,_/ 
                                    /_/                """);
    System.out.println("Lisp Interpreter");
    System.out.println("System ready");
    System.out.println("To exit type (end)");
    System.out.println("for helo type -h");

    while(true){
        String expresion = me.nextLine();//leer linea
        String result = decode.decode(expresion);//decodificar
        env.excecute(expresion, result);//ejecutar
            
    }
    
        
    
    
    }
}
