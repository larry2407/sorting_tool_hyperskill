import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Set<String> orderedStringSeq = new TreeSet<>();
        int n = Integer.valueOf(sc.nextLine().trim());
        for(int i=0; i<n; i++){
            orderedStringSeq.add(sc.nextLine().trim());
        }
        int l = orderedStringSeq.size();
        for(String elt : orderedStringSeq){
            System.out.println(elt);
        }
    }
}