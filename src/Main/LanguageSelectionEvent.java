package Main;

import java.util.EventObject;

/**
 * Created by akash on 12/26/2016.
 */
public class LanguageSelectionEvent extends EventObject {
    String language;
    public LanguageSelectionEvent(Object source,String language){
        super(source);
        this.language = language;
    }

    public String getLanguage(){
        return language;
    }
}
