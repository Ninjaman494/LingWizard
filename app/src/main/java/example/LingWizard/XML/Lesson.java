package example.LingWizard.XML;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by akash on 1/8/2017.
 */

@Root
public class Lesson{
    @Attribute
    String text;
    @Attribute
    String image;

    public String getText(){
        return text;
    }

    public String getImage(){
        return image;
    }
}