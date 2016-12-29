package Main;

import Templates.LessonListTemplate;
import Templates.LessonTemplate;
import Templates.ListTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by akash on 12/26/2016.
 */
public class MainModule {
    static String LESSONLIST_PATH = "Main/LessonList.xml";

    ArrayList<String> languages = new ArrayList();
    LanguageModel LangModel;
    String currentLanguage;
    String currentUnit;
    String currentLesson;
    TextView view;
    public MainModule(){
        Document doc = parseXML(LESSONLIST_PATH);
        NodeList nList = doc.getElementsByTagName("language");
        for(int i=0;i<nList.getLength();i++) {
            Element node = (Element)nList.item(i);
            languages.add(node.getAttribute("id"));
        }
    }

    public void handleEvent(LanguageSelectionEvent event){
        view = (TextView)event.getSource();
        currentLanguage = event.getLanguage();
        view.pickTemplate(new ListTemplate(getUnits(currentLanguage)));
    }

    public void handleEvent(UnitSelectionEvent event){
        currentUnit = event.getSelection();
        view.pickTemplate(new LessonListTemplate(getLessons(currentLanguage,currentUnit)));
    }

    public void handleEvent(LessonSelectionEvent event){
        currentLesson = event.getSelection();
        //This is gross I know, it's just temporary until the front-end gets built. My plan is to have the buttons send the tag directly, I just gotta figure out how....
        String tag = currentLanguage.charAt(0)+"U"+currentUnit.charAt(currentUnit.length()-1)+"L"+currentLesson.charAt(currentLesson.length()-1);
        view.pickTemplate(new LessonTemplate(readFile(getPath(tag),tag).get(0)));
    }

    public static ArrayList<String> getLessons(String language,String unit){
        ArrayList<String> lessons = new ArrayList<>();
        try {
            Document doc = parseXML(LESSONLIST_PATH);

            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            String compile = "//language[@id='"+language+"']"+"/Unit[@name='"+unit+"']";
            XPathExpression expr = xpath.compile(compile);
            Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
            NodeList cList = ((Element)node).getChildNodes();
            for(int i=0;i<cList.getLength();i++) {
                if(cList.item(i) instanceof Element) {
                    Element n = (Element)cList.item(i);
                    lessons.add(n.getTextContent());
                }
            }
            return lessons;

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return lessons;
    }

    public static ArrayList<String> getUnits(String id){
        ArrayList<String> units = new ArrayList<>();
        try {
            Document doc = parseXML(LESSONLIST_PATH);

            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            String compile = "//language[@id='"+id+"']";
            XPathExpression expr = xpath.compile(compile);

            NodeList nList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
            Element x = (Element)nList.item(0);
            NodeList cList = x.getChildNodes();
            for(int i=0;i<cList.getLength();i++) {
                if((cList.item(i) instanceof Element)) {
                    Element node = (Element) cList.item(i);
                    units.add(node.getAttribute("name"));
                }
            }
            return units;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return units;
    }

    public static String getPath(String id){
        Document doc = parseXML(LESSONLIST_PATH);
        try {
            XPathFactory xPathfactory = XPathFactory.newInstance();
            XPath xpath = xPathfactory.newXPath();
            String compile = "//*[@id='"+id+"']";
            XPathExpression expr = xpath.compile(compile);
            Element node = (Element) expr.evaluate(doc, XPathConstants.NODE);
            return node.getAttribute("path");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Document parseXML(String path){
        Document doc = null;
        try {
            File fXmlFile = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return doc;
    }

    public ArrayList<String> readFile(String path,String tag){
        String dir = new File("Korean").getAbsolutePath();
        ArrayList<String> data = new ArrayList<>();
        try {
            BufferedReader file = new BufferedReader(new FileReader(path+"/"+tag+".txt"));
            //BufferedReader file = new BufferedReader(new FileReader("Korean/data/Unit1/lesson1/KU1L1.txt"));
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

    public ArrayList<String> getLanguages(){
        return languages;
    }
}
