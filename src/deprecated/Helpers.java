package deprecated;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *  The following methods are now obsolete. Originally the last letter was found by comparing the block to lists
 *  of blocks, each list had blocks that ended with a specific letter. Since the program is now using Unicode
 *  normalization, these methods are no longer needed.
 */
public class Helpers {

    public static ArrayList<Character> lastLetterGen(int StartPoint){
        int EndPoint = 55203;
        ArrayList<Character> blocks = new ArrayList<>();
        blocks.add((char)StartPoint);
        int i = StartPoint;
        while(i<EndPoint) {
            i+=6;
            blocks.add((char)i);
            i+=22;
            blocks.add((char)i);
        }
        return blocks;
    }

    public static ArrayList<Character> lastLetterGen2(int StartPoint){
        int EndPoint = 55203;
        ArrayList<Character> blocks = new ArrayList<>();
        blocks.add((char)StartPoint);
        int i = StartPoint;
        while(i<EndPoint) {
            i+=28;
            blocks.add((char)i);
        }
        return blocks;
    }

    public static char LastLetterIs(char block){
        HashSet<String> letterLists = new HashSet();
        letterLists.add("ᄀ");letterLists.add("ᄁ");letterLists.add("ᄂ");letterLists.add("ᄃ");letterLists.add("ᄅ");
        letterLists.add("ᄆ");letterLists.add("ᄇ");
        for(String filename : letterLists){
            char[]  syllables = new char[800];
            BufferedReader file = null;
            try {
                file = new BufferedReader(new FileReader(filename));
                int i = 0;
                String letter;
                while((letter = file.readLine())!=null) {
                    syllables[i] = letter.toCharArray()[0];
                    i++;
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            for(char syllable : syllables) {
                if(block==syllable) {
                    return filename.toCharArray()[0];
                }
            }
        }
        return '?';
    }
    public static void fileWriter(int startpt,int letter) {
        System.out.println("Start point: "+(char)startpt);
        System.out.println("End point: "+(char)55203);
        String filename = (char)letter+"";
        System.out.println("Writing to file: "+filename);
        ArrayList letters = lastLetterGen(startpt);
        try
        {
            PrintWriter pr = new PrintWriter(filename);

            for (int i=0; i<letters.size() ; i++)
            {
                pr.println(letters.get(i));
            }
            pr.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("No such file exists.");
        }
    }
}
