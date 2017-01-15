package example.LingWizard.XML;

import android.os.Parcelable;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by akash on 1/7/2017.
 */
@Root
public class LessonPlan{
    @Attribute
    String headertext;
    @ElementList(type=Section.class,entry="Section",inline = true)
    List<Section> sections = new ArrayList<Section>();
    @Element(type=Vocab.class)
    Vocab vocab;
    @Element(type=Grammar.class)
    Grammar grammar;

    public String getHeaderText(){
        return headertext;
    }

    public ArrayList<Section> getSections(){
        return (ArrayList)sections;
    }

    public Vocab getVocab(){
        return vocab;
    }

    public Grammar getGrammar(){
        return grammar;
    }



    public static void main(String[] args){
        System.out.println("hello");
        Serializer serializer = new Persister();
        File file = new File("src/main/assets/Korean/unit1/lesson1/lesson_plan.xml");
        try {
            LessonPlan plan = serializer.read(LessonPlan.class, file);
            System.out.println(plan.getHeaderText());
            System.out.println(plan.sections.get(0).titletext);
            System.out.println(((Lesson)plan.sections.get(0).components.get(0)).text);
            System.out.println(plan.sections.get(0).components.get(1));
    }
        catch(Exception e){e.printStackTrace();}
    }
}