package example.LingWizard.XML;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akash on 1/12/2017.
 */
public class Vocab{
    @ElementList(type=Term.class,entry="Term",inline = true)
    List<Term> terms = new ArrayList<>();

    public ArrayList<String> getTerms(){
        ArrayList<String> names = new ArrayList<>();
        for(Term t:terms){
            names.add(t.getName());
        }
        return names;
    }

    public ArrayList<String> getDefinitions(){
        ArrayList<String> definitions = new ArrayList<>();
        for(Term t:terms){
            definitions.add(t.getDefinition());
        }
        return definitions;
    }
}


class Term {
    @Attribute
    String name;
    @Attribute
    String definition;

    public String getName(){
        return name;
    }

    public String getDefinition(){
        return definition;
    }
}
