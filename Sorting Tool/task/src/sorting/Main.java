package sorting;

import java.util.*;

public class Main {
    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);
        long max = Long.MIN_VALUE;
        int count = 0;
        int countMax = 0;
        List<Long> recordMaxes = new ArrayList<>();
        while (scanner.hasNextLong()) {
            long number = scanner.nextLong();
            count++;
            max = number > max ? number : max;
            if(number==max){
                recordMaxes.add(number);
            }
        }
        for(Long l_max : recordMaxes){
           if(l_max==max){
               countMax++;
           }
        }
        presentResults(max, count, countMax);
    }

    private static void presentResults(long max, int count, int countMax) {
        System.out.println(String.format("Total numbers: %d.\nThe greatest number: %d (%d time(s)).", count, max, countMax));
    }
}
