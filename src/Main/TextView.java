package Main;

import Templates.Template;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by akash on 12/25/2016.
 */
public class TextView {

    Scanner input = new Scanner(System.in);
    MainModule model;
    public TextView(MainModule m){
        model = m;
        this.display(model.getLangs());
    }

    public void pickTemplate(Template template){
        template.display(model);
    }

    /*These templates will likely be standalone classes in the actual view. I included them in this class because making a
       separate class for each template seemed unnecessary when they are simple text interfaces.
     */

    public void Game2Template(String Question,ArrayList Options){
        System.out.println("Game 2: Multiple Choice");
        System.out.println("Question: "+Question);
        System.out.println("Options: ");
        for(int i =1;i<=Options.size();++i) {
            System.out.println(i+": "+Options.get(i-1));
        }
    }

    public void Game3Template(String Question){
        System.out.println("Game 3:Translate");
        System.out.println("Sentence to Translate: ");
    }

    public void display(ArrayList<String> langs) {
        System.out.println("----LingWizard----");
        for (int i = 1; i <= langs.size(); i++) {
            System.out.println(i + ": " + langs.get(i - 1));
        }
        int choice = input.nextInt();
        model.handleEvent(new LanguageSelectionEvent(this,langs.get(choice-1)));
    }
}
