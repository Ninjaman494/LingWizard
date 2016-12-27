package Main;

import Templates.ListTemplate;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

/**
 * Created by akash on 12/26/2016.
 */
public class MainModule {
    ArrayList<String> languages = new ArrayList();
    LanguageModel LangModel;
    String currentLesson;
    TextView view;
    public MainModule(){
        languages.add("Korean");
    }

    public ArrayList<String> getLangs(){
        return languages;
    }

    public void handleEvent(LanguageSelectionEvent event){
        view = (TextView)event.getSource();
        try {
            Class c = Class.forName(event.getLanguage() + ".Model");
            Constructor cons = c.getConstructor(Main.TextView.class);
            LangModel = (LanguageModel)cons.newInstance(view);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        view.pickTemplate(new ListTemplate(LangModel.getLessonList()));
    }

    public void handleEvent(LessonSelectionEvent event){
        currentLesson = event.getLesson();
        LangModel.handleEvent(event);
    }
}
