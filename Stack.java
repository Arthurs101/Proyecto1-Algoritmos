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

public final class Stack implements Cloneable { // Clonbable es una interfaz que nos deja clonar un objeto
    
  
    public Stack() {
      this(new ArrayList());
    }
  // A continuacion se definen los comportamientos del stack


    public boolean isEmpty() {
      return 0 == list.size();
    }
  
    public int size() {
      return list.size();
    }
  
    public void push(Variable o) {
      list.add(o);
    }
  
    public Variable pop() {
      return list.remove(size()-1);
    }
  
    // Encontrar el primer elemento en la lista
    public Variable top() {
      return top(1);
    }
  
    public Variable top(int offset) {
      return list.get(size()-offset);
    }
  
    public void set(Variable o) {
      set(1, o);
    }
  
    public void set(int offset, Variable o) {
      list.set(size()-offset, o);
    }
  
    public Variable clone() {
      return new Stack((ArrayList)list.clone());
    }
  
    private Stack(ArrayList list) {
      this.list = list;
    }
  
    private ArrayList list;
  }
  