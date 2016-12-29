package Main;

import java.util.EventObject;

/**
 * Created by akash on 12/28/2016.
 */
public class LessonSelectionEvent extends EventObject {
    String selection;
    public LessonSelectionEvent(Object source, String lesson){
        super(source);
        this.selection = lesson;
    }

    public String getSelection(){
        return selection;
    }
}
