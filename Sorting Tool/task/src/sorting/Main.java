package sorting;

import java.util.*;

public class Main {

    //private static Scanner sc = new Scanner(System.in);

    public static void main(final String[] args) {
        //Scanner scanner = new Scanner(System.in);
        String choice ="";
        int lengthArgs = args.length;
        for(int i=0; i<lengthArgs; i++){
            if(args[i].equals("-sortIntegers")){
                choice="sortInt";
                break;
            }else if(args[i].equals("-dataType")){
                choice=args[i+1];
            }
        }
       //choice= args[1];
        List<Object> input = new ArrayList<>();
        input = getInput(choice);
        treatInput(input, choice);
    }

    private static void treatInput(List<Object> input, String choice) {
        int count = input.size();
        int countMax = 0;
        if(choice.equals("long")){
            long maxLong = Long.MIN_VALUE;
            List<Long> recordLongMaxes = new ArrayList<>();
            for(Object x : input){
                long n = (long)x;
                if(n>maxLong){
                    maxLong = n;
                }
                if(n==maxLong){
                    recordLongMaxes.add(n);
                }
            }
            for(Long y : recordLongMaxes){
                if(y==maxLong){
                    countMax++;
                }
            }
            int asPercent = 100*countMax/count;
            System.out.println(String.format("Total numbers: %d.\nThe greatest number: %d (%d time(s), %d%%).", count, maxLong, countMax, asPercent));
        }else if(choice.equals("word") || choice.isEmpty() || choice.equals("line")){
            String maxStr = "";
            List<String> recordStringMaxes = new ArrayList<>();
            for(Object x : input){
                String w = (String)x;
                if(w.length()>maxStr.length()){
                    maxStr = w;
                }else if(w.length() == maxStr.length()){
                    if(w.compareTo(maxStr) > 0){
                        maxStr = w;
                    }
                }
                if(w.compareTo(maxStr) == 0){
                    recordStringMaxes.add(w);
                }
            }
            for(String w_max : recordStringMaxes){
                if(w_max.compareTo(maxStr)==0){
                    countMax++;
                }
            }
            int asPercent = 100*countMax/count;
            if(choice.equals("word") || choice.isEmpty()) {
                System.out.println(String.format("Total words: %d.\nThe longest word: %s (%d time(s), %d%%).", count, maxStr, countMax, asPercent));
            }else{
                System.out.println(String.format("Total lines: %d.\nThe longest line:\n%s\n(%d time(s), %d%%).", count, maxStr, countMax, asPercent));

            }

        }else if(choice.equals("sortInt")){
            List<Long> longListSorted = new ArrayList<>();
            for(Object inpt : input){
                longListSorted.add((Long) inpt);
            }
            Collections.sort(longListSorted);
            System.out.println(String.format("Total numbers: %d.", count));
            System.out.println("Sorted data: "+longListSorted.toString().replaceAll("[,\\[\\]]",""));
        }

    }

    private static List<Object> getInput(String choice) {
        Scanner sc = new Scanner(System.in);
        List<Object> ans = new ArrayList<>();
        if (choice.equals("long") || choice.equals("sortInt")) {
            while (sc.hasNextLong()) {
                ans.add(sc.nextLong());
            }
        }else if(choice.equals("word") || choice.isEmpty()){
            String[] currentWordsOnLine;
            while(sc.hasNextLine()){
                currentWordsOnLine = sc.nextLine().split("\\s+");
                for(String w:currentWordsOnLine){
                    ans.add(w);
                }
            }
        }else if(choice.equals("line")){
            while(sc.hasNextLine()){
                     ans.add(sc.nextLine());
                }
        }else{
            ans = null;
        }
        sc.close();
        return ans;
    }

}


