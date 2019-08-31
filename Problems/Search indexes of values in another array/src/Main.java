import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static int[] searchIndexes(int[] first, int[] second) {
        int l=first.length;
        int[] ans = new int[l];
        for(int i=0; i<l; i++){
            ans[i] = getFirstOccurrenceIndex(second, first[i]);
        }
        return ans;
    }

    public static int getFirstOccurrenceIndex(int[] array, int val){
        int index=-1;
        int l=array.length;
        for(int i=0; i<l; i++){
            if(val==array[i]){
                index=i;
                break;
            }
        }
        return index;
    }

    /* Do not change code below */
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int[] first = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        final int[] second = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        final String result = Arrays.toString(searchIndexes(first, second))
                .replace(", ", " ")
                .replace("[", "")
                .replace("]", "");
        System.out.println(result);
    }
}