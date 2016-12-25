package Korean;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by akash on 12/23/2016.
 */
public class Helpers {
    public enum letterTypes{
        CONSONANT(new char[]{'ᄀ','ᄁ','ᄂ','ᄃ',	'ᄄ'	,'ᄅ','ᄆ','ᄇ','ᄈ','ᄉ','ᄊ','ᄋ','ᄌ','ᄍ','ᄎ','ᄏ','ᄐ','ᄑ','ᄒ',
                             'ᆨ','ᆩ','ᆪ','ᆫ','ᆬ','ᆭ','ᆮ','ᆯ','ᆰ','ᆱ','ᆲ','ᆳ','ᆴ','ᆵ','ᆶ','ᆷ','ᆸ','ᆹ','ᆺ','ᆻ','ᆼ','ᆽ','ᆾ','ᆿ','ᇀ','ᇁ','ᇂ'}),
        VOWEL(new char[]{'ᅡ','ᅢ','ᅣ','ᅤ','ᅥ','ᅦ','ᅧ','ᅨ','ᅩ','ᅪ','ᅫ','ᅬ','ᅭ','ᅮ','ᅯ'});

        private ArrayList<Character> letters;
        letterTypes(char[] l){
            this.letters = new ArrayList();
            for(char z:l){
                letters.add(z);
            }
        }
    }

    public static ArrayList<Character> deconstruct(String block){
        String deconstructedBlock = Normalizer.normalize(block, Normalizer.Form.NFD);
        ArrayList<Character> letters = new ArrayList<>();
        for(int i = 0;i<deconstructedBlock.length();i++) {
            letters.add(deconstructedBlock.charAt(i));
        }
        return letters;
    }

    public static letterTypes getType(char letter){
        if(letterTypes.CONSONANT.letters.contains(letter)) {
            return letterTypes.CONSONANT;
        }
        else if(letterTypes.VOWEL.letters.contains(letter)) {
            return letterTypes.VOWEL;
        }
        else {
            return null;
        }
    }
    public static char getLastLetter(String block){
        ArrayList<Character> letters = deconstruct(block);
        return letters.get(letters.size()-1);
    }
    public static void main(String[] args) {
        String syllable = "갌";
        System.out.println("Syllable:"+syllable);
        if(getType((getLastLetter(syllable)))==letterTypes.VOWEL) {
            System.out.println("Last letter is a vowel");
        }
        else if(getType((getLastLetter(syllable)))==letterTypes.CONSONANT) {
            System.out.println("Last letter is a consonant");
        }

    }
}
