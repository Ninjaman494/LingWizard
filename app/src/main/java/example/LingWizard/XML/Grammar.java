package example.LingWizard.XML;

import org.simpleframework.xml.ElementList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akash on 1/13/2017.
 */

public class Grammar {
    @ElementList(type=String.class,entry="concept",inline = true)
    List<String> concepts;

    public ArrayList<String> getConcepts(){
        return (ArrayList)concepts;
    }
}
