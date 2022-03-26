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
/*
Clase referente a una variable
*/
public class Variable<T> {
    private T content;
    public String name;
    
    public Variable(T content, String name){
        this.content = content;
        this.name = name;
    }
    /*
    regresa el tipo del valor contenido
    */
    public Class ContentType(){
        return content.getClass();
    
    }
    public T getValue(){
        return content;
    }
    
}
