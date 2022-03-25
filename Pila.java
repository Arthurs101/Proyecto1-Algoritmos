
import java.util.ArrayList;
import java.util.List;

public class Pila<String> {

    public List<String> coreList;

    public Pila() {
        this.coreList = new ArrayList<>();
    }

    public boolean isEmpty() {
        return coreList.isEmpty();
    }

    public String peek() {
        return coreList.get(0);
    }

    public String pop() {
        return coreList.remove(0);
    }

    public void push(String item) {
        coreList.add(item);
    }

    public int count() {
        return coreList.size();
    }

    public java.lang.String toString() {

        java.lang.String result = "";
        for (String i : coreList) {
            result = result + i;
        }

        return result;
    }
}