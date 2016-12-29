package Templates;

import Main.MainModule;

import java.util.Observable;

/**
 * Created by akash on 12/26/2016.
 */
public class LessonTemplate implements Template  {
    String text;
    public LessonTemplate(String text){
        this.text = text;
    }

    public void display(MainModule main){
        System.out.println(text);
    }
}
