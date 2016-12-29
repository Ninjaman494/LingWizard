package Templates;

import Main.LessonSelectionEvent;
import Main.MainModule;
import Main.UnitSelectionEvent;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by akash on 12/28/2016.
 */
public class LessonListTemplate implements Template{
    ArrayList<String> lessons;
    public LessonListTemplate(ArrayList<String> lessons){
        this.lessons = lessons;
    }

    public void display(MainModule main){
        Scanner input = new Scanner(System.in);
        for(String s: lessons) {
            System.out.println(s);
        }
        int choice = input.nextInt();
        main.handleEvent(new LessonSelectionEvent(this,lessons.get(choice-1)));
    }
}
