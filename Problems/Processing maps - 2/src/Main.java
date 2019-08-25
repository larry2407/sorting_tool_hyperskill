import java.util.*;

class MapUtils {

    public static NavigableMap<Integer, String> getSubMap(NavigableMap<Integer, String> map){
        int firstKey = map.firstKey();
        int lastKey = map.lastKey();
        NavigableMap<Integer, String> output = (NavigableMap<Integer, String>) (firstKey%2 != 0 ? map.subMap(firstKey, 5+firstKey) :
                        map.subMap(lastKey - 4, lastKey+1));
        return output.descendingMap();
    }

}

/* Do not modify code below */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TreeMap<Integer, String> map = new TreeMap<>();
        Arrays.stream(scanner.nextLine().split("\\s")).forEach(s -> {
            String[] pair = s.split(":");
            map.put(Integer.parseInt(pair[0]),pair[1]);
        });
        NavigableMap<Integer, String> res = MapUtils.getSubMap(map);
        res.forEach((k, v) -> System.out.println(k + " : " + v));
    }
}