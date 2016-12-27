package Templates;

import Main.MainModule;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by akash on 12/26/2016.
 */
public class Game1Template extends Observable implements Template  {
    String Sentence;
    ArrayList Options;
    public Game1Template(String Sentence, ArrayList Options){
        this.Sentence = Sentence;
        this.Options = Options;
    }
    public void display(MainModule main){
        System.out.println("Game 1: Fill in the Blank");
        System.out.println("Sentence: "+Sentence);
        System.out.println("Options: ");
        for(int i =1;i<=Options.size();++i) {
            System.out.println(i+": "+Options.get(i-1));
        }
    }
}
