package Main;

import java.util.EventObject;

/**
 * Created by akash on 12/26/2016.
 */
public class LessonSelectionEvent extends EventObject {
    String lesson;
    public LessonSelectionEvent(Object source,String lesson){
        super(source);
        this.lesson = lesson;
    }

    public String getLesson(){
        return lesson;
    }
}
