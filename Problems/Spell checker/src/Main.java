import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Locale;

class Main {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Set<String> knownWords = new HashSet<>();
        Set<String> wordsInText = new HashSet<>();
        int d = Integer.valueOf(sc.nextLine().trim());
        for(int i=0; i<d; i++){
            knownWords.add(sc.nextLine().trim());
        }
        int t = Integer.valueOf(sc.nextLine().trim());
        for(int i=0; i<t; i++){
            String[] currentLineSplit =sc.nextLine().trim().split(" ");
            int currentL = currentLineSplit.length;
            for(int j=0; j<currentL; j++){
                wordsInText.add(currentLineSplit[j]);
            }
        }
        List<String> answer = getWordsNotInDictionary(wordsInText, knownWords);
        // for(int k=0; k<answer.size(); k++){
        //     System.out.println(answer.get(k));
        // }
        for(String ans : answer){
            System.out.println(ans);
        }
    }

    private static List<String> getWordsNotInDictionary(Set<String> text, Set<String> dictionary){
        List<String> found = new ArrayList<>();
        for(String word : text){
            if(!isWordInDictionary(word, dictionary)){
                found.add(word);
            }
        }
        return found;
    }

    private static boolean isWordInDictionary(String word, Set<String> dictionary){
        for(String s : dictionary){
            if(s.toLowerCase(Locale.ENGLISH).equals(word.toLowerCase(Locale.ENGLISH))){
                return true;
            }
        }
        return false;
    }
}
