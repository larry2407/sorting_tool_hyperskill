import java.util.*;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> map = new TreeMap<>();
        map.put("Gamma",  3);
        map.put("Omega", 24);
        map.put("Alpha",  1);

        for(Map.Entry<String, Integer> pair : map.entrySet() ){
            System.out.println(pair.getKey()+"="+pair.getValue());
        }
    }
}