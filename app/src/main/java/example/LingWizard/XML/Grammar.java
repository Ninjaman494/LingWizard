package example.LingWizard.XML;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akash on 1/13/2017.
 */

public class Grammar {
    @ElementList(type=Concept.class,entry="concept",inline = true)
    List<Concept> concepts;

    public ArrayList<Concept> getConcepts(){
        return (ArrayList)concepts;
    }

    public String getName(int position){
        return concepts.get(position).getName();
    }

    public ArrayList<String> getPages(int position){
        return concepts.get(position).getPages();
    }
}
class Concept{
    @Attribute
    String name;
    @ElementList(type=String.class,entry="page",inline=true,required = false)
    List<String> pages;

    public String getName(){
        return name;
    }

    public ArrayList<String> getPages(){
        return (ArrayList)pages;
    }
}