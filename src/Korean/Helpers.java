package Korean;

import java.text.Normalizer;
import java.util.ArrayList;

/**
 * A class containing various "helper" methods that are used to simplify calculations in other parts of this language package
 *
 * Created by Akash Eldo on 12/23/2016.
 */
public class Helpers {

    /**
     * Enums for Consonants and Vowels in Hangul. Each enum is assigned a list of Unicode characters for its letters.
     * NOTE: CONSONANT has two characters for some letters because there are separate Unicode characters for a consonant
     * depending on if it was in the beginning or end of a syllable.
     */
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

    /**
     * Returns a list of the letters in a syllable through Unicode Normalization.
     * NOTE: The characters for the consonants will be different depending on the letter's position in the syllable. See
     * the letterTypes docstring for more info.
     *
     * @param block The syllable to deconstruct
     * @return An ArrayList containing the letters that make up the syllable
     */
    public static ArrayList<Character> deconstruct(String block){
        String deconstructedBlock = Normalizer.normalize(block, Normalizer.Form.NFD);
        ArrayList<Character> letters = new ArrayList<>();
        for(int i = 0;i<deconstructedBlock.length();i++) {
            letters.add(deconstructedBlock.charAt(i));
        }
        return letters;
    }

    /**
     * Returns the type(Consonant or Vowel) of a letter with a letterTypes enum
     * @param letter the letter whose type to return
     * @return An enum corresponding to the letter's type
     */
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

    /**
     * Returns the last letter of a syllable. This method uses deconstruct to break down the syllable and simply returns
     * the last characters in the list.
     *
     * @param block the syllable whose last letter to return
     * @return A character that is the last letter of the syllable
     */
    public static char getLastLetter(String block){
        ArrayList<Character> letters = deconstruct(block);
        return letters.get(letters.size()-1);
    }

    /**
     * A simple test that shows how the helper methods can be used for grammatical checks
     * @param args command line arguments
     */
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
