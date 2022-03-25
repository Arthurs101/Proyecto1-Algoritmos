
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.security.Principal;
import java.util.regex.Pattern;

public class Arimetico {

    public Object convertir(String Expresion) {

        Pila<String> datos = new Pila<>();
        List<String> myList = new ArrayList<String>();
        for (int i = 0; i < Expresion.length(); i++) {

            myList.add(Expresion.charAt(i));

        }
        for (int t; t < myList.size(); t++) {
            if (myList.get(t).equals("(")) {
                myList.set(t, "( ").split(" ");

            } else if (myList.get(t).equals(")")

            ) {
                myList.set(t, " )").split(" ");

            }

            datos.push(myList.get(t));
        }

        return evaluacion(datos);
    }

    public Object evaluacion(Pila<String> datoss) {
        String Key = datoss.pop();

        if (Key.equals("(")) {
            List Listaa = new ArrayList();

            while (!datoss.peek().equals(")")) {
                Listaa.add(evaluacion(datoss));
            }
            datoss.pop();
            return Listaa;
        } else {
            return tipoDeDato(Key);
        }
    }

    public Object tipoDeDato(String Key) {
        Pattern patron = Pattern.compile("[0-9]+");
        Matcher matcher = patron.matcher(Key);
        boolean resultado = matcher.matches();
        if (resultado) {
            try {
                return Integer.parseDouble(Key);
            } catch (Exception e) {
            }
            try {
                return Double.parseDouble(Key);
            } catch (Exception e) {
            }
        } else {
            return Key;
        }
        return null;

    }

    public Object evaluate(List list) {// necesariamente existe una lista

        while (list.size() > 0) {
            Object primero = list.remove(0);// verifica que operador es, en caso de hallar un String

            if (primero instanceof String) {
                Double Z = 0.0;
                Object principal;
                // objeto para comprobar que sea del tipo de dato

                switch (primero.toString()) {

                    case "+": {

                        principal = list.remove(0);// se ve el primer elemento y se elimina
                        if (principal instanceof List) {// ahora comprueba que se trata o no de una lista
                            principal = evaluate((List) principal);// recursivo
                            Z = (Double) principal;
                        } else if (principal instanceof Number) {
                            Z = (Double) principal;// si es un numero
                        }
                        while (list.size() > 0) {
                            Object x = list.remove(0);// va eliminando, en este caso el anterior

                            if (x instanceof Number) {
                                Z = Z + (Double) x;
                            } else if (x instanceof List) {
                                Z = Z + (Double) evaluate((List) x);
                            }
                        }

                        return Z;
                    }

                    case "-": {
                        Z = 0.0;

                        Object x = list.remove(0);

                        if (x instanceof List) {
                            x = evaluate((List) x);
                            Z = (Double) x;
                        } else if (x instanceof Number) {
                            Z = (Double) x;
                        }

                        while (list.size() > 0) {
                            principal = list.remove(0);

                            if (principal instanceof Number) {
                                Z -= (Double) principal;

                            } else if (principal instanceof List) {
                                Z -= (Double) evaluate((List) principal);
                            }
                        }

                        return Z;
                    }

                    case "*": {
                        Z = 1.0;

                        principal = list.remove(0);

                        if (principal instanceof List) {
                            principal = evaluate((List) principal);
                            Z = (Double) principal;
                        } else if (principal instanceof Number) {
                            Z = (Double) principal;
                        }

                        while (list.size() > 0) {
                            Object x = list.remove(0);

                            if (x instanceof Number) {
                                Z *= (Double) x;

                            } else if (x instanceof List) {
                                Z *= (Double) evaluate((List) x);
                            }
                        }

                        return Z;
                    }

                    case "/": {
                        Z = 1.0;
                        Object x = list.remove(0);

                        if (x instanceof List) {
                            x = evaluate((List) x);
                            Z = (Double) x;
                        } else if (x instanceof Number) {
                            Z = (Double) x;
                        }

                        while (list.size() > 0) {
                            principal = list.remove(0);

                            if (principal instanceof Number) {
                                Z /= (Double) principal;

                            } else if (principal instanceof List) {
                                Z /= (Double) evaluate((List) x);
                            }
                        }

                        return Z;
                    }

                }
            }
        }
        return list;
    }
}
