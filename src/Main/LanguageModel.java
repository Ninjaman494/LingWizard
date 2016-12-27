package Main;

import java.util.ArrayList;

/**
 * Created by akash on 12/26/2016.
 */
public abstract class LanguageModel {
    public TextView view;
    public LanguageModel(TextView view){
        this.view = view;
    }
    public abstract ArrayList<String> getLessonList();
    public abstract void handleEvent(LessonSelectionEvent event);
}
