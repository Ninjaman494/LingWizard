package Korean;

import Main.LanguageModel;
import Main.UnitSelectionEvent;
import Main.TextView;
import Templates.LessonTemplate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by akash on 12/26/2016.
 */
public class Model extends LanguageModel {

    String language = "korean";
    String currentLesson;
    public Model(TextView view){
        super(view);
    }
    public ArrayList<String> getLessonList(){
        return readFile("LessonList.txt");
    }

    public void handleEvent(UnitSelectionEvent event){
        currentLesson = event.getSelection();
        view.pickTemplate(new LessonTemplate(readFile("data/"+currentLesson+".txt").get(0)));
    }

    public String getLanguage() {
        return language;
    }

    //Helper method for reading files
    public ArrayList<String> readFile(String path){
        String dir = new File("Korean").getAbsolutePath();
        ArrayList<String> data = new ArrayList<>();
        try {
            BufferedReader file = new BufferedReader(new FileReader(dir+"/"+path));
            String letter;
            while((letter = file.readLine())!=null) {
                data.add(letter);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
