package sorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    //private static Scanner sc = new Scanner(System.in);

    public static void main(final String[] args) {
        //Scanner scanner = new Scanner(System.in);
        String[] choicesAndMsg = checkInputValidity(args);
        if(!choicesAndMsg[2].isEmpty()){
            System.out.println(choicesAndMsg[2]);
            return;
        }
        String[] choice = new String[]{choicesAndMsg[0], choicesAndMsg[1], choicesAndMsg[3], choicesAndMsg[4]};

        //choice= args[1];
        List<Object> input = new ArrayList<>();
        input = getInput(choice[0], choice[2]);
        treatInput(input, choice);
    }

    private static void treatInput(List<Object> input, String choice, String outFile) {
        boolean isOutPutInAFile = !outFile.isEmpty();
        String strForFileOut="";
        File fileOut = null;
        if(isOutPutInAFile){
            fileOut = new File(outFile);
        }
        int count = input.size();
        int countMax = 0;
        if (choice.equals("long")) {
            long maxLong = Long.MIN_VALUE;
            List<Long> recordLongMaxes = new ArrayList<>();
            for (Object x : input) {
                long n = (long) x;
                if (n > maxLong) {
                    maxLong = n;
                }
                if (n == maxLong) {
                    recordLongMaxes.add(n);
                }
            }
            for (Long y : recordLongMaxes) {
                if (y == maxLong) {
                    countMax++;
                }
            }
            int asPercent = 100 * countMax / count;
            if(outFile.isEmpty()) {
                System.out.println(String.format("Total numbers: %d.\nThe greatest number: %d (%d time(s), %d%%).", count, maxLong, countMax, asPercent));
            }else{
                strForFileOut=String.format("Total numbers: %d.\nThe greatest number: %d (%d time(s), %d%%).", count, maxLong, countMax, asPercent)+"\n";
            }
        } else if (choice.equals("word") || choice.isEmpty() || choice.equals("line")) {
            String maxStr = "";
            List<String> recordStringMaxes = new ArrayList<>();
            for (Object x : input) {
                String w = (String) x;
                if (w.length() > maxStr.length()) {
                    maxStr = w;
                } else if (w.length() == maxStr.length()) {
                    if (w.compareTo(maxStr) > 0) {
                        maxStr = w;
                    }
                }
                if (w.compareTo(maxStr) == 0) {
                    recordStringMaxes.add(w);
                }
            }
            for (String w_max : recordStringMaxes) {
                if (w_max.compareTo(maxStr) == 0) {
                    countMax++;
                }
            }
            int asPercent = 100 * countMax / count;
            if (choice.equals("word") || choice.isEmpty()) {
                if(outFile.isEmpty()) {
                    System.out.println(String.format("Total words: %d.\nThe longest word: %s (%d time(s), %d%%).", count, maxStr, countMax, asPercent));
                }else{
                    strForFileOut+=String.format("Total words: %d.\nThe longest word: %s (%d time(s), %d%%).", count, maxStr, countMax, asPercent)+"\n";
                }
            } else {
                if(outFile.isEmpty()) {
                    System.out.println(String.format("Total lines: %d.\nThe longest line:\n%s\n(%d time(s), %d%%).", count, maxStr, countMax, asPercent));
                }else{
                    strForFileOut+=String.format("Total lines: %d.\nThe longest line:\n%s\n(%d time(s), %d%%).", count, maxStr, countMax, asPercent)+"\n";
                }

            }

        } else if (choice.equals("sortInt")) {
            List<Long> longListSorted = new ArrayList<>();
            for (Object inpt : input) {
                longListSorted.add((Long) inpt);
            }
            Collections.sort(longListSorted);
            if(outFile.isEmpty()) {
                System.out.println(String.format("Total numbers: %d.", count));
                System.out.println("Sorted data: " + longListSorted.toString().replaceAll("[,\\[\\]]", ""));
            }else{
                strForFileOut+= String.format("Total numbers: %d.", count)+"\n"+"Sorted data: " + longListSorted.toString().replaceAll("[,\\[\\]]", "")+"\n";
            }
        } else if (choice.equals("sortStr")) {
            List<String> stringListSorted = new ArrayList<>();
            for (Object inpt : input) {
                stringListSorted.add((String) inpt);
            }
            Collections.sort(stringListSorted);
            if(outFile.isEmpty()) {
                System.out.println(String.format("Total numbers: %d.", count));
                System.out.println("Sorted data: " + stringListSorted.toString().replaceAll("[,\\[\\]]", ""));
            }else{
                strForFileOut+=String.format("Total numbers: %d.", count)+"\n"+"Sorted data: " + stringListSorted.toString().replaceAll("[,\\[\\]]", "")+"\n";
            }
        }
        if(isOutPutInAFile){
            try (FileWriter writer = new FileWriter(fileOut)) {
                writer.write(strForFileOut);
            } catch (IOException e) {
                System.out.printf("An exception occurred %s", e.getMessage());
            }
        }
    }

    private static void treatInput(List<Object> input, String[] choice) {
        int count = input.size();
        int countMax = 0;
        boolean isOutPutInAFile = !choice[3].isEmpty();
        String strForFileOut="";
        File fileOut = null;
        if(isOutPutInAFile){
             fileOut = new File(choice[3]);
        }
        if (choice[0].equals("long")) {
            switch (choice[1]) {
                case "byCount":
                    TreeMultiset<Long> longMapper = new TreeMultiset<>();
                    for (Object obj : input) {
                        longMapper.add((Long) obj);
                    }
                    if(choice[3].isEmpty()) {
                        System.out.println(String.format("Total numbers: %d.", count));
                    }else{
                        strForFileOut=String.format("Total numbers: %d.", count)+"\n";
                    }
                    List<Integer> sortedValueList = new ArrayList<>(longMapper.getMap().values());
                    Collections.sort(sortedValueList);
                    List<Long> longList = new ArrayList<>(longMapper.toSet());
                    Set<Integer> usedIndexes = new HashSet<>();
                    for (Integer val : sortedValueList) {
                        for (int i = 0; i < longList.size(); i++) {
                            Long lm = longList.get(i);
                            int currentFreq = longMapper.getMultiplicity(lm);
                            if (currentFreq == val && !usedIndexes.contains(i)) {
                                usedIndexes.add(i);
                                int asPercent = 100 * currentFreq / count;
                                if(choice[3].isEmpty()) {
                                    System.out.println(lm + ": " + currentFreq + " time(s), " + asPercent + "%");
                                }else{
                                    strForFileOut+= lm + ": " + currentFreq + " time(s), " + asPercent + "%"+"\n";
                                }
                                break;
                            }
                        }
                    }
                    if(isOutPutInAFile){
                        try (FileWriter writer = new FileWriter(fileOut)) {
                            writer.write(strForFileOut);
                        } catch (IOException e) {
                            System.out.printf("An exception occurred %s", e.getMessage());
                        }
                    }
/*
                    for(Long lm : longMapper.toSet()){
                        int currentFreq = longMapper.getMultiplicity(lm);
                        int asPercent = 100 * currentFreq / count;
                        System.out.println(lm+": "+currentFreq+" time(s), "+asPercent+"%");
                    }
*/
                    break;
                case "natural":
                    treatInput(input, "sortInt", choice[3]);
                    break;
                default:
                    System.out.println("Wrong choice!");
                    break;
            }
        } else {
            switch (choice[1]) {
                case "byCount":
                    TreeMultiset<String> longMapper = new TreeMultiset<>();
                    for (Object obj : input) {
                        longMapper.add((String) obj);
                    }
                    if(choice[3].isEmpty()) {
                        System.out.println(String.format("Total numbers: %d.", count));
                    }else{
                        strForFileOut=String.format("Total numbers: %d.", count)+"\n";
                    }
                    List<Integer> sortedValueList = new ArrayList<>(longMapper.getMap().values());
                    Collections.sort(sortedValueList);
                    List<String> stringList = new ArrayList<>(longMapper.toSet());
                    Set<Integer> usedIndexes = new HashSet<>();
                    for (Integer val : sortedValueList) {
                        for (int i = 0; i < stringList.size(); i++) {
                            String lm = stringList.get(i);
                            int currentFreq = longMapper.getMultiplicity(lm);
                            if (currentFreq == val && !usedIndexes.contains(i)) {
                                usedIndexes.add(i);
                                int asPercent = 100 * currentFreq / count;
                                if(choice[3].isEmpty()) {
                                    System.out.println(lm + ": " + currentFreq + " time(s), " + asPercent + "%");
                                }else{
                                    strForFileOut+=lm + ": " + currentFreq + " time(s), " + asPercent + "%"+"\n";
                                }
                                break;
                            }
                        }
                    }
                    if(isOutPutInAFile){
                        try (FileWriter writer = new FileWriter(fileOut)) {
                            writer.write(strForFileOut);
                        } catch (IOException e) {
                            System.out.printf("An exception occurred %s", e.getMessage());
                        }
                    }
                    /*
                    for(String sm : longMapper.toSet()){
                        int currentFreq = longMapper.getMultiplicity(sm);
                        int asPercent = 100 * currentFreq / count;
                        System.out.println(sm+": "+currentFreq+" time(s), "+asPercent+"%");
                    }*/
                    break;
                case "natural":
                    treatInput(input, "sortStr", choice[3]);
                    break;
                default:
                    System.out.println("Wrong choice!");
                    break;
            }

        }

    }

    private static List<Object> getInput(String choice, String inputFile) {
        Scanner sc = null;
        if(inputFile.isEmpty()) {
             sc = new Scanner(System.in);
        }else{
           File f = new File(inputFile);
            try {
                sc = new Scanner(f);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        List<Object> ans = new ArrayList<>();
        if (choice.equals("long") || choice.equals("sortInt")) {
            while (sc.hasNextLong()) {
                ans.add(sc.nextLong());
            }
        } else if (choice.equals("word") || choice.isEmpty()) {
            String[] currentWordsOnLine;
            while (sc.hasNextLine()) {
                currentWordsOnLine = sc.nextLine().split("\\s+");
                for (String w : currentWordsOnLine) {
                    ans.add(w);
                }
            }
        } else if (choice.equals("line")) {
            while (sc.hasNextLine()) {
                ans.add(sc.nextLine());
            }
        } else {
            ans = null;
        }
        sc.close();
        return ans;
    }

    private static String[] checkInputValidity(String[] args){
        String[] answer = new String[]{"", "natural", "", "", ""}; // 0 and 1 is choices and 2 is message
        // 3 inputFile and 4 outputFile
        Set<String> allowedDataType = new HashSet<>(Arrays.asList("long", "word", "line"));
        Set<String> allowedSortingType = new HashSet<>(Arrays.asList("natural", "byCount", ""));
        int lengthArgs = args.length;
        for (int i = 0; i < lengthArgs; i++) {
            if (args[i].equals("-sortIntegers")) {
                answer[0] = "sortInt";
                break;
            }
            if (args[i].equals("-dataType")) {
                if(i+1<= lengthArgs-1 && null !=args[i+1] && !args[i + 1].isEmpty() ) {
                    answer[0] = args[i + 1];
                    if(!allowedDataType.contains(answer[0])){
                        answer[2] += "\""+answer[0]+" \"isn't a valid parameter. It's skipped.";
                    }

                }else{
                    answer[2] += "No data type defined!";
                }
            }
            if (args[i].equals("-sortingType")) {
                if(i+1<= lengthArgs-1 && null !=args[i+1] ) {
                    answer[1] = !args[i + 1].isEmpty() ? args[i + 1] : answer[1];
                    if(!allowedSortingType.contains(answer[1])){
                        answer[2] += "\""+answer[1]+" \"isn't a valid parameter. It's skipped.";
                    }
                }else{
                    answer[2] += "No sorting type defined!";
                }
            }
//            if(!allowedDataType.contains(args[i]) && !allowedSortingType.contains(args[i]) &&
//                    !args[i].equals("-sortingType") && args[i].equals("-dataType")){
//                answer[2] += "\""+answer[1]+"\"isn't a valid parameter. It's skipped.";
//            }
            if (args[i].equals("-inputFile")) {
                if(i+1<= lengthArgs-1 && null !=args[i+1] ) {
                    answer[3] = !args[i + 1].isEmpty() ? args[i + 1] : answer[3];
                }
            }
            if (args[i].equals("-outputFile")) {
                if(i+1<= lengthArgs-1 && null !=args[i+1] ) {
                    answer[4] = !args[i + 1].isEmpty() ? args[i + 1] : answer[4];
                }
            }

        }
        return answer;
    }

}


