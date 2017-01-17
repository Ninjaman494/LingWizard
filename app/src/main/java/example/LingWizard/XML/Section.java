package example.LingWizard.XML;

import android.support.v4.app.Fragment;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

import example.LingWizard.LessonFragment;

/**
 * Created by akash on 1/7/2017.
 */
@Root
public class Section {
    @Attribute
    String titletext;
    @ElementListUnion({
            @ElementList(entry="lesson", inline=true, type=Lesson.class),
            @ElementList(entry="game", inline=true, type=Game.class),
    })
    List<Object> components = new ArrayList<Object>();
    int currentComponent = 0;

    public String getTitleText(){
        return titletext;
    }

    public ArrayList<Object> getComponents(){
        return (ArrayList)components;
    }

    public int getLength(){
        return components.size();
    }

    public Object onCompletedComponent(){
        currentComponent++;
        if(currentComponent>=components.size()){
            return null;
        }
        else{
            return components.get(currentComponent);
        }
    }

    public ArrayList<Fragment> generateFragments(){
        ArrayList<Fragment> frags = new ArrayList<>();
        for(Object component : components){
            if(component instanceof Lesson) {
                LessonFragment frag = LessonFragment.newInstance(((Lesson) component).getText(),((Lesson) component).getImage());
                frags.add(frag);
            }
        }
        return frags;
    }

    public ArrayList<String> getNotes(){
        ArrayList<String> frags = new ArrayList<>();
        for(Object component : components){
            if(component instanceof Lesson) {
                frags.add(((Lesson) component).getText());
            }
        }
        return frags;
    }

    public Lesson getLesson(int position){
        return (Lesson)components.get(position);
    }

    public ArrayList<String> getImages(){
        return null;
    }

}

@Root
class Game{
}
