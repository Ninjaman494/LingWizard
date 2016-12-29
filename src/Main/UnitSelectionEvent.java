package Main;

import java.util.EventObject;

/**
 * Created by akash on 12/26/2016.
 */
public class UnitSelectionEvent extends EventObject {
    String selection;
    public UnitSelectionEvent(Object source, String lesson){
        super(source);
        this.selection = lesson;
    }

    public String getSelection(){
        return selection;
    }
}
